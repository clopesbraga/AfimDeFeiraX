package com.example.afimdefeirax.View.Screens


import android.R.attr.onClick
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CheckboxDefaults.colors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
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
import androidx.navigation.NavHostController
import com.example.afimdefeirax.View.Components.ButtonMeasureComponent
import com.example.afimdefeirax.View.Components.MessageNoPrice
import com.example.afimdefeirax.View.Components.SeletorPesoComponent
import com.example.afimdefeirax.View.Components.SeletorPrecoComponent
import com.example.afimdefeirax.ViewModel.ProdutosViewModel
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProdutosListScreen(navController: NavHostController, showBottomBar: (Boolean) -> Unit) {

    showBottomBar(false)
    val viewModel: ProdutosViewModel = koinInject()
    var selectedNumber by remember { mutableStateOf(1) }

    var respostaPreco = remember {
        mutableStateListOf(*List(viewModel.loadProducts().size) { "" }.toTypedArray())
    }

    var respostaPeso by remember { mutableStateOf("") }
    val visibleStates = remember {
        mutableStateListOf(
            *List(viewModel.loadProducts().size) { true }.toTypedArray()
        )
    }
    var showDialog by remember { mutableStateOf(false) }

    val loadedItems = viewModel.loadProducts()
    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Efetivar Compras", color = Color.White)
                    }
                },
                navigationIcon = {
                    IconButton(

                        onClick = {
                            navController.navigate("comp")
                        }) {
                        Icon(Icons.Filled.ArrowBackIosNew, "Back", tint = Color.White)
                    }
                    colors(Color.White)
                },
                colors = topAppBarColors(
                    containerColor = Color(0xFF009688)
                ),

                )
        }

    ) { innerpading ->

        LazyColumn(contentPadding = innerpading) {
            items(loadedItems) { item ->

                var currentResposta by remember { mutableStateOf("") }
                var selectedButton by remember { mutableStateOf("") }

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
                                    .padding(8.dp)
                                    .fillMaxWidth(),
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
                                            respostaPeso = novoValor.toString()
                                        }
                                    )
                                    Spacer(modifier = Modifier.size(8.dp))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceEvenly
                                    ) {
                                        Column {
                                            listOf("KG", "LT", "PC", "UN").forEach { unity ->
                                                ButtonMeasureComponent(
                                                    unidade = unity,
                                                    selectedMeasure = selectedButton,
                                                    onSelected = { selectedButton = it },
                                                    modifier = Modifier.fillMaxWidth()
                                                )

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
                            Row {

                                Button(
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF009688)
                                    ),
                                    shape = RoundedCornerShape(8.dp),
                                    onClick = {
                                        if (currentResposta.equals("0")) {
                                            showDialog = true
                                            return@Button
                                        }
                                        respostaPreco[loadedItems.indexOf(item)] = currentResposta
                                        viewModel.requestOfHistorico(
                                            item.itemName,
                                            currentResposta,
                                            item.imageName
                                        )
                                        viewModel.removeProduct(loadedItems[loadedItems.indexOf(item)])
                                        visibleStates[loadedItems.indexOf(item)] = false

                                    }
                                ) {
                                    Text("Efetivar")
                                }

                                Spacer(modifier = Modifier.size(32.dp))
                                SeletorPrecoComponent(
                                    onValueChange = { novoValor ->
                                        selectedNumber = novoValor
                                        currentResposta = novoValor.toString()
                                        respostaPreco[loadedItems.indexOf(item)] = novoValor.toString()
                                    }
                                )

                            }
                        }

                    }
                }
            }
        }

    }
    MessageNoPrice(showDialog){showDialog =false}
}



