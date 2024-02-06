package com.example.afimdefeirax.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.afimdefeirax.View.Adapter.ProdutosAdapter
import com.example.afimdefeirax.ViewModel.ListaViewModel
import com.example.afimdefeirax.databinding.FragmentListaBinding


class ListaFragment : Fragment() {

    private var _binding: FragmentListaBinding?=null
    private val binding  get()= _binding!!
    private lateinit var viewModel : ListaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel =  ViewModelProvider(this).get(ListaViewModel::class.java)
        _binding = FragmentListaBinding.inflate(inflater,container,false)

        //CHAMA RECYCLERVIEW DOS PRODUTOS
        binding.ltvprodutos.layoutManager = LinearLayoutManager(context)

        //CHAMA ADAPTER DOS PRODUTOS
        binding.ltvprodutos.adapter= ProdutosAdapter()

        return binding.root
    }

}