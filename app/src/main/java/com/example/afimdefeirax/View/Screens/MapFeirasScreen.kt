package com.example.afimdefeirax.View.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController


@Composable
fun MapFeirasScreen(navController: NavHostController) {

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text="MapFeirasScreen")
    }


}

@Preview(showBackground = true)
@Composable
fun MapFeirasScreenPreview() {
        MapFeirasScreen(
            navController = NavHostController(LocalContext.current)
        )
    }