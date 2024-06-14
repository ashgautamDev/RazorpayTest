package com.intugine.razorpayapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.intugine.razorpayapp.MainActivity
import com.intugine.razorpayapp.R
import com.intugine.razorpayapp.data.remote.NetworkResult
import com.intugine.razorpayapp.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailActivity : AppCompatActivity() {

    private val viewModel: MyViewModel by viewModels { MyViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val id = intent.getStringExtra(Constants.ID)
        if (id != null) viewModel.getMovieByID(id) else Toast.makeText(
            this,
            "No ID present!",
            Toast.LENGTH_SHORT
        ).show()

        collectData()


    }

    private fun collectData() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.getMoviesByIdDetails.observeForever {
                when (it) {
                    NetworkResult.Empty -> {
                        Log.d("Details",  "collectData: Empty}")

                    }

                    is NetworkResult.Error -> {
                        Log.d("Details",  "collectData: ${it.e.message}")

                    }

                    is NetworkResult.Success -> {
                        Log.d("Details", "collectData: ${it.data} ")
                        val data = it.data

                        val details = StringBuilder()
                            .append("ID: ${data.imdbID}")
                            .append("\n\n")
                            .append(data.title)
                            .append("\n\n")
                            .append("Year: ${data.year}")
                            .append("\n\n")
                            .append("Actors: ${data.actors}")
                            .append("\n\n")
                            .append(data.plot)
                            .append("\n\n")
                            .append("Directed by")
                            .append("\n")
                            .append(data.director)

                        findViewById<TextView>(R.id.tv_details).text = details.toString()
                        findViewById<Button>(R.id.btn_home).visibility = View.VISIBLE
                        findViewById<Button>(R.id.btn_home).setOnClickListener {
                            val intent = Intent(applicationContext, MainActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }
}