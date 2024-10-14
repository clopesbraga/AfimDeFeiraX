package com.example.afimdefeirax.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.afimdefeirax.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inicializeMainActivity()
    }

    private fun inicializeMainActivity() {
        Handler().postDelayed({
            changeImageSplash()
            finish()
        }, 1000)
    }

    private fun changeImageSplash() {

        startActivity(Intent(this, SplashActivity2::class.java))
    }

}