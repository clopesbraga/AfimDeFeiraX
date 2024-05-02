package com.example.afimdefeirax.Utils


import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng

class LocationImpl(private val fusedLocationClient: FusedLocationProviderClient,ctx : Application) : ILocation {

    val context = ctx
    override fun getLastLocation(callback: (LatLng?) -> Unit) {

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                callback.invoke(LatLng(location.latitude, location.longitude))
            } else {
                callback.invoke(null)
            }
        }
    }
}