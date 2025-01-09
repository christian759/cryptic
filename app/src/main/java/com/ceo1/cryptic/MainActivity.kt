package com.ceo1.cryptic

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.ceo1.cryptic.ui.theme.*

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold( modifier = Modifier.fillMaxSize() ) {
                Master()
            }
        }
    }
}

data class Theme(val color1: Color, val color2: Color, val color3: Color)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Master() {
    // theme
    val colorTheme = Theme(darkPurple, mediumPurple, lightPurple)

    // Page number
    var page by remember { mutableStateOf(1) }

    // welcome page(inner function)
    @Composable
    fun Welcome(primaryTheme: Theme){
        Column {
            Text(
                "Welcome to Cryptic",
                fontSize = TextUnit(6F, TextUnitType.Em),
                fontWeight = FontWeight.Bold,
                color = primaryTheme.color3,
                modifier = Modifier.align(Alignment.CenterHorizontally)
                    .padding(12.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize()
            ){
                item{
                    Button(onClick = {}) {
                        Text("Hide Text")
                    }
                }

                item{
                    Button(onClick = {}) {
                        Text("Hide Image")
                    }
                }

                item{
                    Button(onClick = {}) {
                        Text("Extract Text")
                    }
                }

                item{
                    Button(onClick = {}) {
                        Text("Extract Image")
                    }
                }
            }
        }
    }

    Welcome(colorTheme)
}



