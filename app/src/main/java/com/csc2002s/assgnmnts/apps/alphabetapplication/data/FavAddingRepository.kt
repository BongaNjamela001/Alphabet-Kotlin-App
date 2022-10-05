package com.csc2002s.assgnmnts.apps.alphabetapplication.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavAddingRepository @Inject constructor(
    private val favAddingDao: FavAddingDao
) {

    suspend fun createFavAdding(alphabetId: String) {
        val favAdding = FavAdding(alphabetId)
        favAddingDao.insertFavAdding(favAdding)
    }

    suspend fun removeFavAdding(favAdding: FavAdding) {
        favAddingDao.deleteFavAdding(favAdding)
    }

    fun isAdded(alphabetId: String) =
        favAddingDao.isAdded(alphabetId)

    fun getAddedFavs() = favAddingDao.getAddedFavs()

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: FavAddingRepository? = null

        fun getInstance(favAddingDao: FavAddingDao) =
            instance ?: synchronized(this) {
                instance ?: FavAddingRepository(favAddingDao).also { instance = it }
            }
    }
}
