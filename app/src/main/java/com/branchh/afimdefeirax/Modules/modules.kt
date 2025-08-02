package com.branchh.afimdefeirax.Modules

import com.branchh.afimdefeirax.Repository.FeiraRepository.FeirasRepositoryImpl
import com.branchh.afimdefeirax.Repository.HistoricoRepository
import com.branchh.afimdefeirax.SharedPreferences.HistTutorialSharedImpl
import com.branchh.afimdefeirax.SharedPreferences.HistoricoShared
import com.branchh.afimdefeirax.SharedPreferences.ILoginShared
import com.branchh.afimdefeirax.SharedPreferences.ListProductsShared
import com.branchh.afimdefeirax.SharedPreferences.LoginSharedImpl
import com.branchh.afimdefeirax.SharedPreferences.MapTutorialSharedImpl
import com.branchh.afimdefeirax.SharedPreferences.ProductTutorialSharedImpl
import com.branchh.afimdefeirax.Utils.FirebaseAnalytics.FirebaseAnalyticsImpl
import com.branchh.afimdefeirax.Utils.FirebaseAuth.FirebaseAuthServiceImpl
import com.branchh.afimdefeirax.Utils.FocusCamera
import com.branchh.afimdefeirax.Utils.Location.LocationImpl
import com.branchh.afimdefeirax.ViewModel.HistoricoViewModel
import com.branchh.afimdefeirax.ViewModel.LoginViewModel
import com.branchh.afimdefeirax.ViewModel.MainViewModel
import com.branchh.afimdefeirax.ViewModel.MapaFeirasViewModel
import com.branchh.afimdefeirax.ViewModel.ProdutosViewModel
import com.google.android.gms.location.LocationServices
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{


     single{ FeirasRepositoryImpl() }
     single{ HistoricoRepository(androidContext()) }
     single {LocationServices.getFusedLocationProviderClient(androidContext()) }
     single { LocationImpl(get(), androidContext()) }
     single { FocusCamera() }
     single { LoginSharedImpl(androidContext()) }
     single { FirebaseAuth.getInstance() }
     factory { FirebaseAuthServiceImpl(get()) }
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


