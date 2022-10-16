package com.csc2002s.assgnmnts.apps.alphabetapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the Alphabet class.
 */
@Dao
interface AlphabetDao {
    @Query("SELECT * FROM alphabets ORDER BY alphabetName")
    fun getAlphabets(): Flow<List<Alphabet>>

    @Query("SELECT * FROM alphabets WHERE practiceNumber = :practiceNumber ORDER BY alphabetName")
    fun getAlphabetsWithPracticeNumber(practiceNumber: Int): Flow<List<Alphabet>>

    @Query("SELECT * FROM alphabets WHERE id = :alphabetId")
    fun getAlphabet(alphabetId: String): Flow<Alphabet>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(alphabets: List<Alphabet>)
}
