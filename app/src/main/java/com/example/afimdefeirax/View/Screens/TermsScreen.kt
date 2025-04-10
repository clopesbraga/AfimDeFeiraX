package com.example.afimdefeirax.View.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CheckboxDefaults.colors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import androidx.navigation.NavHostController
import com.example.afimdefeirax.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsOfServiceScreen(navController: NavHostController) {

    val scrollState = rememberScrollState()

    val descriptionTerms = stringResource(R.string.terms_of_service)
    val termsOfService = remember {
        HtmlCompat.fromHtml(descriptionTerms, HtmlCompat.FROM_HTML_MODE_LEGACY)}

    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.info_politicas),
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                navigationIcon = {
                    IconButton(

                        onClick = {
                            navController.navigate("map")
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(innerpading)
        ) {
            Text(
                text = buildAnnotatedString { append(termsOfService.toString())},
                textAlign = TextAlign.Justify,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
