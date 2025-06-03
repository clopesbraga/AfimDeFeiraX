package com.example.afimdefeirax.View.Screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.afimdefeirax.R
import com.example.afimdefeirax.Utils.FirebaseAnalytics.FirebaseAnalyticsImpl
import com.example.afimdefeirax.Utils.Monitoring
import com.example.afimdefeirax.View.Components.ColunaDinamica
import com.example.afimdefeirax.View.Components.TutorialShowCaseComponent
import com.example.afimdefeirax.ViewModel.HistoricoViewModel
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoricoScreen(showBottomBar: (Boolean)->Unit) {


    showBottomBar(true)
    val firebaseanalytics: FirebaseAnalyticsImpl = koinInject()
    val viewModel : HistoricoViewModel =koinInject()
    val loadedhistorico = viewModel.loadHistorico()

    firebaseanalytics.firebaselogEvent(Monitoring.Historic.HISTORIC_SCREEN_START)

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
    ) { innerpading ->

        LazyColumn(
            modifier = Modifier
                .background(Color(0xFF009688))
                .padding(innerpading)
                .fillMaxSize()
        ) {

            itemsIndexed (loadedhistorico) { index,item ->

                val isFirstItemAndTutorialActive = if(index == 0)true else false

                Card(modifier = Modifier.padding(8.dp)) {
                    Row(
                        modifier = Modifier
                            .padding(2.dp)
                    )
                    {
                        Image(
                            painter = painterResource(id = item.imagem),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(2.dp)
                                .size(80.dp)
                                .clip(CircleShape)
                        )
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(2.dp)
                                .fillMaxWidth()
                        ) {

                            Text(
                                text = item.nome,
                                color = Color.Black,
                                fontStyle = FontStyle.Italic,
                                fontSize = 25.sp
                            )
                            TutorialShowCaseComponent(
                                targetIndex = 0,
                                showintro = isFirstItemAndTutorialActive,
                                title = stringResource(R.string.tutorial_title_item_historic),
                                description = stringResource(R.string.tutorial_description_item_historic),
                                onTutorialCompleted = true

                            ) {
                                Row {
                                    if (item.preco2 != null && item.preco3 != null) {
                                        ColunaDinamica(
                                            preco1 = item.preco1!!.toDouble(),
                                            preco2 = item.preco2!!.toDouble(),
                                            preco3 = item.preco3!!.toDouble()
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