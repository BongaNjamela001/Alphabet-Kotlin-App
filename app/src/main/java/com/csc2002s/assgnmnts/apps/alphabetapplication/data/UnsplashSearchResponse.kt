package com.csc2002s.assgnmnts.apps.alphabetapplication.data

import com.google.gson.annotations.SerializedName

/**
 * Data class that represents a photo search response from Unsplash.
 *
 * Not all of the fields returned from the API are represented here; only the ones used in this
 */
data class UnsplashSearchResponse(
    @field:SerializedName("results") val results: List<UnsplashPhoto>,
    @field:SerializedName("total_pages") val totalPages: Int
)
