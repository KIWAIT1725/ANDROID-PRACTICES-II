package com.example.woof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.woof.ui.theme.WoofTheme

data class Dog(
    val nameResId: Int,
    val ageResId: Int,
    val imageResId: Int,
    val descriptionResId: Int
)

class MainActivity : ComponentActivity() {

    private val dogList = listOf(
        Dog(R.string.dog_name_1, R.string.dog_age_1, R.drawable.dog1, R.string.dog_desc_1),
        Dog(R.string.dog_name_2, R.string.dog_age_2, R.drawable.dog2, R.string.dog_desc_2),
        Dog(R.string.dog_name_3, R.string.dog_age_3, R.drawable.dog3, R.string.dog_desc_3),
        Dog(R.string.dog_name_4, R.string.dog_age_4, R.drawable.dog4, R.string.dog_desc_4),
        Dog(R.string.dog_name_5, R.string.dog_age_5, R.drawable.dog5, R.string.dog_desc_5),
        Dog(R.string.dog_name_6, R.string.dog_age_6, R.drawable.dog6, R.string.dog_desc_6),
        Dog(R.string.dog_name_7, R.string.dog_age_7, R.drawable.dog7, R.string.dog_desc_7),
        Dog(R.string.dog_name_8, R.string.dog_age_8, R.drawable.dog8, R.string.dog_desc_8),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WoofTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LazyColumn {
                        items(dogList) { dog ->
                            DogCard(dog = dog)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DogCard(dog: Dog) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(dog.imageResId),
                    contentDescription = stringResource(dog.nameResId),
                    modifier = Modifier
                        .size(64.dp)
                        .padding(end = 16.dp)
                )
                Column {
                    Text(
                        text = stringResource(dog.nameResId),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = stringResource(dog.ageResId),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = if (expanded)
                    stringResource(dog.descriptionResId)
                else
                    "Ver más...",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .clickable { expanded = !expanded }
                    .padding(top = 8.dp)
            )
        }
    }
}
