package com.example.posts.interfaces

import com.example.posts.model.Post
import retrofit2.http.GET

interface ApiService {
        @GET("posts")
        suspend fun getPosts(): List<Post>
}