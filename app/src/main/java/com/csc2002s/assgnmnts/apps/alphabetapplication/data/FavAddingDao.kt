package com.csc2002s.assgnmnts.apps.alphabetapplication.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface FavAddingDao {
    @Query("SELECT * FROM favourites")
    fun getFavAdding(): Flow<List<FavAdding>>

    @Query("SELECT EXISTS(SELECT 1 FROM favourites WHERE alphabet_id = :alphabetId LIMIT 1)")
    fun isAdded(alphabetId: String): Flow<Boolean>

    /**
     * This query will tell Room to query both the [Alphabet] and [FavAdding] tables and handle
     * the object mapping.
     */
    @Transaction
    @Query("SELECT * FROM alphabets WHERE id IN (SELECT DISTINCT(alphabet_id) FROM favourites)")
    fun getAddedFavs(): Flow<List<AlphabetAndFavAdding>>

    @Insert
    suspend fun insertFavAdding(favAdding: FavAdding): Long

    @Delete
    suspend fun deleteFavAdding(favAdding: FavAdding)
}
