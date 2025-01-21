package com.example.afimdefeirax.Utils.Location

import com.google.android.gms.maps.model.LatLng

interface ILocation {
    fun getLastLocation(callback: (LatLng?) -> Unit)

}