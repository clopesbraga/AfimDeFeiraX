package com.example.afimdefeirax.View

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.afimdefeirax.Model.FeirasModel
import com.example.afimdefeirax.R
import com.example.afimdefeirax.Utils.DeviceCurrentTime
import com.example.afimdefeirax.databinding.FragmentMapFeirasBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
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


class MapFeirasFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentMapFeirasBinding? = null
    private val binding get() = _binding!!
//    private val viewModel: MapaFeirasViewModel by inject()
    private val diasemana = DeviceCurrentTime()
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var userlocalization: LatLng
    private var database: DatabaseReference = let{
        Firebase.database.reference.child("Pesquisa").child("Feiras")
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMapFeirasBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(binding.root.context)

    }

    override fun onResume() {
        super.onResume()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(map: GoogleMap): Unit = map.let {

        showMyLocalizationIn(map)
        showFeirasLocalizationIn(map)

    }

    private fun showMyLocalizationIn(map: GoogleMap) {
        when {
            ContextCompat.checkSelfPermission(
                binding.root.context as Activity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {

                fusedLocationClient.lastLocation.addOnSuccessListener {

                    if (it != null) {

                        val latitude = it.latitude
                        val longitude = it.longitude
                        userlocalization = LatLng(latitude, longitude)

                        val cameraPosition = CameraPosition.Builder()
                            .target(userlocalization)
                            .zoom(13f)
                            .build()

                        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
                        map.addMarker(MarkerOptions().position(userlocalization).title("Marker"))
                    }

                    binding.fabLocalization.setOnClickListener{

                        val cameraPosition = CameraPosition.Builder()
                            .target(userlocalization)
                            .zoom(13f)
                            .build()

                        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

                    }

                }

            }

            else -> ActivityCompat.requestPermissions(
                binding.root.context as Activity,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                200
            )
        }
    }

    private fun showFeirasLocalizationIn(map: GoogleMap){
        val referenceListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (feirasnapshot in snapshot.children) {

                    val feiras = feirasnapshot.getValue(FeirasModel::class.java)
                    feiras?.let {
                        if(diasemana.trazSemana() == it.dia){

                            val latLng = LatLng(it.Latitude.toDouble(), it.Longitude.toDouble())
                            map.addMarker(
                                MarkerOptions()
                                    .position(latLng)
                                    .title("FEIRA ${it.Feira}")
                                    .snippet(it.bairro)
                                    .contentDescription(it.endereco)
                                    .icon(
                                        BitmapDescriptorFactory.fromResource(R.mipmap.ic_loc)
                                    )
                            )
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