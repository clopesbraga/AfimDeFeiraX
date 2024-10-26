package com.example.afimdefeirax.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

        binding.composeViewHistorico.setContent {


            ListHistorico(viewModel)

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListHistorico(viewModel: HistoricoViewModel) {

    val loadedhistorico = viewModel.loadHistorico()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color(0xFF009688)
                ),
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Historico", color = Color.White)
                    }
                },
            )
        }
    ){innerpading ->

        LazyColumn(
            modifier = Modifier
                .background(Color(0xFF009688))
                .padding(innerpading)
                .fillMaxSize()
        ) {


            items(loadedhistorico) { item ->

                Card (modifier = Modifier.padding(8.dp)){
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                    )
                    {

                        Image(
                            painter = painterResource(id = item.imagem),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(18.dp)
                                .size(100.dp)
                                .clip(CircleShape)
                        )
                        Column (
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                        ){
                            Text(text = item.nome, color = Color.Black, fontStyle = FontStyle.Italic, fontSize =20.sp)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("R$ ${item.preco1}")
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("R$ ${item.preco2}")
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("R$ ${item.preco3}")
                        }

                    }
                }

            }
        }


    }


}