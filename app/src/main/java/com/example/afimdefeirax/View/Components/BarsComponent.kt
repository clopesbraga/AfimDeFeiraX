package com.example.afimdefeirax.View.Components

import android.icu.text.NumberFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import ir.ehsannarmani.compose_charts.models.Bars

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BarsChartComponent(value: Double): Bars {
    val currencyFormat = NumberFormat.getCurrencyInstance()
   return Bars(
        label = "R${currencyFormat.format(value)}",
        values = listOf(
            Bars.Data(
                value = value, color = Brush.horizontalGradient(
                    0.3f to Color.Green,
                    0.6f to Color.Blue,
                    0.9f to Color.Red,
                )
            ),
        )
    )
}