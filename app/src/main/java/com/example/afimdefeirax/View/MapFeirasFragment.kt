package com.example.afimdefeirax.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.afimdefeirax.R
import com.example.afimdefeirax.ViewModel.MapaFeirasViewModel
import com.example.afimdefeirax.databinding.FragmentMapFeirasBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import org.koin.android.ext.android.inject


class MapFeirasFragment : Fragment(),OnMapReadyCallback {

    private var _binding: FragmentMapFeirasBinding? = null
    private val binding get() = _binding!!
    private val viewModel : MapaFeirasViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMapFeirasBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(map: GoogleMap) = map.let {
        viewModel.showMyLocalization(it)
        viewModel.showFeirasLocalization(it)
    }
}