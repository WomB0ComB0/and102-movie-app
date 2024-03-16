package com.example.and102_movieapp

import com.google.gson.annotations.SerializedName

class TrendingMovies (
    @SerializedName("original_title")
    var movieTtile: String? = null,

    @SerializedName("overview")
    var movieDescription: String? = null,

    @SerializedName("poster_path")
    var movieImage: String? = null
)