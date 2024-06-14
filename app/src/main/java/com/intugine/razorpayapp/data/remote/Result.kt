package com.intugine.razorpayapp.data.remote

import com.intugine.razorpayapp.data.MovieDetail

sealed class NetworkResult {
    data class Success(val data: MovieDetail) : NetworkResult()
    data class Error(val e : Exception) : NetworkResult()
    data object Empty : NetworkResult()
}