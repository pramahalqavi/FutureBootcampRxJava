package com.example.testapp.model

import com.google.gson.annotations.SerializedName

data class PokeResponse<T> (
    @SerializedName("count")
    var count: Int? = null,
    @SerializedName("next")
    var next: String? = null,
    @SerializedName("previous")
    var previous: String? = null,
    @SerializedName("results")
    var results: T? = null
)