package com.intugine.razorpayapp.data.remote

import com.intugine.razorpayapp.data.MovieDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/")
    suspend fun searchMovie(
        @Query("t") query : String,
        @Query("apikey") key : String = "c8d30391"
    ) : Response<MovieDetail>

    @GET("/")
    suspend fun movieById(
        @Query("i") id : String,
        @Query("apikey") key : String = "c8d30391"
    ) : Response<MovieDetail>
}