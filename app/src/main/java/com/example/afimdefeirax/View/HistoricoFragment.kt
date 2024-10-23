package com.example.afimdefeirax.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.fragment.app.Fragment
import com.example.afimdefeirax.ViewModel.HistoricoViewModel
import com.example.afimdefeirax.databinding.FragmentHistoricoBinding
import org.koin.android.ext.android.inject


class HistoricoFragment : Fragment() {

    private val viewModel: HistoricoViewModel by inject()
    private var _binding: FragmentHistoricoBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHistoricoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.composeViewHistorico.setContent{


            listHistorico()

        }

        return root
    }


    @Composable
    fun listHistorico(){

        val loadedhistorico = viewModel.loadHistorico()

        LazyColumn{
            items(loadedhistorico){item->
                 Card{
                     Row{
                         item.imagem
                         item.preco
                     }

                 }
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}