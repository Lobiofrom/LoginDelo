package com.example.data

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit

class Retrofit {
    val api: Api by lazy {
        Retrofit
            .Builder()
            .baseUrl("https://lk.pravoe-delo.su/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().also {
                    it.level = HttpLoggingInterceptor.Level.BODY
                }).build()
            )
            .build()
            .create(Api::class.java)
    }

    interface Api {

        @GET("getCode")
        suspend fun getCode(
            @Query("login") login: String,
        ): CodeDto

        @GET("regenerateCode")
        suspend fun regenerateCode(
            @Query("login") login: String,
        )

        @GET("getToken")
        suspend fun getToken(
            @Query("login") login: String,
            @Query("password") password: String,
        ): ResponseBody
    }
}