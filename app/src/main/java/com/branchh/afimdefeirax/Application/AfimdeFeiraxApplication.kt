package com.branchh.afimdefeirax.Application

import android.app.Application
import com.branchh.afimdefeirax.Modules.appModule
import com.branchh.afimdefeirax.Modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AfimdeFeiraxApplication:Application() {

    override fun onCreate(){
        super.onCreate()

        startKoin{
            androidContext(applicationContext)
            modules(appModule, viewModelModule)
        }

    }




}
