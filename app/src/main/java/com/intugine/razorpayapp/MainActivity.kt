package com.intugine.razorpayapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.intugine.razorpayapp.ui.MovieListActivity
import com.intugine.razorpayapp.utils.CommonUtils
import com.intugine.razorpayapp.utils.Constants

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText =  findViewById<EditText>(R.id.editText)



        findViewById<Button>(R.id.btn_search).setOnClickListener{

            if (editText.text.isNotEmpty()) {
                if (CommonUtils.checkForInternet(this)){
                val intent = Intent(applicationContext, MovieListActivity::class.java).apply {
                    putExtra(Constants.Q,editText.text.toString())
                }
                startActivity(intent)
                editText.text = null
                } else
                {
                    Toast.makeText(this, "No internet", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(this, "Please enter the movie name", Toast.LENGTH_SHORT).show()
            }
        }
    }
}