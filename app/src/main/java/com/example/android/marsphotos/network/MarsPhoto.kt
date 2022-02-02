package com.example.android.marsphotos.network

import com.squareup.moshi.Json

data class MarsPhoto (

    /* To use variable names in your data class that differ from the key
        names in the JSON response, use the @Json annotation. */
    val id: String,
    @Json(name = "img_src") val imgSrcUrl: String
)