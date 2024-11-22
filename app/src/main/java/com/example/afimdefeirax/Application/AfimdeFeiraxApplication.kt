package com.example.afimdefeirax.Application

import android.app.Application
import com.example.afimdefeirax.Modules.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AfimdeFeiraxApplication:Application() {

    override fun onCreate(){
        super.onCreate()

        startKoin{
            androidContext(applicationContext)
            modules(appModule)
        }

    }




}
