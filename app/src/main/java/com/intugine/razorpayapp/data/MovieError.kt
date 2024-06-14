package com.intugine.razorpayapp.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class MovieError(
    @SerializedName("Error")
    val error: String?,
    @SerializedName("Response")
    val response: String?
) : Serializable