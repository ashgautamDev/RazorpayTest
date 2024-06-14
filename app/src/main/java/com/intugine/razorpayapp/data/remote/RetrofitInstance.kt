package com.intugine.razorpayapp.data.remote

import com.google.gson.GsonBuilder
import com.intugine.razorpayapp.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private val httpLoggingInterceptor = HttpLoggingInterceptor()
    private val client = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()


    private var retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(client)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().enableComplexMapKeySerialization().serializeNulls()
                    .setPrettyPrinting()
                    .setVersion(1.0).create()
            )
        )
        .build()

    var service: ApiService = retrofit.create(ApiService::class.java)
}