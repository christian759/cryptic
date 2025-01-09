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
import com.ceo1.cryptic.blocks.hide
import com.ceo1.cryptic.blocks.extract
import com.ceo1.cryptic.blocks.extractText
import com.ceo1.cryptic.blocks.hideText
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

    // color theme can only be one or two indicating light and dark theme
    var colorTheme by remember { mutableStateOf(false) }

    // light theme and dark theme
    val Darkprimary = Theme(Dark1, Dark2, Dark3)
    val LightTheme = Theme(Light1, Light2, Light3)

    var primaryTheme by remember { mutableStateOf(LightTheme) }

    if (colorTheme) {
       primaryTheme = Darkprimary
    }else{
        primaryTheme = LightTheme
    }

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

        }
    }

    Welcome(colorTheme)
}



    }
}
