package com.example.lemonade

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Composable
fun LemonadeApp() {
    var step by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    val imageResource = when (step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val instructionText = when (step) {
        1 -> "Toca el árbol para recolectar un limón"
        2 -> "Exprime el limón tocando varias veces"
        3 -> "Toca el vaso para beber la limonada"
        else -> "Toca el vaso vacío para empezar de nuevo"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = instructionText, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .clickable {
                    when (step) {
                        1 -> {
                            step = 2
                            squeezeCount = Random.nextInt(2, 5)
                        }
                        2 -> {
                            squeezeCount--
                            if (squeezeCount == 0) step = 3
                        }
                        3 -> step = 4
                        4 -> step = 1
                    }
                }
        )
    }
}
