package com.example.afimdefeirax.Utils

import com.google.android.gms.maps.model.LatLng

interface ILocation {
    fun getLastLocation(callback: (LatLng?) -> Unit)

}