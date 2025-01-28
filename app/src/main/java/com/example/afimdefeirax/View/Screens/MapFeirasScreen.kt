package com.example.afimdefeirax.View.Screens


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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

import kotlinx.coroutines.launch
import com.example.afimdefeirax.R


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
    var googleMap = remember { mutableStateOf(null) }


    var showBottomSheet = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    data class CarouselItem(
        val id: Int,
        @DrawableRes val imageResId: Int,
        @StringRes val contentDescriptionResId: Int
    )

    val carrouselitems =
        listOf(
            CarouselItem(0, com.google.android.material.R.drawable.mtrl_ic_cancel, R.string.welcome),
            CarouselItem(1, com.google.android.material.R.drawable.mtrl_ic_cancel, R.string.welcome),
            CarouselItem(2, com.google.android.material.R.drawable.mtrl_ic_cancel, R.string.welcome),
            CarouselItem(3, com.google.android.material.R.drawable.mtrl_ic_cancel, R.string.welcome),
            CarouselItem(5, com.google.android.material.R.drawable.mtrl_ic_cancel, R.string.welcome),
            CarouselItem(6, com.google.android.material.R.drawable.mtrl_ic_cancel, R.string.welcome),
            CarouselItem(7, com.google.android.material.R.drawable.mtrl_ic_cancel, R.string.welcome),
            CarouselItem(8, com.google.android.material.R.drawable.mtrl_ic_cancel, R.string.welcome),
            CarouselItem(9, com.google.android.material.R.drawable.mtrl_ic_cancel, R.string.welcome),
            CarouselItem(10, com.google.android.material.R.drawable.mtrl_ic_cancel, R.string.welcome),
            CarouselItem(11, com.google.android.material.R.drawable.mtrl_ic_cancel, R.string.welcome),
        )


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

                    }


                }

            }

        }
        if (showBottomSheet.value) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet.value = false
                },
                sheetState = sheetState
            ) {

                Text(text = "Cidades",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                )

                Row(modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)

                ) {
                    LazyColumn(state = listState, modifier = Modifier.height(120.dp)) {
                        items(10) { index ->
                            Text(
                                modifier = Modifier.height(40.dp),
                                text = "Item $index"
                            )
                        }
                    }

                }

                Text(text = "Bairros",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                )

                Column(modifier = Modifier.padding(16.dp)) {

                    TextField(
                        value = "",
                        onValueChange = {},
                        label = { Text("Search") },
                        modifier = Modifier.fillMaxWidth()
                    )


                }

                HorizontalMultiBrowseCarousel(
                    state = rememberCarouselState { carrouselitems.count() },
                    modifier = Modifier.width(412.dp).height(100.dp),
                    preferredItemWidth = 186.dp,
                    itemSpacing = 8.dp,
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) { i ->
                    val item = carrouselitems[i]
                    Image(
                        modifier = Modifier.height(205.dp).maskClip(MaterialTheme.shapes.extraSmall),
                        painter = painterResource(id = item.imageResId),
                        contentDescription = stringResource(item.contentDescriptionResId),
                        contentScale = ContentScale.Crop
                    )
                }

            }
        }






    }
}

