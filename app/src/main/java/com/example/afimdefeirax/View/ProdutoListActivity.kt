package com.example.afimdefeirax.View

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.afimdefeirax.View.Components.SeletorPesoComponent
import com.example.afimdefeirax.View.Components.SeletorPrecoComponent
import com.example.afimdefeirax.ViewModel.ProdutosViewModel
import com.example.afimdefeirax.databinding.ActivityProdutoListBinding
import org.koin.android.ext.android.inject

class ProdutoListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProdutoListBinding


    private val viewModel: ProdutosViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProdutoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Lista de Compras"

        binding.composeViewProdutoList.setContent {

            ListProdutos(viewModel)

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    @Composable
    fun ListProdutos(viewModel: ProdutosViewModel) {

        var selectedNumber by remember { mutableStateOf(1) }
        val resposta = remember { mutableStateOf("") }
        val visibleStates = remember {
            mutableStateListOf<Boolean>(
                *List(viewModel.loadProducts().size) { true }.toTypedArray()
            )
        }



        val loadedItems = viewModel.loadProducts()


        LazyColumn {
            items(loadedItems) { item ->

                var selecionadoBotaoKG by remember { mutableStateOf(false) }
                var selecionadoBotaoLT by remember { mutableStateOf(false) }
                var selecionadoBotaoPC by remember { mutableStateOf(false) }
                var selecionadoBotaoUN by remember { mutableStateOf(false) }
                AnimatedVisibility(
                    visible = visibleStates[loadedItems.indexOf(item)],
                    exit = fadeOut(animationSpec = tween(durationMillis = 500))
                ) {

                    Card(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Image(
                                painter = painterResource(id = item.imageName),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .size(100.dp)
                                    .clip(CircleShape)

                            )
                            Column(
                                modifier = Modifier
                                    .padding(8.dp),
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(text = item.itemName)
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    SeletorPesoComponent(
                                        onValueChange = { novoValor ->
                                            selectedNumber = novoValor
                                            resposta.value = novoValor.toString()
                                        }
                                    )
                                    Spacer(modifier = Modifier.size(8.dp))
                                    Row(horizontalArrangement = Arrangement.End) {
                                        Column {

                                            OutlinedButton(
                                                shape = RoundedCornerShape(8.dp),
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = if (selecionadoBotaoKG) Color.Green else Color.Transparent
                                                ),
                                                onClick = {
                                                    selecionadoBotaoKG = !selecionadoBotaoKG
                                                    selecionadoBotaoLT = false
                                                    selecionadoBotaoPC = false
                                                    selecionadoBotaoUN = false
                                                }
                                            ) {
                                                Text("KG", color = Color.Black)
                                            }

                                            OutlinedButton(
                                                shape = RoundedCornerShape(8.dp),
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = if (selecionadoBotaoLT) Color.Green else Color.Transparent
                                                ),
                                                onClick = {
                                                    selecionadoBotaoLT = !selecionadoBotaoLT
                                                    selecionadoBotaoKG = false
                                                    selecionadoBotaoPC = false
                                                    selecionadoBotaoUN = false
                                                }
                                            ) {
                                                Text("LT", color = Color.Black)
                                            }
                                        }

                                        Column {

                                            OutlinedButton(
                                                shape = RoundedCornerShape(8.dp),
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = if (selecionadoBotaoPC) Color.Green else Color.Transparent
                                                ),
                                                onClick = {
                                                    selecionadoBotaoPC = !selecionadoBotaoPC
                                                    selecionadoBotaoLT = false
                                                    selecionadoBotaoKG = false
                                                    selecionadoBotaoUN = false
                                                }
                                            ) {
                                                Text("PC", color = Color.Black)
                                            }

                                            OutlinedButton(
                                                shape = RoundedCornerShape(8.dp),
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = if (selecionadoBotaoUN) Color.Green else Color.Transparent
                                                ),
                                                onClick = {
                                                    selecionadoBotaoUN = !selecionadoBotaoUN
                                                    selecionadoBotaoKG = false
                                                    selecionadoBotaoPC = false
                                                    selecionadoBotaoLT = false
                                                }
                                            ) {
                                                Text("UN", color = Color.Black)
                                            }

                                        }

                                    }
                                }
                            }

                        }

                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Row{

                                Button(
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF009688)
                                    ),
                                    shape = RoundedCornerShape(8.dp),
                                    onClick = {

                                        viewModel.removeProduct(loadedItems[loadedItems.indexOf(item)])
                                        visibleStates[loadedItems.indexOf(item)] = false
                                    }
                                ) {
                                    Text("Comprar")
                                }

                                Spacer(modifier = Modifier.size(32.dp))
                                SeletorPrecoComponent(
                                    onValueChange = { novoValor ->
                                        selectedNumber = novoValor
                                        resposta.value = novoValor.toString()
                                    }
                                )

                            }
                        }

                    }
                }
            }


        }
    }
}

