package com.intugine.razorpayapp.data

import com.intugine.razorpayapp.data.remote.ApiService

class Repository(private val apiService: ApiService) {

    suspend fun searchMovies(query : String) = apiService.searchMovie(query)

    suspend fun movieById(id : String) = apiService.movieById(id)
}