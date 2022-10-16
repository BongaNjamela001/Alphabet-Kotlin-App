package com.csc2002s.assgnmnts.apps.alphabetapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Calendar

@Entity(
    tableName = "favourites",
    foreignKeys = [
        ForeignKey(entity = AlphabetApplication::class, parentColumns = ["id"], childColumns = ["alphabet_id"])
    ],
    indices = [Index("alphabet_id")]
)
data class AlphabetPlanting(
    @ColumnInfo(name = "alphabet_id") val alphabetId: String,

    /**
     * Indicates when the [Alphabet] was added. Used for showing notification when it's time
     * to harvest the alphabet.
     */
    @ColumnInfo(name = "alphabet_date") val alphabetDate: Calendar = Calendar.getInstance(),

    @ColumnInfo(name = "last_adding_date")
    val lastWateringDate: Calendar = Calendar.getInstance()
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var gardenPlantingId: Long = 0
}
