package com.example.afimdefeirax.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.afimdefeirax.Model.FeirasModel
import com.example.afimdefeirax.R
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


class MapaFeirasViewModel: ViewModel() {


    private var database: DatabaseReference


    init {
        database =Firebase.database.reference.child("Pesquisa").child("Feiras")
    }
     fun showMyLocalization(mapa:GoogleMap){

         val latitude = -23.4976431
         val longitude = -46.5505222
         val location = LatLng(latitude, longitude)

         val cameraPosition = CameraPosition.Builder()
             .target(location)
             .zoom(13f)
             .build()

         mapa.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
         mapa.addMarker(MarkerOptions().position(location).title("Marker"))

    }

     fun showFeirasLocalization(mapa:GoogleMap){

         val referenceListener= object : ValueEventListener {
             override fun onDataChange(snapshot: DataSnapshot) {

                 for(feirasnapshot in snapshot.children){

                     val feiras = feirasnapshot.getValue(FeirasModel::class.java)
                     feiras?.let{

                         val latLng = LatLng(it.Latitude.toDouble(), it.Longitude.toDouble())

                         mapa.addMarker(MarkerOptions()
                             .position(latLng)
                             .title(it.Feira)
                             .snippet(it.endereco)
                             .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_loc)
                         ))

                     }

                 }
             }

             override fun onCancelled(error: DatabaseError) {
                 Log.d("FIREBASE","Failed to read",error.toException())
             }
         }

         database.addListenerForSingleValueEvent(referenceListener)
     }

}