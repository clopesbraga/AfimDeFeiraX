package com.branchh.afimdefeirax.View.Components

import android.R.attr.progress
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.branchh.afimdefeirax.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashSCreenComponet(onAnimationFinished: () -> Unit, showBottomBar: (Boolean) -> Unit){

    Content(
        onAnimationFinished=onAnimationFinished,
        showBottomBar = showBottomBar
    )

}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun Content(onAnimationFinished: () -> Unit,showBottomBar: (Boolean) -> Unit) {

    val composition: LottieComposition? by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.logo_animation)
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations =  1
    )

    showBottomBar(false)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF009688)),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {

            LottieAnimation(
                composition = composition,
                progress = {progress},
                modifier = Modifier
                    .padding(8.dp)
                    .size(350.dp)
            )

        }

    }
    LaunchedEffect(progress) {
        if (progress == 1f) {
            onAnimationFinished()
        }
    }

}


@Preview(showBackground = true)
@Composable
fun SplashScreenComponentPreview(){


Content(onAnimationFinished = {}, showBottomBar = {})


}