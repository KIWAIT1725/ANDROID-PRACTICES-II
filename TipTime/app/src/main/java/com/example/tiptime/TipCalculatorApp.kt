package com.example.tiptime

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import kotlin.math.ceil

@Composable
fun TipCalculatorApp() {
    var amountInput by remember { mutableStateOf("") }
    var tipPercentInput by remember { mutableStateOf("") }
    var roundUp by remember { mutableStateOf(false) }

    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tipPercent = tipPercentInput.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount, tipPercent, roundUp)

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = R.string.calculate_tip),
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = amountInput,
            onValueChange = { amountInput = it },
            label = { Text(stringResource(id = R.string.bill_amount)) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = tipPercentInput,
            onValueChange = { tipPercentInput = it },
            label = { Text(stringResource(id = R.string.how_was_the_service)) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("¿Redondear?", modifier = Modifier.weight(1f))
            Switch(
                checked = roundUp,
                onCheckedChange = { roundUp = it }
            )
        }

        Text(
            text = stringResource(id = R.string.tip_amount, tip),
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 24.dp)
        )
    }
}

fun calculateTip(amount: Double, tipPercent: Double, roundUp: Boolean): String {
    var tip = amount * tipPercent / 100
    if (roundUp) {
        tip = ceil(tip)
    }
    return NumberFormat.getCurrencyInstance().format(tip)
}
