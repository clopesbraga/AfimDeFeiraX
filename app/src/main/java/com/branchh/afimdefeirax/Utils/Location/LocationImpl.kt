package com.branchh.afimdefeirax.Utils.Location


import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient


class LocationImpl(
    private val fusedLocationClient: FusedLocationProviderClient,
    private val context: Context
) : ILocation {

    @SuppressLint("MissingPermission")
    override fun getLastLocation(callback: (Location?) -> Unit) {

        val hasPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (hasPermission) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    callback.invoke(location)
                }
                .addOnFailureListener {
                    callback(null)
                }

        } else {
            callback(null)
        }
    }
}