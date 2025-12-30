package com.branchh.afimdefeirax.View.Screens


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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.branchh.afimdefeirax.R
import com.branchh.afimdefeirax.View.Components.MessageNoPrice
import com.branchh.afimdefeirax.View.Components.SeletorPrecoComponent
import com.branchh.afimdefeirax.ViewModel.ProdutosViewModel
import org.koin.compose.koinInject
import kotlin.collections.set

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProdutosListScreen(navController: NavHostController, showBottomBar: (Boolean) -> Unit) {

    showBottomBar(false)
    val viewModel: ProdutosViewModel = koinInject()
    val state by viewModel.state.collectAsState()

    val loadedItems = viewModel.loadProducts()
    var showDialog by remember { mutableStateOf(false) }

    val valorTotal by remember {
        derivedStateOf {
            state.totalSum + loadedItems
                .sumOf { item -> viewModel.respostaPreco[item.itemName]?.toIntOrNull() ?: 0 }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.resetTotalSum()
    }

    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Column {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = stringResource(R.string.my_list_products),
                                color = Color.White
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = stringResource(R.string.my_value_total),
                                color = Color.White
                            )
                            val totalFormatado = "%.2f".format(valorTotal / 100.0).replace('.', ',')
                            Text(text = ": R$ $totalFormatado", color = Color.White)
                        }
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
            items(loadedItems, key = { item -> item.itemName }) { item ->

                var currentResposta by remember { mutableStateOf("0") }

                AnimatedVisibility(
                    visible = viewModel.visibleStates[item.itemName] ?: true,
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
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = item.itemName,
                                        fontSize = 40.sp,
                                        modifier = Modifier.padding(16.dp)
                                    )
                                    Spacer(modifier = Modifier.size(8.dp))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceEvenly
                                    ) {
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
                                        val itemValueConfirmed = currentResposta.toIntOrNull() ?: 0
                                        if (currentResposta == "0") {
                                            showDialog = true
                                            return@Button
                                        }

                                        viewModel.requestOfHistorico(
                                            item.itemName,
                                            currentResposta,
                                            item.imageName
                                        )

                                        viewModel.sumOfTotal(itemValueConfirmed)
                                        viewModel.visibleStates[item.itemName] = false
                                        viewModel.removeProduct(item)

                                    }
                                ) {
                                    Text(stringResource(R.string.confirm_item_in_list))
                                }

                                Spacer(modifier = Modifier.size(32.dp))
                                SeletorPrecoComponent(
                                    onValueChange = { novoValor ->
                                        currentResposta = novoValor.toString()
                                        viewModel.respostaPreco[item.itemName] = novoValor.toString()
                                    }
                                )

                            }
                        }

                    }
                }
            }
        }

    }
    MessageNoPrice(showDialog) { showDialog = false }
}



