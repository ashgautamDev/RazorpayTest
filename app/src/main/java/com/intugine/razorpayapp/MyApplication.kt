package com.intugine.razorpayapp

import android.app.Application
import com.intugine.razorpayapp.data.Repository
import com.intugine.razorpayapp.data.remote.RetrofitInstance

class MyApplication : Application(){

    private val service = RetrofitInstance.service
    val repository = Repository(service)
}