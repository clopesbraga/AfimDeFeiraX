package com.example.afimdefeirax.View.Screens


import android.Manifest
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
import com.example.afimdefeirax.R.mipmap.*
import com.example.afimdefeirax.State.MapFeirasUIState
import com.example.afimdefeirax.Utils.Flags
import com.example.afimdefeirax.View.Components.SearchNeighborHoodComponent


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestLocationPermission() {
    val locationPermissionState =
        rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)



    LaunchedEffect(Unit) {
        if (!locationPermissionState.equals(true)) {
            locationPermissionState.launchPermissionRequest()
        }
    }
}

@OptIn(MapsComposeExperimentalApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MapFeirasScreen(showBottomBar: (Boolean) -> Unit) {


    showBottomBar(true)
    val viewModel: MapaFeirasViewModel = koinInject()
    val sheetState = rememberModalBottomSheetState()
    val listState = rememberLazyListState()


    val mapProperties = MapProperties(isMyLocationEnabled = true)
    val uiSettings = MapUiSettings(zoomControlsEnabled = false)
    var showBottomSheet = remember { mutableStateOf(false) }

    var selectedCity by remember { mutableStateOf<String?>("SAO PAULO") }
    val cities = stringArrayResource(id = R.array.cidades)
    var searchQuery by remember { mutableStateOf("") }
    var cityImages by remember { mutableStateOf(ic_bandeira_saopaulo) }

    val spNeighborhoods = stringArrayResource(id = R.array.sp_bairros)
    var neighborhoodsToShow by remember { mutableStateOf<List<String>>(spNeighborhoods.toList()) }


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
                onClick = {showBottomSheet.value = true },
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
                onDismissRequest = { showBottomSheet.value= false },
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
                                            neighborhoodsToShow =
                                                viewModel.neighborhoodsMap[selectedCity]?.toList()
                                                    ?: emptyList()
                                            cityImages =
                                                Flags[selectedCity] ?: ic_bandeira_saopaulo
                                        }
                                        .padding(horizontal = 16.dp, vertical = 0.dp)

                                )
                            }
                        }
                        TextField(
                            value = searchQuery,
                            onValueChange = {searchQuery = it },
                            label = { Text("Pesquisar bairros") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        SearchNeighborHoodComponent(
                            searchQuery,
                            neighborhoodsToShow,
                            cityImages,
                            selectedCity,
                            viewModel
                        )
                    }
                }


            }


        }


    }


}




