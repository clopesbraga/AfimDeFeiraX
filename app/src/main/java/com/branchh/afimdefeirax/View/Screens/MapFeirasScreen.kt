package com.branchh.afimdefeirax.View.Screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.branchh.afimdefeirax.R
import com.branchh.afimdefeirax.Utils.FirebaseAnalytics.FirebaseAnalyticsImpl
import com.branchh.afimdefeirax.Utils.Monitoring
import com.branchh.afimdefeirax.View.Components.CitiesMenuComponent
import com.branchh.afimdefeirax.View.Components.SearchNeighborHoodComponent
import com.branchh.afimdefeirax.View.Components.TutorialShowCaseComponent
import com.branchh.afimdefeirax.ViewModel.MapaFeirasViewModel
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapEffect
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MapsComposeExperimentalApi
import org.koin.compose.koinInject


@OptIn(MapsComposeExperimentalApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MapFeirasScreen(showBottomBar: (Boolean) -> Unit,showTutorial:Boolean) {


    var shouldShowBottomBar by remember { mutableStateOf(true) }
    SideEffect { showBottomBar(shouldShowBottomBar) }

    val firebaseanalytics: FirebaseAnalyticsImpl = koinInject()
    val viewModel: MapaFeirasViewModel = koinInject()
    val state by viewModel.state.collectAsState()

    val sheetState = rememberModalBottomSheetState()
    val listState = rememberLazyListState()

    val mapProperties = MapProperties(isMyLocationEnabled = true)
    val uiSettings = MapUiSettings(zoomControlsEnabled = false)


    firebaseanalytics.firebaselogEvent(Monitoring.Map.MAP_SCREEN_START)


    LaunchedEffect(Unit) {
            viewModel.onShowTutorial()
    }
        Scaffold(
            Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text("Feiras da cidade", color = Color.White) },
                    colors = TopAppBarDefaults.topAppBarColors(Color(0xFF009688))
                )
            },
            floatingActionButton = {

                TutorialShowCaseComponent(
                    targetIndex = 0,
                    showintro = state.showTutorial,
                    title = stringResource(R.string.tutorial_title_button_search_neigboor),
                    description = stringResource(R.string.tutorial_description_button_search_neigboor),
                    onTutorialCompleted = {viewModel.onTutorialCompleted()}
                ){
                    FloatingActionButton(
                        onClick = {
                            firebaseanalytics.firebaselogEvent(Monitoring.Map.MAP_FLOATING_BUTTON_PRESSED)
                            viewModel.toggleBottomSheet()
                        },
                        containerColor = Color(0xFF009688),
                        shape = CircleShape,
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_lupa_pesquisa),
                            contentDescription = "Lupa de Pesquisa Icon",
                            tint = Color.White
                        )
                    }
                }
            }
        ) { innerpadding ->

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box {

                    GoogleMap(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFF009688))
                            .padding(innerpadding),
                        properties = mapProperties,
                        uiSettings = uiSettings,
                    ) {

                        MapEffect {
                            viewModel.showMyLocalizationIn(it)
                            viewModel.showFeirasLocalizationIn(it)
                            viewModel.googleMap(it)

                        }
                    }
                }
            }

            if (state.showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { viewModel.toggleBottomSheet() },
                    sheetState = sheetState,
                    containerColor = Color(0xFF009688),
                ) {
                    firebaseanalytics.firebaselogEvent(Monitoring.Map.SHOW_MENU_NEIGHBORS)
                    Box(
                        modifier = Modifier
                            .fillMaxHeight(0.5f)
                            .padding(2.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Column {
                            LazyRow(
                                state = listState,
                                modifier = Modifier
                                    .height(80.dp)
                            ) {
                                items(viewModel.cities.size) { index ->
                                    val isSelected = viewModel.cities[index] == state.selectedCity

                                    CitiesMenuComponent(
                                        viewModel.cities,
                                        index,
                                        isSelected,
                                        viewModel,
                                        firebaseanalytics
                                    )
                                }
                            }
                            TextField(
                                value = state.searchQuery,
                                onValueChange = { viewModel.onSearchQueryChange(it) },
                                label = { Text("Pesquisar bairros") },
                                modifier = Modifier.fillMaxWidth()
                            )
                            SearchNeighborHoodComponent(
                                state.searchQuery,
                                state.neighborhoodsToShow,
                                state.cityImages,
                                state.selectedCity,
                                viewModel,
                                firebaseanalytics
                            )
                        }
                    }
                }
            }
        }

}




