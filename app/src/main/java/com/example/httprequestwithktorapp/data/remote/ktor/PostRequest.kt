package com.example.httprequestwithktorapp.data.remote.ktor

import kotlinx.serialization.Serializable

@Serializable
data class PostRequest(

    val title: String,
    val body: String,
    val userId: Int,

)
