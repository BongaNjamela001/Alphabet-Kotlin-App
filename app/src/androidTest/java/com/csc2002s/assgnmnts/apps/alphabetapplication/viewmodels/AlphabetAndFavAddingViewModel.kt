package com.csc2002s.assgnmnts.apps.alphabetapplication.viewmodels

import com.csc2002s.assgnmnts.apps.alphabetapplication.data.AlphabetAndFavAdding
import java.text.SimpleDateFormat
import java.util.Locale

class AlphabetAndFavAddingViewModel(addings: AlphabetAndFavAdding) {
    private val alphabet = checkNotNull(addings.alphabet)
    private val favAdding = addings.alphabetAdding[0]

    val addDateString: String = dateFormat.format(favAdding.lastAddingDate.time)
    val addingInterval
        get() = alphabet.addingInterval
    val imageUrl
        get() = alphabet.imageUrl
    val alphabetName
        get() = alphabet.name
    val alphabetDateString: String = dateFormat.format(favAdding.alphabetDate.time)
    val alphabetId
        get() = alphabet.alphabetId

    companion object {
        private val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.US)
    }
}
