package com.androidstation.trendingmovies.utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.androidstation.trendingmovies.R
import com.androidstation.trendingmovies.ui.popular_movie.MainActivity

class SplashActivity : AppCompatActivity() {

     lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        handler = Handler()
        handler.postDelayed({

            startActivity(Intent(this, MainActivity::class.java))
            finish()

        },4000 )

        }

    }
