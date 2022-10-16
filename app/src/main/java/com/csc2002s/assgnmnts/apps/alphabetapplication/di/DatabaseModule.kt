package com.csc2002s.assgnmnts.apps.alphabetapplication.di

import android.content.Context
import com.csc2002s.assgnmnts.apps.alphabetapplication.data.AppDatabase
import com.csc2002s.assgnmnts.apps.alphabetapplication.data.FavAddingDao
import com.csc2002s.assgnmnts.apps.alphabetapplication.data.AlphabetDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideAlphabetDao(appDatabase: AppDatabase): AlphabetDao {
        return appDatabase.alphabetDao()
    }

    @Provides
    fun provideFavAddingDao(appDatabase: AppDatabase): FavAddingDao {
        return appDatabase.favAddingDao()
    }
}
