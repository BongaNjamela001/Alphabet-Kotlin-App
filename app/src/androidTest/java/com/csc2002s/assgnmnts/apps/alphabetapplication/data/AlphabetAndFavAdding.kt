package com.csc200s.assgnmnts.apps.alphabetapplication.data

import androidx.room.Embedded
import androidx.room.Relation

/**
 * This class captures the relationship between an [Alphabet] and a user's [FavAdding], which is
 * used by Room to fetch the related entities.
 */
data class AlphabetAndFavAdding(
    @Embedded
    val alphabet: Alphabet,

    @Relation(parentColumn = "id", entityColumn = "alphabet_id")
    val alphabetAdding: List<FavAdding> = emptyList()
)
