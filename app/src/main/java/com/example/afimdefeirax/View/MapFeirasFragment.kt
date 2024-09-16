package com.example.afimdefeirax.View


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.afimdefeirax.R
import com.example.afimdefeirax.ViewModel.MapaFeirasViewModel
import com.example.afimdefeirax.databinding.FragmentMapFeirasBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import org.koin.android.ext.android.inject


class MapFeirasFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentMapFeirasBinding? = null
    private val binding get() = _binding!!

    val viewModel: MapaFeirasViewModel by inject()


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

    }

    override fun onMapReady(map: GoogleMap): Unit = map.let {

        viewModel.showMyLocalizationIn(map)
        viewModel.showFeirasLocalizationIn(map)
        actionFloatButtons(map)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun actionFloatButtons(map: GoogleMap) {

        searchFeirasButton(map)
        localizationUserButton(map)

    }

    private fun localizationUserButton(map: GoogleMap) {
        binding.fabLocalization.setOnClickListener {
            viewModel.focusUserCamera(map)
        }
    }

    private fun searchFeirasButton(map: GoogleMap) {
        binding.fabSearch.setOnClickListener {

            lateinit var bairrosadapter: ArrayAdapter<*>
            val inflater = requireActivity().layoutInflater
            val mensagem = AlertDialog.Builder(activity)

            val janelacidadesview =
                inflater.inflate(R.layout.layout_list_cities_ditricts, null) as ViewGroup

            val spinnerselecaodacidade = janelacidadesview.findViewById<Spinner>(R.id.spinnercidade)
            val spinnerselecaodobairro = janelacidadesview.findViewById<Spinner>(R.id.spinnerbairro)


            val cidadesadapter = ArrayAdapter.createFromResource(
                janelacidadesview.context,
                R.array.cidades,
                android.R.layout.simple_spinner_dropdown_item
            )

            spinnerselecaodacidade.adapter = cidadesadapter

            val districtsarray = arrayOf(

                R.array.sp_bairros,
                R.array.guaru_bairros,
                R.array.suza_bairros,
                R.array.osasco_bairros,
                R.array.maua_bairros,
                R.array.andre_bairros,
                R.array.bernado_bairros,
                R.array.caetano_bairros
            )

            val escolha = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    val cityselection = parent.getItemAtPosition(position) as String
                    val citieslist = when (cityselection) {
                        "Guarulhos" -> 1
                        "Suzano" -> 2
                        "Osasco" -> 3
                        "Mauá" -> 4
                        "Santo André" -> 5
                        "São Bernardo do Campo" -> 6
                        "São Caetano do Sul" -> 7
                        else -> 0
                    }
                    bairrosadapter = ArrayAdapter.createFromResource(
                        view.context,
                        districtsarray[citieslist],
                        android.R.layout.simple_spinner_dropdown_item
                    )

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
            viewModel.geoLocalization(
                spinnerselecaodacidade.getSelectedItem().toString(),
                spinnerselecaodobairro.getSelectedItem().toString(),
                map
            )
        }
        mensagem.setNegativeButton("Voltar") { dialog, which -> }
        mensagem.show()
    }

}