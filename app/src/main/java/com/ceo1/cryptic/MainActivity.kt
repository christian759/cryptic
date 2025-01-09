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

            Card(
                border = BorderStroke(width = 5.dp, color = primaryTheme.color2),
                modifier = Modifier.fillMaxWidth().padding(12.dp).height(90.dp),
                colors = CardColors(
                    containerColor = primaryTheme.color3,
                    contentColor = primaryTheme.color2,

                    disabledContainerColor = Color.Unspecified,
                    disabledContentColor = Color.Unspecified),
                onClick = {
                    page = 5
                }) {
                Text("Hide Text",
                    fontSize = TextUnit(4F, TextUnitType.Em),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(7.dp))
                Text("Hide secret text within other image", fontSize = TextUnit(3F, TextUnitType.Em),
                    modifier = Modifier.padding(7.dp))
            }
        }
    }

    Column(modifier = Modifier.background(color = primaryTheme.color1)
                            .fillMaxSize()) {
        Spacer(modifier = Modifier.padding(15.dp))

        Column {

            Row(horizontalArrangement = Arrangement.SpaceBetween) {

            if (page != 1){
                IconButton(onClick = {
                    page = 1
                }) {
                    val painter = Icons.Default.ArrowBack
                    Icon(imageVector = painter, contentDescription = "")
                }
            }
                Switch(modifier = Modifier.align(Alignment.Top),
                checked = colorTheme,
                onCheckedChange = {
                    colorTheme = if (colorTheme) {
                        false
                    } else {
                        true
                    }
                },
                colors = SwitchColors(
                    checkedThumbColor = primaryTheme.color2,
                    checkedTrackColor = primaryTheme.color3,
                    checkedBorderColor = primaryTheme.color2,
                    checkedIconColor = primaryTheme.color3,
                    uncheckedThumbColor = primaryTheme.color2,
                    uncheckedTrackColor = primaryTheme.color3,
                    uncheckedBorderColor = primaryTheme.color2,
                    uncheckedIconColor = primaryTheme.color3,

                    disabledCheckedThumbColor = primaryTheme.color2,
                    disabledCheckedTrackColor = primaryTheme.color2,
                    disabledCheckedBorderColor = primaryTheme.color2,
                    disabledCheckedIconColor = primaryTheme.color2,
                    disabledUncheckedThumbColor = primaryTheme.color2,
                    disabledUncheckedTrackColor = primaryTheme.color2,
                    disabledUncheckedBorderColor = primaryTheme.color2,
                    disabledUncheckedIconColor = primaryTheme.color2
                )
            )
        }
            when (page) {
                1 -> Welcome(primaryTheme)
                2 -> hide(primaryTheme)
                3 -> extract(primaryTheme)
                4 -> extractText(primaryTheme)
                5 -> hideText(primaryTheme)
            }
        }

    }
}
