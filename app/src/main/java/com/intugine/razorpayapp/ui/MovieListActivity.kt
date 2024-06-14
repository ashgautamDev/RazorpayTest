package com.intugine.razorpayapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.intugine.razorpayapp.R
import com.intugine.razorpayapp.data.remote.NetworkResult
import com.intugine.razorpayapp.utils.CommonUtils
import com.intugine.razorpayapp.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieListActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MovieList"
    }

    private val viewModel: MyViewModel by viewModels { MyViewModel.Factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val query = intent.getStringExtra(Constants.Q)

        if (query != null) viewModel.searchMovie(query) else Toast.makeText(
            this,
            "Nothing to search!",
            Toast.LENGTH_SHORT
        ).show()

        collectData()
    }

    private fun collectData() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.getMoviesDetails.observeForever {
                when (it) {
                    NetworkResult.Empty -> {

                    }

                    is NetworkResult.Error -> {
                        Log.d(TAG, "collectData: ${it.e.message}")

                    }

                    is NetworkResult.Success -> {
                        Log.d(TAG, "collectData: ${it.data} ")
                        val data = it.data
                            val button = findViewById<Button>(R.id.btn_details)

                        if (data.title != null) {
                            button.isEnabled = true
                            findViewById<TextView>(R.id.find_movie).text = "Movie Found"
                            findViewById<TextView>(R.id.id).text = data.imdbID
                            findViewById<TextView>(R.id.name).text = data.title

                            button.setOnClickListener {
                                if (CommonUtils.checkForInternet(this@MovieListActivity)){
                                val intent =
                                        Intent(
                                            applicationContext,
                                            MovieDetailActivity::class.java
                                        ).apply {
                                            putExtra(Constants.ID, data.imdbID)
                                        }
                                    startActivity(intent)
                                } else
                                {
                                    Toast.makeText(this@MovieListActivity, "No internet", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        } else {
                            findViewById<TextView>(R.id.find_movie).text = "Movie Not found"
                            button.isEnabled = true
                            findViewById<TextView>(R.id.id).isVisible = false
                            findViewById<TextView>(R.id.name).isVisible = false
                            button.text = "Search again"
                            button.setOnClickListener {
                                super.onBackPressed();
                            }
                        }

                    }
                }
            }
        }
    }
}