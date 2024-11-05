package com.example.afimdefeirax.View.Components

import android.icu.text.NumberFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.ehsannarmani.compose_charts.RowChart
import ir.ehsannarmani.compose_charts.models.BarProperties
import ir.ehsannarmani.compose_charts.models.Bars

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ColunaDinamica(preco1: Double, preco2: Double, preco3: Double) {

    RowChart(
        modifier = Modifier
            .padding(2.dp)
            .size(300.dp, 150.dp),
        data = listOf(
            BarsChartComponent(preco1),
            BarsChartComponent(preco2),
            BarsChartComponent(preco3)
        ),
        barProperties = BarProperties(
            cornerRadius = Bars.Data.Radius.Rectangle(topRight = 6.dp, topLeft = 6.dp),
            spacing = 5.dp,
            thickness = 20.dp
        ),
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
    )

}


