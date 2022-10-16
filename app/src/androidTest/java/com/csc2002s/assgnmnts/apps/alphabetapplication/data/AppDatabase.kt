package com.csc2002s.assgnmnts.apps.alphabetapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.csc2002s.assgnmnts.apps.alphabetapplication.utilities.DATABASE_NAME
import com.csc2002s.assgnmnts.apps.alphabetapplication.utilities.ALPHABET_DATA_FILENAME
import com.csc2002s.assgnmnts.apps.alphabetapplication.workers.SeedDatabaseWorker
import com.csc2002s.assgnmnts.apps.alphabetapplication.workers.SeedDatabaseWorker.Companion.KEY_FILENAME

@Database(entities = [FavAdding::class, Alphabet::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favAddingDao(): FavAddingDao
    abstract fun alphabetDao(): AlphabetDao

    companion object {

        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
                                    .setInputData(workDataOf(KEY_FILENAME to ALPHABET_DATA_FILENAME))
                                    .build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                )
                .build()
        }
    }
}
