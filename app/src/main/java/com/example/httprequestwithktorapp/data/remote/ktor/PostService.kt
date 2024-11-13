package com.example.httprequestwithktorapp.data.remote.ktor

import com.example.httprequestwithktorapp.data.remote.dto.PostResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json

interface PostService {

    suspend fun getPost(): List<PostResponse>

    suspend fun createPost(postRequest: PostRequest): PostResponse?


    companion object{
        fun create(): PostService{
            return PostServiceImpl(
                httpClient = HttpClient(Android){

                    install(Logging){
                        level = LogLevel.ALL
                    }
                    install(ContentNegotiation){
                        json()
                    }

                }
            )
        }
    }


}