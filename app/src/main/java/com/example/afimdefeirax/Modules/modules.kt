package com.example.afimdefeirax.Modules

import com.example.afimdefeirax.Repository.FeiraRepository.FeirasRepositoryImpl
import com.example.afimdefeirax.Utils.FocusCamera
import com.example.afimdefeirax.Utils.LocationImpl
import com.example.afimdefeirax.ViewModel.MapaFeirasViewModel
import com.google.android.gms.location.LocationServices
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{

     viewModel{ MapaFeirasViewModel(get()) }
     single{FeirasRepositoryImpl()}
     single {LocationServices.getFusedLocationProviderClient(androidContext()) }
     single {LocationImpl(get())}
     single {FocusCamera()}
 }


