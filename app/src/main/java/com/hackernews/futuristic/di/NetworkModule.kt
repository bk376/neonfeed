package com.hackernews.futuristic.di

import com.hackernews.futuristic.data.api.HackerNewsApi
import com.hackernews.futuristic.data.service.CloudLLMService
import com.hackernews.futuristic.data.service.ClaudeApiService
import com.hackernews.futuristic.data.service.GeminiApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }
    
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://hacker-news.firebaseio.com/v0/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
    
    @Provides
    @Singleton
    fun provideHackerNewsApi(retrofit: Retrofit): HackerNewsApi {
        return retrofit.create(HackerNewsApi::class.java)
    }
    
    @Provides
    @Singleton
    fun provideCloudLLMService(): CloudLLMService {
        return CloudLLMService()
    }
    
    @Provides
    @Singleton
    fun provideClaudeApiService(gson: Gson): ClaudeApiService {
        return ClaudeApiService(gson)
    }
    
    @Provides
    @Singleton
    fun provideGeminiApiService(gson: Gson): GeminiApiService {
        return GeminiApiService(gson)
    }
}