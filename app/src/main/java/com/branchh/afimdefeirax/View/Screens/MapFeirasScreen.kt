package com.branchh.afimdefeirax.View.Screens


import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.branchh.afimdefeirax.R
import com.branchh.afimdefeirax.Model.TipoFeiraModel
import com.branchh.afimdefeirax.State.MapFeirasUIState
import com.branchh.afimdefeirax.Utils.FirebaseAnalytics.FirebaseAnalyticsImpl
import com.branchh.afimdefeirax.Utils.Monitoring
import com.branchh.afimdefeirax.View.Components.CitiesMenuComponent
import com.branchh.afimdefeirax.View.Components.DaysOfWeekMenuComponent
import com.branchh.afimdefeirax.View.Components.SearchNeighborHoodComponent
import com.branchh.afimdefeirax.View.Components.TutorialShowCaseComponent
import com.branchh.afimdefeirax.ViewModel.MapaFeirasViewModel
import com.canopas.lib.showcase.IntroShowcase
import com.canopas.lib.showcase.component.ShowcaseStyle
import com.canopas.lib.showcase.component.rememberIntroShowcaseState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapEffect
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MapsComposeExperimentalApi
import org.koin.compose.koinInject


@Preview(showBackground = true)
@Composable
fun MapFeirasPreview(){
    MapFeirasContent(
        showBottomBar = { },
        firebaseanalytics = null,
        viewModel = null
    )
}

@Composable
fun MapFeirasScreen(showBottomBar: (Boolean) -> Unit) {

    val firebaseanalytics: FirebaseAnalyticsImpl = koinInject()
    val viewModel: MapaFeirasViewModel = koinInject()

    MapFeirasContent(showBottomBar,firebaseanalytics,viewModel)
}

@OptIn(ExperimentalMaterial3Api::class, MapsComposeExperimentalApi::class)
@Composable
fun MapFeirasContent(
    showBottomBar: (Boolean) -> Unit,
    firebaseanalytics: FirebaseAnalyticsImpl?,
    viewModel: MapaFeirasViewModel?
) {
    val isInPreview = LocalInspectionMode.current

    var shouldShowBottomBar by remember { mutableStateOf(true) }
    SideEffect { showBottomBar(shouldShowBottomBar) }

    val state = viewModel?.state?.collectAsState()?.value ?: MapFeirasUIState(
        neighborhoodsToShow = listOf("Bairro 1", "Bairro 2", "Bairro 3")
    )

    val daysOfWeekArray = viewModel?.days_of_week ?: arrayOf("TER", "QUA", "QUI", "SEX", "SAB", "DOM")
    val citiesArray = viewModel?.cities ?: arrayOf("SAO PAULO", "GUARULHOS", "SUZANO")

    val sheetState = rememberModalBottomSheetState()
    val citiesListState = rememberLazyListState()
    val daysListState = rememberLazyListState()

    val mapProperties = MapProperties(isMyLocationEnabled = true)
    val uiSettings = MapUiSettings(zoomControlsEnabled = false)
    val activity = LocalActivity.current
    val context = LocalContext.current

    firebaseanalytics?.firebaselogEvent(Monitoring.Map.MAP_SCREEN_START)

    LaunchedEffect(Unit) {
        if (viewModel != null) {
            viewModel.days_of_week.indexOf(state.selecteDayOfWeek)
            viewModel.onShowTutorial()
            viewModel.onReview(activity, context)
        }
    }
    val introShowcaseState = rememberIntroShowcaseState()

    IntroShowcase(
        showIntroShowCase = if (isInPreview) false else state.showTutorial,
        state = introShowcaseState,
        onShowCaseCompleted = { viewModel?.onTutorialCompleted() }
    ) {

        Scaffold(
            Modifier.fillMaxSize(),
            topBar = {

                Column() {

                    TopAppBar(
                        title = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            )
                            {
                                Text(stringResource(R.string.fairs_of_City), color = Color.White)
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(Color(0xFF009688)),
                    )

                    LazyRow(
                        state = daysListState,
                        modifier = Modifier
                            .height(40.dp)
                    ) {
                        items(daysOfWeekArray.size) { index ->
                            val isSelected = daysOfWeekArray[index] == state.selecteDayOfWeek

                            if(index == 0 && !isInPreview){
                                Box(Modifier.introShowCaseTarget(
                                    index = 0,
                                    style = ShowcaseStyle.Default.copy(
                                        backgroundColor = Color(0xFF009688),
                                        backgroundAlpha = 0.98f,
                                        targetCircleColor = Color.White
                                    ),
                                    content={
                                        Column {
                                            Text(
                                                text = stringResource(R.string.tutorial_title_days_of_week),
                                                color = Color.White,
                                                fontSize = 24.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = stringResource(R.string.tutorial_description_days_of_week),
                                                color = Color.White,
                                                fontSize = 16.sp
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))
                                        }
                                    }
                                )){

                                    DaysOfWeekMenuComponent(
                                        daysOfWeekArray,
                                        index,
                                        isSelected,
                                        viewModel,
                                        firebaseanalytics)
                                }
                            } else {
                                DaysOfWeekMenuComponent(
                                    daysOfWeekArray,
                                    index,
                                    isSelected,
                                    viewModel,
                                    firebaseanalytics
                                )
                            }
                        }
                    }

                }
            },
            floatingActionButton = {
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    var showFilterMenu by remember { mutableStateOf(false) }

                    Box {
                        FloatingActionButton(
                            onClick = {
                                showFilterMenu = true
                                firebaseanalytics?.firebaselogEvent(Monitoring.Map.MAP_MARKER_LOCALIZATION)
                            },
                            containerColor = Color(0xFF009688),
                            shape = CircleShape,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_nav_btn_map),
                                contentDescription = "Filtrar Feiras Icon",
                                tint = Color.White
                            )
                        }

                        DropdownMenu(
                            expanded = showFilterMenu,
                            onDismissRequest = { showFilterMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Feiras Orgânicas") },
                                onClick = {
                                    showFilterMenu = false
                                    firebaseanalytics?.firebaselogEvent(Monitoring.Map.MAP_MARKER_FEIRAS)
                                    viewModel?.filterFeirasByType(TipoFeiraModel.ORGANICA)
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Feiras Tradicionais") },
                                onClick = {
                                    showFilterMenu = false
                                    firebaseanalytics?.firebaselogEvent(Monitoring.Map.MAP_MARKER_FEIRAS)
                                    viewModel?.filterFeirasByType(TipoFeiraModel.NORMAL)
                                }
                            )
                        }
                    }

                    Box(
                         modifier = if (isInPreview) Modifier else Modifier.introShowCaseTarget(
                             index = 1,
                             style = ShowcaseStyle.Default.copy(
                                 backgroundColor = Color(0xFF009688),
                                 backgroundAlpha = 0.98f,
                                 targetCircleColor = Color.White
                             ),
                             content = {
                                 Column {
                                     Text(
                                         text = stringResource(R.string.tutorial_title_button_search_neigboor),
                                         color = Color.White,
                                         fontSize = 24.sp,
                                         fontWeight = FontWeight.Bold
                                     )
                                     Spacer(modifier = Modifier.height(4.dp))
                                     Text(
                                         text = stringResource(R.string.tutorial_description_button_search_neigboor),
                                         color = Color.White,
                                         fontSize = 16.sp
                                     )
                                     Spacer(modifier = Modifier.height(10.dp))
                                 }

                             }
                         )
                    ) {
                        FloatingActionButton(
                            onClick = {
                                firebaseanalytics?.firebaselogEvent(Monitoring.Map.MAP_FLOATING_BUTTON_PRESSED)
                                viewModel?.toggleBottomSheet()
                            },
                            containerColor = Color(0xFF009688),
                            shape = CircleShape,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_lupa_pesquisa),
                                contentDescription = "Lupa de Pesquisa Icon",
                                tint = Color.White
                            )
                        }
                    }
                }
            }

        ) { innerpadding ->

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    if (isInPreview) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(0xFF009688))
                                .padding(innerpadding),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Mapa de Feiras (Preview)",
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    } else {
                        GoogleMap(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(0xFF009688))
                                .padding(innerpadding),
                            properties = mapProperties,
                            uiSettings = uiSettings,
                        ) {
                            MapEffect {
                                viewModel?.showMyLocalizationIn(it)
                                viewModel?.showFeirasLocalizationIn(it)
                                viewModel?.googleMap(it)
                            }
                        }
                    }
                }
            }

            if (state.showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { viewModel?.toggleBottomSheet() },
                    sheetState = sheetState,
                    containerColor = Color(0xFF009688),
                ) {
                    firebaseanalytics?.firebaselogEvent(Monitoring.Map.SHOW_MENU_NEIGHBORS)
                    Box(
                        modifier = Modifier
                            .fillMaxHeight(0.5f)
                            .padding(2.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Column {
                            LazyRow(
                                state = citiesListState,
                                modifier = Modifier
                                    .height(80.dp)
                            ) {
                                items(citiesArray.size) { index ->
                                    val isSelected = citiesArray[index] == state.selectedCity

                                    CitiesMenuComponent(
                                        citiesArray,
                                        index,
                                        isSelected,
                                        viewModel,
                                        firebaseanalytics
                                    )
                                }
                            }
                            TextField(
                                value = state.searchQuery,
                                onValueChange = { viewModel?.onSearchQueryChange(it) },
                                label = { Text(stringResource(R.string.search_neighborhoods)) },
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
}




