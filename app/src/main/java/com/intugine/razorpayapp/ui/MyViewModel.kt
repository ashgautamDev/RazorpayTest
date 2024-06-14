package com.intugine.razorpayapp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.intugine.razorpayapp.MyApplication
import com.intugine.razorpayapp.data.Repository
import com.intugine.razorpayapp.data.remote.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MyViewModel(private val repository: Repository) : ViewModel() {

    val TAG = "MyViewModel"
    private val _getMoviesState: MutableLiveData<NetworkResult> = MutableLiveData<NetworkResult>()


    val getMoviesDetails = _getMoviesState

    private val _getMoviesByIdState: MutableLiveData<NetworkResult> = MutableLiveData<NetworkResult>()


    val getMoviesByIdDetails = _getMoviesByIdState

     fun searchMovie(query: String) = viewModelScope.launch(Dispatchers.IO){
        val response = repository.searchMovies(query)

         Log.d(TAG, "searchMovie: ${response.body()}")
         try {
             if (response.isSuccessful){
                  if (response.body() != null){
                      _getMoviesState.postValue(NetworkResult.Success(response.body()!!))
                 } else {
                      _getMoviesState.postValue(NetworkResult.Empty)
                 }
             }
         } catch (e : Exception){
             _getMoviesState.postValue(NetworkResult.Error(e))
         }
    }

    fun getMovieByID(id: String) = viewModelScope.launch(Dispatchers.IO){
        val response = repository.movieById(id)

        Log.d(TAG, "MovieById: ${response.body()}")
        try {
            if (response.isSuccessful){
                if (response.body() != null){
                    _getMoviesByIdState.postValue(NetworkResult.Success(response.body()!!))
                } else {
                    _getMoviesByIdState.postValue(NetworkResult.Empty)
                }
            }
        } catch (e : Exception){
            _getMoviesByIdState.postValue(NetworkResult.Error(e))
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository = (this[APPLICATION_KEY] as MyApplication).repository

                MyViewModel(myRepository)
            }
        }
    }


}