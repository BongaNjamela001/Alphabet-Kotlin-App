package com.csc2002s.assgnmnts.apps.alphabetapplication.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ALphabetRepository @Inject constructor(private val alphabetDao: AlphabetDao) {

    fun getAlphabets() = alphabetDao.getAlphabets()

    fun getAlphabet(alphabetId: String) = alphabetDao.getAlphabet(alphabetId)

    fun getAlphabetsWithPracticeNumber(practiceNumber: Int) =
        alphabetDao.getAlphabetsWithPracticeNumber(practiceNumber)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AlphabetRepository? = null

        fun getInstance(alphabetDao: AlphabetDao) =
            instance ?: synchronized(this) {
                instance ?: AlphabetRepository(alphabetDao).also { instance = it }
            }
    }
}
