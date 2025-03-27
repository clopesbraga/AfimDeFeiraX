package com.example.afimdefeirax.Utils.Location


import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng

class LocationImpl(private val fusedLocationClient: FusedLocationProviderClient) : ILocation {

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