package com.branchh.afimdefeirax.View.Screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.branchh.afimdefeirax.R
import com.branchh.afimdefeirax.Utils.FirebaseAnalytics.FirebaseAnalyticsImpl
import com.branchh.afimdefeirax.Utils.Monitoring
import com.branchh.afimdefeirax.View.Components.ColunaDinamica
import com.branchh.afimdefeirax.View.Components.TutorialShowCaseComponent
import com.branchh.afimdefeirax.ViewModel.HistoricoViewModel
import com.canopas.lib.showcase.IntroShowcase
import com.canopas.lib.showcase.component.ShowcaseStyle
import com.canopas.lib.showcase.component.rememberIntroShowcaseState
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoricoScreen(showBottomBar: (Boolean) -> Unit, showTutorial: Boolean) {


    showBottomBar(true)
    val firebaseanalytics: FirebaseAnalyticsImpl = koinInject()
    val viewModel: HistoricoViewModel = koinInject()
    val loadedhistorico = viewModel.loadHistorico()
    val state by viewModel.state.collectAsState()
    val introShowcaseState = rememberIntroShowcaseState()


    firebaseanalytics.firebaselogEvent(Monitoring.Historic.HISTORIC_SCREEN_START)

    IntroShowcase(
        showIntroShowCase = state.showTutorial,
        state = introShowcaseState,
        onShowCaseCompleted = { viewModel.onTutorialCompleted() }

    ) {

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
                            Text(
                                text = stringResource(R.string.title_historic),
                                color = Color.White
                            )
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

                itemsIndexed(loadedhistorico) { index, item ->

                    LaunchedEffect(Unit) {
                        viewModel.onShowTutorial()
                    }

                    val isFirstItemAndTutorialActive = index == 0 && state.showTutorial

                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .then(
                                if (isFirstItemAndTutorialActive) {
                                    Modifier.introShowCaseTarget(
                                        index = 0,
                                        style = ShowcaseStyle.Default.copy(
                                            backgroundColor = Color(0xFF009688),
                                            backgroundAlpha = 0.98f,
                                            targetCircleColor = Color.White
                                        ),
                                        content = {
                                            Column {
                                                Text(
                                                    text = stringResource(R.string.tutorial_title_item_historic),
                                                    color = Color.White,
                                                    fontSize = 24.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                                Spacer(modifier = Modifier.height(4.dp))
                                                Text(
                                                    text = stringResource(R.string.tutorial_description_item_historic),
                                                    color = Color.White,
                                                    fontSize = 16.sp
                                                )
                                                Spacer(modifier = Modifier.height(10.dp))
                                            }
                                        }
                                    )
                                } else Modifier
                            )

                    ) {
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