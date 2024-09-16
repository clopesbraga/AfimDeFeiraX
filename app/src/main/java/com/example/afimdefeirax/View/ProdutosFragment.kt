package com.example.afimdefeirax.View


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.example.afimdefeirax.Model.produtosList
import com.example.afimdefeirax.ViewModel.ComprasViewModel
import com.example.afimdefeirax.databinding.FragmentComprasBinding




class ProdutosFragment : Fragment(){

    private lateinit var _viewModel: ComprasViewModel
    private var _binding: FragmentComprasBinding? = null
    val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentComprasBinding.inflate(inflater, container, false)

        binding.composeView.setContent {

            ListProdutos()
        }

        return binding.root
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ListProdutos() {

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
                            Text(text = "Compras", color = Color.White)
                        }
                    },
                )
            }
        ) { innerpadding ->

            LazyColumn(
                modifier = Modifier
                    .background(Color(0xFF009688))
                    .padding(innerpadding)
                    .fillMaxSize()

            ) {
                items(produtosList.size) { produtos ->
                    Column {
                        Text(
                            text = produtosList[produtos][0].name,
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline
                        )
                        LazyRow {
                            items(produtosList[produtos].size) { items ->
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ){
                                    Text(produtosList[produtos][items].name)
                                    Card(
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .size(100.dp)
                                            .clickable {  }
                                    ){
                                        Image(
                                            painter = painterResource(
                                                id = produtosList[produtos][items].imageResId),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .size(100.dp)
                                                .clip(CircleShape)
                                        )

                                    }
                                }

                            }

                        }

                    }
                }

            }

        }

    }

}
