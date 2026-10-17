package com.hackernews.futuristic.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hackernews.futuristic.data.api.AlgoliaApi
import com.hackernews.futuristic.data.api.HackerNewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideHackerNewsApi(okHttpClient: OkHttpClient, gson: Gson): HackerNewsApi =
        Retrofit.Builder()
            .baseUrl("https://hacker-news.firebaseio.com/v0/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(HackerNewsApi::class.java)

    @Provides
    @Singleton
    fun provideAlgoliaApi(okHttpClient: OkHttpClient, gson: Gson): AlgoliaApi =
        Retrofit.Builder()
            .baseUrl("https://hn.algolia.com/api/v1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(AlgoliaApi::class.java)
}
