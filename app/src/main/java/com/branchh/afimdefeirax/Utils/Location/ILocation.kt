package com.branchh.afimdefeirax.Utils.Location

import android.location.Location
import com.google.android.gms.maps.model.LatLng

interface ILocation {
    fun getLastLocation(callback: (Location?) -> Unit)

}