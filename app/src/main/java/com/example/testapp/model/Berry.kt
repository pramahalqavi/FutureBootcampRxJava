package com.example.testapp.model

import com.google.gson.annotations.SerializedName

data class Berry (
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("url")
    var url: String? = null
)