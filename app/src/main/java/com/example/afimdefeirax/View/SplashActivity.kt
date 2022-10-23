package com.example.afimdefeirax.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.afimdefeirax.R
import com.example.afimdefeirax.SharedPreferences.LoginShared
import com.example.afimdefeirax.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var msharedlogin: LoginShared
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        msharedlogin = LoginShared(application.applicationContext)
        inicializeMainActivity()
    }

    private fun inicializeMainActivity() {
        Handler().postDelayed({
            changeImageSplash()
            verifyAcess()
            finish()
        }, 3000)
    }

    private fun changeImageSplash() {
        binding.imgLogoBackground.setImageResource(R.mipmap.ic_empresa_logo_background)
        binding.imgLogoBackground.setImageResource(R.mipmap.ic_logo_app_foreground)
    }

    private fun verifyAcess() {
        if (!msharedlogin.getString("id").isNullOrEmpty()) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }


}