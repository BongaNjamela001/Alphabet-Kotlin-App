package com.csc2002s.assgmnts.apps.alphabetapplication.data

import com.google.gson.annotations.SerializedName

/**
 * Data class that represents a user from Unsplash.
 *
 * Not all of the fields returned from the API are represented here; only the ones used in this
 */
data class UnsplashUser(
    @field:SerializedName("name") val name: String,
    @field:SerializedName("username") val username: String
) {
    val attributionUrl: String
        get() {
            return "alphabet"
        }
}
