package com.branchh.afimdefeirax.View.Components

import android.icu.text.NumberFormat
import android.icu.util.Currency
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.intl.Locale
import ir.ehsannarmani.compose_charts.models.Bars

@Composable
fun BarsChartComponent(value: Double): Bars {
    val currencyFormat = NumberFormat.getCurrencyInstance(java.util.Locale("pt", "BR"))
    currencyFormat.currency = Currency.getInstance("BRL")
    val valueInMoney = value/100

    currencyFormat.minimumFractionDigits = 2
    currencyFormat.maximumFractionDigits = 2

    val formattedValue = currencyFormat.format(valueInMoney)
   return Bars(
        label = formattedValue,
        values = listOf(
            Bars.Data(
                value = valueInMoney,
                color = Brush.horizontalGradient(
                    0.3f to Color.Green,
                    0.6f to Color.Blue,
                    0.9f to Color.Red,
                )
            ),
        )
    )
}