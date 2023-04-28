package com.example.afimdefeirax.View


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.afimdefeirax.ViewModel.ComprasViewModel

import com.example.afimdefeirax.databinding.FragmentComprasBinding


class ComprasFragment : Fragment() {

    companion object {
        fun newInstance() = ComprasFragment()
    }

    private lateinit var comprasViewModel: ComprasViewModel
    private var _binding: FragmentComprasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentComprasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}