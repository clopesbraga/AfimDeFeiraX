package com.example.afimdefeirax.ViewModel

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afimdefeirax.R
import com.example.afimdefeirax.Repository.FeiraRepository.FeirasRepositoryImpl
import com.example.afimdefeirax.Utils.FocusCamera
import com.example.afimdefeirax.Utils.LocationImpl
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import java.io.IOException
import java.util.Objects


class MapaFeirasViewModel(application: Application) : ViewModel() {

    private val application: Application = application
    private lateinit var userLocation: LatLng
    private val camera : FocusCamera by inject(FocusCamera::class.java)
    private val locationProvider : LocationImpl by inject(LocationImpl::class.java)
    private val feirasRepository : FeirasRepositoryImpl by inject(FeirasRepositoryImpl::class.java)



    fun showMyLocalizationIn(map: GoogleMap) {

        viewModelScope.launch {

            when {
                ContextCompat.checkSelfPermission(
                    application.applicationContext,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED -> {

                    locationProvider.getLastLocation { location ->
                        location?.let {

                            userLocation = LatLng(it.latitude, it.longitude)
                            camera.focusCamera(userLocation, map)
                            map.addMarker(MarkerOptions().position(userLocation).title("Usuario"))
                        }
                    }
                }
                else -> ActivityCompat.requestPermissions(
                    application.applicationContext as Activity,
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ),
                    200
                )
            }
        }
    }

     fun showFeirasLocalizationIn(map: GoogleMap) {
        feirasRepository.getFeirasLocations { feiras ->
            feiras.forEach { feira ->
                val latLng = LatLng(feira.Latitude.toDouble(), feira.Longitude.toDouble())
                map.addMarker(
                    MarkerOptions()
                        .position(latLng)
                        .title(feira.Feira)
                        .snippet("${feira.endereco},${feira.bairro}")
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_loc))
                )
            }
        }
    }

    fun focusUserCamera(map: GoogleMap){
        camera.focusCamera(userLocation,map)
    }

     fun geoLocalization(cidade: String, bairro: String, map: GoogleMap) {

        val local = "$cidade,$bairro"
        val gc = Geocoder(application.applicationContext)
        var list: List<Address?>? = null
        try {
            list = gc.getFromLocationName(local, 1)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val localization = Objects.requireNonNull<List<Address>>(list as List<Address>?)[0]

        map.animateCamera(
            CameraUpdateFactory.newCameraPosition(camera.focusCamera(localization))
        )
    }

}