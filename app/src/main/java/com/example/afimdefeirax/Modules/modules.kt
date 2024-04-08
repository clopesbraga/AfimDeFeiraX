package com.example.afimdefeirax.Modules

import com.example.afimdefeirax.ViewModel.MapaFeirasViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{

     viewModel{ MapaFeirasViewModel() }

 }


