package com.example.afimdefeirax.ViewModel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng

class MapaFeirasViewModel_ref(private val application: Application) : ViewModel() {



    var userlocation = mutableStateOf<LatLng?>(null)




}