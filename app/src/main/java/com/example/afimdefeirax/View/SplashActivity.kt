package com.example.afimdefeirax.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.example.afimdefeirax.R
import com.example.afimdefeirax.SharedPreferences.LoginShared
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import kotlin.time.measureTime

class SplashActivity : AppCompatActivity() {

    private lateinit var msharedlogin: LoginShared

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        msharedlogin = LoginShared(application.applicationContext)
        inicializeMainActivity()

    }

    fun inicializeMainActivity(){
        Handler().postDelayed({
            verifyAcess()
            finish()
        }, 3000)
    }

    private fun verifyAcess(){
        if (!msharedlogin.getString("id").isNullOrEmpty()){
            startActivity( Intent(this,MainActivity::class.java))
        }else{
            startActivity( Intent(this,LoginActivity::class.java))
        }
    }
}