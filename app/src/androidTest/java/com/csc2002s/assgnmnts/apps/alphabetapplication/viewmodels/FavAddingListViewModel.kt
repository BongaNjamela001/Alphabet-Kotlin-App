package com.csc2002s.assgnmnts.apps..viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.google.samples.apps.sunflower.data.GardenPlantingRepository
import com.google.samples.apps.sunflower.data.PlantAndGardenPlantings
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavAddingListViewModel @Inject internal constructor(
    favAddingRepository: FavAddingRepository
) : ViewModel() {
    val alphabetAndFavAdding: LiveData<List<AlphabetAndFavAdding>> =
        alphabetAndFavAdding.getAddedFav().asLiveData()
}
