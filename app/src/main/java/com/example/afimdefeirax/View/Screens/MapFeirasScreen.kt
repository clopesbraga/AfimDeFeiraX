package com.example.afimdefeirax.View.Screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.afimdefeirax.ViewModel.MapaFeirasViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MapsComposeExperimentalApi
import org.koin.compose.koinInject
import com.google.maps.android.compose.MapEffect
import com.example.afimdefeirax.R
import com.example.afimdefeirax.R.mipmap
import com.example.afimdefeirax.R.mipmap.*
import com.example.afimdefeirax.Utils.CarouselItem
import com.example.afimdefeirax.View.Components.ListOfNeighborHood
import kotlin.text.contains


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestLocationPermission() {
    val locationPermissionState =
        rememberPermissionState(android.Manifest.permission.ACCESS_FINE_LOCATION)



    LaunchedEffect(Unit) {
        if (!locationPermissionState.equals(true)) {
            locationPermissionState.launchPermissionRequest()
        }
    }
}

@OptIn(MapsComposeExperimentalApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MapFeirasScreen(navController: NavHostController, showBottomBar: (Boolean) -> Unit) {

    showBottomBar(true)

    val mapProperties = MapProperties(isMyLocationEnabled = true)
    val uiSettings = MapUiSettings(zoomControlsEnabled = false)

    val viewModel: MapaFeirasViewModel = koinInject()

    var showBottomSheet = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val listState = rememberLazyListState()

    val spNeighborhoods = stringArrayResource(id = R.array.sp_bairros)
    val guaruNeighborhoods = stringArrayResource(id = R.array.guaru_bairros)
    val suzanoNeighborhoods = stringArrayResource(id = R.array.suza_bairros)
    val osNeighborhoods = stringArrayResource(id = R.array.osasco_bairros)
    val mauaNeighborhoods = stringArrayResource(id = R.array.maua_bairros)
    val andreNeighborhoods = stringArrayResource(id = R.array.andre_bairros)
    val bernardoNeighborhoods = stringArrayResource(id = R.array.bernado_bairros)
    val catetanoNeighborhoods = stringArrayResource(id = R.array.caetano_bairros)
    var cityImages by remember { mutableStateOf(ic_bandeira_saopaulo) }

    val cityFlags = mapOf(
        "SAO PAULO" to ic_bandeira_saopaulo,
        "GUARULHOS" to ic_bandeira_guarulhos,
        "SUZANO" to ic_bandeira_suzano,
        "OSASCO" to ic_bandeira_osasco,
        "MAUA" to ic_bandeira_maua,
        "SANTO ANDRE" to ic_bandeira_st_andre,
        "SAO BERNADO" to ic_bandeira_st_bernado,
        "SAO CAETANO" to ic_bandeira_st_caetano
    )


    var selectedCity by remember { mutableStateOf<String?>("SAO PAULO") }
    var neighborhoodsToShow by remember { mutableStateOf<List<String>>(spNeighborhoods.toList()) }
    val cities = stringArrayResource(id = R.array.cidades)

    var searchQuery by remember { mutableStateOf("") }


    RequestLocationPermission()
    Scaffold(

        Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Feiras da cidade", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(Color(0xFF009688))
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showBottomSheet.value = true },
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
    ) { innerpadding ->

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(innerpadding)
        ) {

            Box {

                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
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
        if (showBottomSheet.value) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet.value = false },
                sheetState = sheetState,
                containerColor = Color(0xFF009688),
            ) {

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
                            items(cities.size) { index ->
                                val isSelected = cities[index] == selectedCity

                                Text(
                                    text = cities[index],
                                    color = if (isSelected) Color(0xFF009688) else Color.White,

                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    modifier = Modifier
                                        .height(40.dp)
                                        .background(
                                            if (isSelected) Color.White else Color.Transparent,
                                            shape = MaterialTheme.shapes.medium
                                        )


                                        .clickable {
                                            selectedCity = cities[index]
                                            neighborhoodsToShow = when (selectedCity) {
                                                "SAO PAULO" -> spNeighborhoods.toList()
                                                "GUARULHOS" -> guaruNeighborhoods.toList()
                                                "SUZANO" -> suzanoNeighborhoods.toList()
                                                "OSASCO" -> osNeighborhoods.toList()
                                                "MAUA" -> mauaNeighborhoods.toList()
                                                "SAO BERNADO" -> bernardoNeighborhoods.toList()
                                                "SAO CAETANO" -> catetanoNeighborhoods.toList()
                                                "SANTO ANDRE" -> andreNeighborhoods.toList()
                                                else -> spNeighborhoods.toList()
                                            }
                                            cityImages =
                                                cityFlags[selectedCity] ?: ic_bandeira_saopaulo
                                        }
                                        .padding(horizontal = 16.dp, vertical = 0.dp)

                                )
                            }
                        }

                        TextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            label = { Text("Pesquisar bairros") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        val filteredCarouselItems = neighborhoodsToShow.filter {
                            it.contains(searchQuery, ignoreCase = true)
                        }.map { bairro ->
                            CarouselItem(
                                bairros = bairro,
                                imageResId = cityImages,
                                contentDescriptionResId = R.string.app_name
                            )
                        }
                        if (filteredCarouselItems.isEmpty()) {
                            Text(
                                text = "Nenhum bairro encontrado",
                                color = Color.White,
                                modifier = Modifier.padding(16.dp)
                            )
                        } else {
                            ListOfNeighborHood(
                                filteredCarouselItems,
                                selectedCity,
                                viewModel
                            )
                        }
                    }
                }


            }


        }


    }


}


