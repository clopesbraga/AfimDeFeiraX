package com.example.afimdefeirax.Modules

import android.app.Application
import com.example.afimdefeirax.Repository.FeiraRepository.FeirasRepositoryImpl
import com.example.afimdefeirax.Utils.DeviceCurrentTime
import com.example.afimdefeirax.Utils.FocusCamera
import com.example.afimdefeirax.Utils.LocationImpl
import com.example.afimdefeirax.ViewModel.MapaFeirasViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{

     viewModel{ MapaFeirasViewModel() }
     single{FeirasRepositoryImpl()}
     single{LocationImpl(get(),get())}
     single {FocusCamera()}
     single<FusedLocationProviderClient> {
        FusedLocationProviderClient(androidApplication())
    }
 }


