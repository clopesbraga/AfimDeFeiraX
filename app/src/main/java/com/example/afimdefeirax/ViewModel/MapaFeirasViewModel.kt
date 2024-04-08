package com.example.afimdefeirax.ViewModel

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.example.afimdefeirax.Model.FeirasModel
import com.example.afimdefeirax.R
import com.example.afimdefeirax.Utils.DeviceCurrentTime
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MapaFeirasViewModel : ViewModel() {


    private val diasemana = DeviceCurrentTime()
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var database: DatabaseReference = let{
        Firebase.database.reference.child("Pesquisa").child("Feiras")
    }



   fun showMyLocalization(mapa: GoogleMap, ctx: Context) {

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(ctx)

        when {
            ContextCompat.checkSelfPermission(
                ctx,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {

                fusedLocationClient.lastLocation.addOnSuccessListener {

                    val latitude = it.latitude
                    val longitude = it.longitude
                    val userlocalization = LatLng(latitude, longitude)

                    val cameraPosition = CameraPosition.Builder()
                        .target(userlocalization)
                        .zoom(13f)
                        .build()

                    mapa.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
                    mapa.addMarker(MarkerOptions().position(userlocalization).title("Marker"))
                }
            }

            else -> ActivityCompat.requestPermissions(
                ctx as Activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 200
            )


        }

    }

    fun showFeirasLocalization(mapa: GoogleMap,ctx:Context) {

        val referenceListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (feirasnapshot in snapshot.children) {

                    val feiras = feirasnapshot.getValue(FeirasModel::class.java)
                    feiras?.let {
                        if(diasemana.trazSemana() == it.dia){

                            val latLng = LatLng(it.Latitude.toDouble(), it.Longitude.toDouble())
                            mapa.addMarker(
                                MarkerOptions()
                                    .position(latLng)
                                    .title(it.Feira)
                                    .snippet(it.endereco)
                                    .icon(
                                        BitmapDescriptorFactory.fromResource(R.mipmap.ic_loc)
                                    )
                            )
                        }else{
                                Toast.makeText(ctx,R.string.feira_not_found, Toast.LENGTH_LONG).show();
                        }

                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("FIREBASE", "Failed to read", error.toException())
            }
        }

        database.addListenerForSingleValueEvent(referenceListener)
    }

}