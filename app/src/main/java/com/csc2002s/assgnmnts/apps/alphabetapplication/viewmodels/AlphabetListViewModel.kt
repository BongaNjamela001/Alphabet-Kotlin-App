package com.csc2002s.assgnmnts.apps.alphabetapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.csc2002s.assgnmnts.apps.alphabetapplication.AlphabetListFragment
import com.csc2002s.assgnmnts.apps.alphabetapplication.data.Alphabet
import com.csc2002s.assgnmnts.apps.alphabetapplication.data.AlphabetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The ViewModel for [AlphabetListFragment].
 */
@HiltViewModel
class AlphabetListViewModel @Inject internal constructor(
    alphabetRepository: AlphabetRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val practiceZone: MutableStateFlow<Int> = MutableStateFlow(
        savedStateHandle.get(PRACTICE_ZONE_SAVED_STATE_KEY) ?: NO_PRACTICE_ZONE
    )

    val alphabets: LiveData<List<Alphabet>> = practiceZone.flatMapLatest { zone ->
        if (zone == NO_PRACTICE_ZONE) {
            alphabetRepository.getAlphabets()
        } else {
            alphabetRepository.getAlphabetsWithPracticeNumber(zone)
        }
    }.asLiveData()

    init {
        viewModelScope.launch {
            practiceZone.collect { newPracticeZone ->
                savedStateHandle.set(PRACTICE_ZONE_SAVED_STATE_KEY, newPracticeZone)
            }
        }
    }

    fun setPracticeZoneNumber(num: Int) {
        practiceZone.value = num
    }

    fun clearPracticeZoneNumber() {
        practiceZone.value = NO_PRACTICE_ZONE
    }

    fun isFiltered() = practiceZone.value != NO_PRACTICE_ZONE

    companion object {
        private const val NO_PRACTICE_ZONE = -1
        private const val PRACTICE_ZONE_SAVED_STATE_KEY = "PRACTICE_ZONE_SAVED_STATE_KEY"
    }
}
