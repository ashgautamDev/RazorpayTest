package com.intugine.razorpayapp.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieDetail(
    @SerializedName("Actors")
    val actors: String?,
    @SerializedName("Awards")
    val awards: String?,
    @SerializedName("BoxOffice")
    val boxOffice: String?,
    @SerializedName("Country")
    val country: String?,
    @SerializedName("DVD")
    val dVD: String?,
    @SerializedName("Director")
    val director: String?,
    @SerializedName("Genre")
    val genre: String?,
    @SerializedName("imdbID")
    val imdbID: String?,
    @SerializedName("imdbRating")
    val imdbRating: String?,
    @SerializedName("imdbVotes")
    val imdbVotes: String?,
    @SerializedName("Language")
    val language: String?,
    @SerializedName("Metascore")
    val metascore: String?,
    @SerializedName("Plot")
    val plot: String?,
    @SerializedName("Poster")
    val poster: String?,
    @SerializedName("Production")
    val production: String?,
    @SerializedName("Rated")
    val rated: String?,
    @SerializedName("Response")
    val response: String?,
    @SerializedName("Runtime")
    val runtime: String?,
    @SerializedName("Title")
    val title: String?,
    @SerializedName("Type")
    val type: String?,
    @SerializedName("Website")
    val website: String?,
    @SerializedName("Writer")
    val writer: String?,
    @SerializedName("Year")
    val year: String?
) : Serializable