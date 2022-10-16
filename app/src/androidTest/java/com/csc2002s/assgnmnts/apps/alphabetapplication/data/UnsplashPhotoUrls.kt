package com.csc2002s.assgnmnts.apps..data

import com.google.gson.annotations.SerializedName

/**
 * Data class that represents URLs available for a Unsplash photo.
 *
 * Although several photo sizes are available, this project uses only uses the `small` sized photo.
 */
data class UnsplashPhotoUrls(
    @field:SerializedName("small") val small: String
)
