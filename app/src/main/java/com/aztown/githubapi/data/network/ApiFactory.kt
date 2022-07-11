package com.aztown.githubapi.data.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private const val BASE_URL = "https://api.github.com/"

    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val headers = Interceptor { chain ->
        val original: Request = chain.request()
        val request: Request = original.newBuilder()
            .header("Accept", "application/vnd.github+json")
            .method(original.method, original.body)
            .build()
        chain.proceed(request)
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(headers)
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(httpClient)
        .build()

    val apiService = retrofit.create(ApiService::class.java)
}