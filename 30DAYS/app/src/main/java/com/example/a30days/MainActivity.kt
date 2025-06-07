package com.example.a30days

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class DayActivity(
    val day: Int,
    val titleResId: Int,
    val descResId: Int,
    val imageResId: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val days = List(30) { index ->
            val day = index + 1
            val titleId = resources.getIdentifier("day${day}_title", "string", packageName)
            val descId = resources.getIdentifier("day${day}_desc", "string", packageName)
            val imageId = resources.getIdentifier("day$day", "drawable", packageName)
            DayActivity(day, titleId, descId, imageId)
        }

        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ActivityList(days)
                }
            }
        }
    }
}

@Composable
fun ActivityList(days: List<DayActivity>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(days) { activity ->
            ActivityCard(activity)
        }
    }
}

@Composable
fun ActivityCard(activity: DayActivity) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Día ${activity.day}: ${stringResource(id = activity.titleResId)}",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(id = activity.imageResId),
                contentDescription = stringResource(id = activity.titleResId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = activity.descResId),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
