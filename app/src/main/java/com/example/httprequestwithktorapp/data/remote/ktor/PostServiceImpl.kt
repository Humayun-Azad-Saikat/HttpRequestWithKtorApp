package com.example.httprequestwithktorapp.data.remote.ktor

import android.util.Log
import android.util.Log.e
import com.example.httprequestwithktorapp.data.remote.dto.PostResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.*


class PostServiceImpl(
    private val httpClient: HttpClient
): PostService {

    override suspend fun getPost(): List<PostResponse> {

        return try {
            httpClient.get { url(HttpRoutes.POSTS) }.body()
        }catch (e: RedirectResponseException){
            Log.d("ErrorKtor", e.message)
            emptyList()
        }


    }

    override suspend fun createPost(postRequest: PostRequest): PostResponse? {

        return try {
                httpClient.post {
                url(HttpRoutes.POSTS)
                contentType(ContentType.Application.Json)
                setBody(postRequest)
            }.body()
        }catch (e: RedirectResponseException){
            Log.d("ErrorKtor", e.message)
            null
        }

    }

}