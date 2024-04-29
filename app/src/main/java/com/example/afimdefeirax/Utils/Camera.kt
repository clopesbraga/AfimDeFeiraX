package com.example.afimdefeirax.Utils

import android.location.Address
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
class Camera {

    fun focusCamera(endereco: Address):CameraPosition{

        val localization = LatLng(endereco.latitude, endereco.longitude)

       return cameraPosition(localization)
    }

    fun focusCamera(localization : LatLng, map: GoogleMap){

        map.animateCamera(CameraUpdateFactory.
        newCameraPosition(cameraPosition(localization)))
    }

    fun focusCamera(localization : LatLng):CameraPosition{

        return cameraPosition(localization)
    }

    private fun cameraPosition(position:LatLng):CameraPosition{

        val cameraPosition = CameraPosition.Builder()
            .target(position)
            .zoom(13f)
            .build()

        return cameraPosition
    }

}

