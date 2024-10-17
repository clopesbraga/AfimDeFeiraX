package com.example.afimdefeirax.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.afimdefeirax.databinding.FragmentHistoricoBinding


class HistoricoFragment : Fragment() {

    private var _binding: FragmentHistoricoBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHistoricoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.composeViewHistorico.setContent {



        }

        return root
    }







    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}