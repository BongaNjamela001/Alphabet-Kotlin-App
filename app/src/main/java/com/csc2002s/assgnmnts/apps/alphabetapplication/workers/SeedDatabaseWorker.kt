package com.csc2002s.assgnmnts.apps.alphabetapplication.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.csc2002s.assgnmnts.apps.alphabetapplication.data.AppDatabase
import com.csc2002s.assgnmnts.apps.alphabetapplication.data.Alphabet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SeedDatabaseWorker(
        context: Context,
        workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val filename = inputData.getString(KEY_FILENAME)
            if (filename != null) {
                applicationContext.assets.open(filename).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val alphabetType = object : TypeToken<List<Alphabet>>() {}.type
                        val alphabetList: List<Alphabet> = Gson().fromJson(jsonReader, alphabetType)

                        val database = AppDatabase.getInstance(applicationContext)
                        database.alphabetDao().insertAll(alphabetList)

                        Result.success()
                    }
                }
            } else {
                Log.e(TAG, "Error seeding database - no valid filename")
                Result.failure()
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
        const val KEY_FILENAME = "ALPHABET_DATA_FILENAME"
    }
}
