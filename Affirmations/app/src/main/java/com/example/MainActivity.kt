package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                AffirmationList()
            }
        }
    }
}

@Composable
fun AffirmationList() {
    val affirmations = stringArrayResource(id = R.array.affirmations_array)

    // Lista de imágenes en el mismo orden que las afirmaciones
    val images = listOf(
        R.drawable.affirmation1,
        R.drawable.affirmation2,
        R.drawable.affirmation3,
        R.drawable.affirmation4,
        R.drawable.affirmation5,
        R.drawable.affirmation6,
        R.drawable.affirmation7,
        R.drawable.affirmation10
    )

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        itemsIndexed(affirmations) { index, text ->
            AffirmationCard(text = text, imageResId = images[index])
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun AffirmationCard(text: String, imageResId: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
