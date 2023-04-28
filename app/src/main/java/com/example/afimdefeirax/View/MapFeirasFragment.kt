package com.example.afimdefeirax.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.afimdefeirax.ViewModel.MapaFeirasViewModel
import com.example.afimdefeirax.databinding.FragmentMapFeirasBinding

class MapFeirasFragment : Fragment() {

    private var _binding: FragmentMapFeirasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mapFeirasViewModel =
            ViewModelProvider(this).get(MapaFeirasViewModel::class.java)

        _binding = FragmentMapFeirasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        mapFeirasViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}