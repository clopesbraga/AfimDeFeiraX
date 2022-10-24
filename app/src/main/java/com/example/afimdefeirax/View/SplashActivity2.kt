package com.example.afimdefeirax.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.afimdefeirax.R
import com.example.afimdefeirax.SharedPreferences.LoginShared
import com.example.afimdefeirax.databinding.ActivitySplash2Binding
import com.example.afimdefeirax.databinding.ActivitySplashBinding

class SplashActivity2 : AppCompatActivity() {

    private lateinit var msharedlogin: LoginShared
    private lateinit var binding: ActivitySplash2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplash2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        msharedlogin = LoginShared(application.applicationContext)
        inicializeMainActivity()
    }

    private fun inicializeMainActivity() {
        Handler().postDelayed({
            verifyAcess()
            finish()
        }, 3000)
    }

    private fun verifyAcess() {
        if (!msharedlogin.getString("id").isNullOrEmpty()) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }


}