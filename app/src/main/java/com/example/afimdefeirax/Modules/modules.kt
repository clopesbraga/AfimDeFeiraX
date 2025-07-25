package com.example.afimdefeirax.Modules

import com.example.afimdefeirax.Repository.FeiraRepository.FeirasRepositoryImpl
import com.example.afimdefeirax.Repository.HistoricoRepository
import com.example.afimdefeirax.SharedPreferences.HistTutorialSharedImpl
import com.example.afimdefeirax.SharedPreferences.HistoricoShared
import com.example.afimdefeirax.SharedPreferences.ILoginShared
import com.example.afimdefeirax.SharedPreferences.ListProductsShared
import com.example.afimdefeirax.SharedPreferences.LoginSharedImpl
import com.example.afimdefeirax.SharedPreferences.MapTutorialSharedImpl
import com.example.afimdefeirax.SharedPreferences.ProductTutorialSharedImpl
import com.example.afimdefeirax.Utils.FirebaseAnalytics.FirebaseAnalyticsImpl
import com.example.afimdefeirax.Utils.FirebaseAuth.FirebaseAuthServiceImpl
import com.example.afimdefeirax.Utils.FocusCamera
import com.example.afimdefeirax.Utils.Location.LocationImpl
import com.example.afimdefeirax.ViewModel.HistoricoViewModel
import com.example.afimdefeirax.ViewModel.LoginViewModel
import com.example.afimdefeirax.ViewModel.MainViewModel
import com.example.afimdefeirax.ViewModel.MapaFeirasViewModel
import com.example.afimdefeirax.ViewModel.ProdutosViewModel
import com.google.android.gms.location.LocationServices
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{


     single{FeirasRepositoryImpl()}
     single{ HistoricoRepository(androidContext()) }
     single {LocationServices.getFusedLocationProviderClient(androidContext()) }
     single {LocationImpl(get(),androidContext())}
     single {FocusCamera()}
     single { LoginSharedImpl(androidContext()) }
     single { FirebaseAuth.getInstance() }
     factory { FirebaseAuthServiceImpl (get())}
     factory{FirebaseAnalyticsImpl(get())}
     single{ FirebaseAnalytics.getInstance(get())}
     factory{ FirebaseAuthServiceImpl(get()) }
     single <ILoginShared> { LoginSharedImpl(androidContext()) }
     single { ListProductsShared(androidContext()) }
     single { HistoricoShared(androidContext()) }
     single{ MapTutorialSharedImpl(androidContext()) }
     single{ProductTutorialSharedImpl(androidContext())}
     single{ HistTutorialSharedImpl(androidContext())}
 }

val viewModelModule = module {
     viewModel{ MapaFeirasViewModel(get(),get(),get(),get(),get(),get())}
     viewModel{ ProdutosViewModel(get(),get(),get(),get(),get()) }
     viewModel{ HistoricoViewModel(get(),get(),get(),get(),get()) }
     viewModel{ LoginViewModel(get(),get(),get(),false) }
     viewModel{ MainViewModel() }
}


