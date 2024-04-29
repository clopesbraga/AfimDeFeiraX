package com.example.afimdefeirax.View

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.afimdefeirax.Model.FeirasModel
import com.example.afimdefeirax.R
import com.example.afimdefeirax.Utils.Camera
import com.example.afimdefeirax.Utils.DeviceCurrentTime
import com.example.afimdefeirax.databinding.FragmentMapFeirasBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.io.IOException
import java.util.Objects


class MapFeirasFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentMapFeirasBinding? = null
    private val binding get() = _binding!!

    //    private val viewModel: MapaFeirasViewModel by inject()
    private val diasemana = DeviceCurrentTime()
    private val camera = Camera()

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var userlocalization: LatLng

    private var database: DatabaseReference = let {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(map: GoogleMap): Unit = map.let {

        showMyLocalizationIn(map)
        showFeirasLocalizationIn(map)

        binding.fabSearch.setOnClickListener {

            lateinit var bairrosadapter: ArrayAdapter<*>
            val inflater = requireActivity().layoutInflater
            val mensagem = AlertDialog.Builder(
                activity
            )

            val janelacidadesview =
                inflater.inflate(R.layout.layout_list_cities_ditricts, null) as ViewGroup

            val spinnerselecaodacidade = janelacidadesview.findViewById<Spinner>(R.id.spinnercidade)
            val spinnerselecaodobairro = janelacidadesview.findViewById<Spinner>(R.id.spinnerbairro)


            val cidadesadapter = ArrayAdapter.createFromResource(
                janelacidadesview.context,
                R.array.cidades,
                android.R.layout.simple_spinner_dropdown_item
            )

            spinnerselecaodacidade.adapter=cidadesadapter

            val escolha = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {

                    when (spinnerselecaodacidade.selectedItemPosition) {
                        0 -> {
                            bairrosadapter = ArrayAdapter.createFromResource(
                                view.context,
                                R.array.sp_bairros,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }

                        1 -> {
                            bairrosadapter = ArrayAdapter.createFromResource(
                                view.context,
                                R.array.guaru_bairros,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }

                        2 -> {
                            bairrosadapter = ArrayAdapter.createFromResource(
                                view.context,
                                R.array.suza_bairros,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }

                        3 -> {
                            bairrosadapter = ArrayAdapter.createFromResource(
                                view.context,
                                R.array.osasco_bairros,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }

                        4 -> {
                            bairrosadapter = ArrayAdapter.createFromResource(
                                view.context,
                                R.array.maua_bairros,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }

                        5 -> {
                            bairrosadapter = ArrayAdapter.createFromResource(
                                view.context,
                                R.array.andre_bairros,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }

                        6 -> {
                            bairrosadapter = ArrayAdapter.createFromResource(
                                view.context,
                                R.array.bernado_bairros,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }

                        7 -> {
                            bairrosadapter = ArrayAdapter.createFromResource(
                                view.context,
                                R.array.caetano_bairros,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }

                        else -> {
                            bairrosadapter = ArrayAdapter.createFromResource(
                                view.context,
                                R.array.sp_bairros,
                                android.R.layout.simple_spinner_dropdown_item
                            )
                        }
                    }

                    spinnerselecaodobairro.adapter = bairrosadapter
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Implementação opcional caso deseje fazer algo quando nada for selecionado
                }

            }

            spinnerselecaodacidade.onItemSelectedListener = escolha

            localizationSpinner(
                mensagem,
                janelacidadesview,
                spinnerselecaodacidade,
                spinnerselecaodobairro,
                map
            )


        }

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

                        camera.focusCamera(userlocalization, map)
                        map.addMarker(MarkerOptions().position(userlocalization).title("Marker"))
                    }

                    binding.fabLocalization.setOnClickListener {

                        camera.focusCamera(userlocalization, map)
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

    private fun showFeirasLocalizationIn(map: GoogleMap) {
        val referenceListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (feirasnapshot in snapshot.children) {

                    val feiras = feirasnapshot.getValue(FeirasModel::class.java)
                    feiras?.let {
                        if (diasemana.trazSemana()==it.dia.trim()) {

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

    private fun geoLocalization(cidade: String, bairro: String, map: GoogleMap) {

        val local = "$cidade,$bairro"
        val gc = Geocoder(binding.root.context)
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

    private fun localizationSpinner(
        mensagem: AlertDialog.Builder,
        janelacidadesview: ViewGroup,
        spinnerselecaodacidade: Spinner,
        spinnerselecaodobairro: Spinner,
        map: GoogleMap
    ) {
        mensagem.setTitle("Teste")
        mensagem.setView(janelacidadesview)
        mensagem.setPositiveButton(
            "OK"
        ) { _, _ ->

            val spinnercidade = spinnerselecaodacidade.getSelectedItem().toString()
            val spinnerbairro = spinnerselecaodobairro.getSelectedItem().toString()

            geoLocalization(spinnercidade, spinnerbairro, map)

        }
        mensagem.setNegativeButton("Voltar") { dialog, which -> }
        mensagem.show()
    }

}