package com.example.afimdefeirax.View.Components


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.graphics.Color
import com.example.afimdefeirax.View.Components.quantidade.INICIAL
import com.example.afimdefeirax.View.Components.quantidade.MAX
import com.example.afimdefeirax.View.Components.quantidade.MIN


object quantidade {
    const val INICIAL=5
    const val MIN = 1
    const val MAX = 100
}



@Composable
fun SeletorComponent(onValueChange: (Int) -> Unit){
    var currentValue by remember { mutableStateOf(INICIAL) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier

            .border(2.dp, Color.Black, RoundedCornerShape(16.dp))
            .background(Color.Transparent, RoundedCornerShape(16.dp))
            .padding(2.dp)

    ){
        IconButton(onClick = { currentValue = (currentValue - 1).coerceAtLeast(MIN) }) {
            Icon(imageVector = Icons.Filled.Remove, contentDescription = "diminui")
        }

        Text(
            text = currentValue.toString(),
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.align(alignment = Alignment.CenterVertically)
        )

        IconButton(onClick = { currentValue = (currentValue + 1).coerceAtMost(MAX) }) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "aumenta")
        }

    }

    LaunchedEffect(key1 = currentValue) {
        onValueChange(currentValue)
    }
}

@Composable
fun ButtonSeletorComponent(
    selecionadoBotao: Boolean,
    buttonText: String = ""
){
    var button by remember { mutableStateOf(selecionadoBotao) }

    OutlinedButton(
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (button) Color.Green else Color.Transparent
        ),
        onClick = {
                button = !button
        }
    ) {
        Text(buttonText, color = Color.Black)
    }
}