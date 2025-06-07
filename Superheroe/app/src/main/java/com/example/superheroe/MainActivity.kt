package com.example.superheroe

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

data class SuperHero(
    val nameRes: Int,
    val aliasRes: Int,
    val descRes: Int,
    val imageRes: Int
)

class MainActivity : ComponentActivity() {

    private val heroes = listOf(
        SuperHero(R.string.hero_name_1, R.string.hero_alias_1, R.string.hero_desc_1, R.drawable.hero1),
        SuperHero(R.string.hero_name_2, R.string.hero_alias_2, R.string.hero_desc_2, R.drawable.hero2),
        SuperHero(R.string.hero_name_3, R.string.hero_alias_3, R.string.hero_desc_3, R.drawable.hero3)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LazyColumn {
                        items(heroes) { hero ->
                            HeroCard(hero)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HeroCard(hero: SuperHero) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(hero.imageRes),
                    contentDescription = stringResource(hero.aliasRes),
                    modifier = Modifier
                        .size(72.dp)
                        .padding(end = 16.dp)
                )
                Column {
                    Text(
                        text = stringResource(hero.aliasRes),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = stringResource(hero.nameRes),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = if (expanded)
                    stringResource(hero.descRes)
                else
                    "Ver más...",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.clickable { expanded = !expanded }
            )
        }
    }
}
