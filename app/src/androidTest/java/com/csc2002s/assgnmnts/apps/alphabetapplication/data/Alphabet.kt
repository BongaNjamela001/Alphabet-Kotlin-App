package com.csc2002s.assgnmnts.apps.alphabetapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar
import java.util.Calendar.DAY_OF_YEAR

@Entity(tableName = "Alphabets")
data class Alphabet(
    @PrimaryKey @ColumnInfo(name = "id") val alphabetId: String,
    val name: String,
    val description: String,
    val alphaPos: Int,
    val addingInterval: Int = 7, // number of words starting with this alphabet to find and practice
    val imageUrl: String = ""
) {


    fun shouldBeAdded(since: Calendar, lastWateringDate: Calendar) =
        since > lastAddingDate.apply { add(DAY_OF_YEAR, addingInterval) }

    override fun toString() = name
}
