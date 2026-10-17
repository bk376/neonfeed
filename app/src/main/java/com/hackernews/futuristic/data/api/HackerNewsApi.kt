package com.hackernews.futuristic.data.api

import com.hackernews.futuristic.data.model.Story
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/** Official HN Firebase API (https://github.com/HackerNews/API): live rankings, scores and users. */
interface HackerNewsApi {
    @GET("topstories.json")
    suspend fun getTopStories(): Response<List<Long>>

    @GET("newstories.json")
    suspend fun getNewStories(): Response<List<Long>>

    @GET("askstories.json")
    suspend fun getAskStories(): Response<List<Long>>

    @GET("showstories.json")
    suspend fun getShowStories(): Response<List<Long>>

    @GET("item/{id}.json")
    suspend fun getItem(@Path("id") id: Long): Response<Story>

    @GET("user/{id}.json")
    suspend fun getUser(@Path("id") id: String): Response<User>
}

data class User(
    val id: String,
    val created: Long,
    val karma: Int,
    val about: String?,
    val submitted: List<Long>?
)
