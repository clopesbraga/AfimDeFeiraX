package com.example.afimdefeirax.View

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.afimdefeirax.SharedPreferences.LoginSharedImpl

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    companion object {
        private const val ID ="id"
    }

    private lateinit var mSharedLogin: LoginSharedImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        mSharedLogin = LoginSharedImpl(application.applicationContext)
        verifyAcess()
    }

    private fun verifyAcess() {
        if (!mSharedLogin.getString(ID).isNullOrEmpty()) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
//            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

}