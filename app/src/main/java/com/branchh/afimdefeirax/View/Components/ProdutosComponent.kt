package com.branchh.afimdefeirax.View.Components


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.branchh.afimdefeirax.R
import com.branchh.afimdefeirax.View.Components.preco.PRECO_INICIAL
import com.branchh.afimdefeirax.View.Components.preco.PRECO_MAXIMO
import com.branchh.afimdefeirax.View.Components.preco.PRECO_MINIMO
import com.branchh.afimdefeirax.View.Components.quantidade.INICIAL
import com.branchh.afimdefeirax.View.Components.quantidade.MAX
import com.branchh.afimdefeirax.View.Components.quantidade.MIN


object quantidade {
    const val INICIAL = 5
    const val MIN = 1
    const val MAX = 100
}

object preco {
    const val PRECO_INICIAL = 0
    const val PRECO_MINIMO = 0
    const val PRECO_MAXIMO = 10000
}


@Composable
fun SeletorPesoComponent(onValueChange: (Int) -> Unit) {
    var currentValue by remember { mutableStateOf(INICIAL) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier

            .border(2.dp, Color.Black, RoundedCornerShape(16.dp))
            .background(Color.Transparent, RoundedCornerShape(16.dp))
            .padding(2.dp)

    ) {
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
fun ButtonMeasureComponent(
    unidade: String,
    selectedMeasure: String,
    onSelected: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val selecionadoBota = unidade == selectedMeasure
    OutlinedButton(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selecionadoBota) Color.Green else Color.Transparent
        ),
        onClick = {
            onSelected(unidade)
        }
    ) {
        Text(unidade, color = Color.Black)
    }
}
@Composable
fun SeletorPrecoComponent(onValueChange: (Int) -> Unit) {

    var currentValue by remember { mutableIntStateOf(PRECO_INICIAL) }

    fun formatPrice(value: Int): String {
        return "%.2f".format(value / 100.0).replace('.', ',')
    }

    var textFieldValue by remember(currentValue) {
        mutableStateOf(formatPrice(currentValue))
    }

    LaunchedEffect(currentValue) {
        onValueChange(currentValue)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .border(2.dp, Color.Black, RoundedCornerShape(16.dp))
            .background(Color.Transparent, RoundedCornerShape(16.dp))
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        IconButton(onClick = {
            currentValue = (currentValue - 25).coerceAtLeast(PRECO_MINIMO)
        }) {
            Icon(imageVector = Icons.Filled.Remove, contentDescription = "diminui")
        }

        Text(text = "R$ ", style = MaterialTheme.typography.bodyLarge)

        BasicTextField(
            value = textFieldValue,
            onValueChange = { newValue ->
                val cleanString = newValue.replace(Regex("[^\\d]"), "")
                if (cleanString.isEmpty()) {
                    textFieldValue = "0,00"
                    currentValue = 0
                } else {
                    val totalCents = cleanString.toIntOrNull() ?: 0
                    if (totalCents <= PRECO_MAXIMO) {
                        currentValue = totalCents
                        textFieldValue = formatPrice(totalCents)
                    }
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = MaterialTheme.typography.displaySmall.copy(
                textAlign = TextAlign.Center,
                color = Color.Black
            ),
            modifier = Modifier
                .width(120.dp)
                .padding(vertical = 8.dp),
            singleLine = true
        )
        IconButton(onClick = {
            currentValue = (currentValue + 25).coerceAtMost(PRECO_MAXIMO)
        }) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "aumenta")
        }
    }
}
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MessageNoPrice(showDialog: Boolean,onDismiss:() ->Unit) {
    if (showDialog) {
        BasicAlertDialog(
            onDismissRequest = onDismiss
        ) {
            Surface(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = stringResource(R.string.warnning_price_message))
                    Spacer(modifier = Modifier.height(24.dp))
                    TextButton(
                        onClick = onDismiss,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("OK")
                    }
                }
            }
        }
    }
}