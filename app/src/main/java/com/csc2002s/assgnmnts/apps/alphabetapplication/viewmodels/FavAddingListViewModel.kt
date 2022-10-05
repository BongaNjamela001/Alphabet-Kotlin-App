package com.csc2002s.assgnmnts.apps.alphabetapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.csc2002s.assgnmnts.apps.alphabetapplication.data.FavAddingRepository
import com.csc2002s.assgnmnts.apps.alphabetapplication.data.AlphabetAndFavAdding
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavAddingListViewModel @Inject internal constructor(
    favAddingRepository: FavAddingRepository
) : ViewModel() {
    val alphabetAndFavAdding: LiveData<List<AlphabetAndFavAdding>> =
        favAddingRepository.getAddedFavs().asLiveData()
}
