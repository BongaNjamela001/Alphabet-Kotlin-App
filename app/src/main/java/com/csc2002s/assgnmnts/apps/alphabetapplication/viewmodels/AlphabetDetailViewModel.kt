package com.csc2002s.assgnmnts.apps.alphabetapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.csc2002s.assgnmnts.apps.alphabetapplication.BuildConfig
import com.csc2002s.assgnmnts.apps.alphabetapplication.AlphabetDetailFragment
import com.csc2002s.assgnmnts.apps.alphabetapplication.AlphabetDetailFragmentDirections
import com.csc2002s.assgnmnts.apps.alphabetapplication.data.FavAddingRepository
import com.csc2002s.assgnmnts.apps.alphabetapplication.data.AlphabetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.text.Typography.dagger

/**
 * The ViewModel used in [AlphabetDetailFragment].
 */
@HiltViewModel
class AlphabetDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    alphabetRepository: AlphabetRepository,
    private val favAddingRepository: FavAddingRepository,
) : ViewModel() {

    val alphabetId: String = savedStateHandle.get<String>(ALPHABET_ID_SAVED_STATE_KEY)!!

    val isAdded = favAddingRepository.isAdded(alphabetId).asLiveData()
    val alphabet = alphabetRepository.getAlphabet(alphabetId).asLiveData()

    private val _showSnackbar = MutableLiveData(false)
    val showSnackbar: LiveData<Boolean>
        get() = _showSnackbar

    fun addAlphabetToFav() {
        viewModelScope.launch {
            favAddingRepository.createFavAdding(alphabetId)
            _showSnackbar.value = true
        }
    }

    fun dismissSnackbar() {
        _showSnackbar.value = false
    }

    fun hasValidUnsplashKey() = (BuildConfig.UNSPLASH_ACCESS_KEY != "null")

    companion object {
        private const val ALPHABET_ID_SAVED_STATE_KEY = "alphabetId"
    }
}
