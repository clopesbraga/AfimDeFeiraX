package com.branchh.afimdefeirax.View.Screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.branchh.afimdefeirax.View.Components.getAllProductsList
import com.branchh.afimdefeirax.R
import com.branchh.afimdefeirax.Utils.FirebaseAnalytics.FirebaseAnalyticsImpl
import com.branchh.afimdefeirax.Utils.Monitoring
import com.branchh.afimdefeirax.View.Components.TutorialShowCaseComponent
import com.branchh.afimdefeirax.ViewModel.ProdutosViewModel
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProdutosScreen(
    navController: NavHostController,
    showBottomBar: (Boolean) -> Unit,
) {


    val viewModel: ProdutosViewModel = koinInject()
    val firebaseanalytics: FirebaseAnalyticsImpl = koinInject()
    val state by viewModel.state.collectAsState()
    val produtosList = getAllProductsList()

    firebaseanalytics.firebaselogEvent(Monitoring.Product.PRODUCT_SCREEN_START)
    showBottomBar(true)


    LaunchedEffect(Unit) {
        viewModel.onShowTutorial()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(containerColor = Color(0xFF009688)),
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = stringResource(R.string.list_of_products), color = Color.White)
                    }
                },
            )
        },

        floatingActionButton = {

            TutorialShowCaseComponent(
                targetIndex = 0,
                showintro = state.showTutorial,
                title = stringResource(R.string.tutorial_description_button_product_list),
                description = stringResource(R.string.tutorial_descritpiton_product_list),
                onTutorialCompleted = { viewModel.onTutorialCompleted() }
            ) {

                FloatingActionButton(
                    onClick = {
                        firebaseanalytics.firebaselogEvent(
                            Monitoring.Product.PRODUCT_FLOATING_BUTTON_PRESSED
                        )
                        navController.navigate("list")
                    },
                    containerColor = Color(0xFF009688),
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_nav_btn_produtos),
                        contentDescription = stringResource(R.string.describe_button_list),
                        tint = Color.White
                    )
                }


            }

        }

    ) { innerpadding ->

        LazyColumn(
            modifier = Modifier
                .background(Color(0xFF009688))
                .padding(innerpadding)
                .fillMaxSize()

        ) {
            items(produtosList.size) { produtos ->
                var focusedStates = remember {
                    mutableStateListOf(*List(produtosList[produtos].size) {
                        true
                    }.toTypedArray())
                }
                Column {
                    Text(
                        text = produtosList[produtos][0].categoria,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.padding(12.dp)
                    )
                    LazyRow(Modifier.padding(8.dp)) {
                        items(produtosList[produtos].size) { items ->

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Spacer(Modifier.size(12.dp))
                                Text(
                                    text=produtosList[produtos][items].name,
                                    color = Color.Yellow,
                                    fontStyle = FontStyle.Italic,
                                    fontFamily = FontFamily.SansSerif,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.ExtraBold

                                )
                                Spacer(Modifier.size(8.dp))
                                Card(
                                    modifier = Modifier
                                        .padding(8.dp,8.dp,8.dp,24.dp)
                                        .size(100.dp)
                                        .clickable {

                                            when (focusedStates[items]) {

                                                true -> {
                                                    viewModel.takeItems(
                                                        produtosList[produtos][items].imageResId,
                                                        produtosList[produtos][items].name
                                                    )
                                                    focusedStates[items] = !focusedStates[items]
                                                }

                                                false -> {
                                                    viewModel.removeItems(
                                                        produtosList[produtos][items].imageResId,
                                                        produtosList[produtos][items].name
                                                    )
                                                    focusedStates[items] = !focusedStates[items]
                                                }
                                            }

                                        }
                                        .border(
                                            width = if (!focusedStates[items]) 5.dp else 0.dp,
                                            color = if (!focusedStates[items]) Color.Green else Color.Transparent,
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                ) {
                                    Image(
                                        painter = painterResource(
                                            id = produtosList[produtos][items].imageResId
                                        ),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(100.dp)
                                            .clip(CircleShape)
                                            .alpha(if (focusedStates[items]) 1f else 0.5f)
                                    )

                                }
                            }
                            Spacer(Modifier.size(8.dp))
                        }

                    }

                }
            }


        }

    }

}

