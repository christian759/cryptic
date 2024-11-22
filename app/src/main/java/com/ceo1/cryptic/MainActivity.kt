package com.ceo1.cryptic

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ceo1.cryptic.blocks.hide
import com.ceo1.cryptic.blocks.extract
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
        Text("Welcome to Cryptic")

        Button(onClick = {
            page += 1
        }){
            Row{
                val painter1 = painterResource(R.drawable.whatsapp_image_2024_11_21_at_02_00_14_6cdcf836)
                Image(painter = painter1, contentDescription = "encrypt", modifier = Modifier.size(100.dp))
                Column{
                    Text("Hide Images", color = primaryTheme.color1)
                    Text("hide secret images within other image", color = primaryTheme.color1)
                }
            }
        }

        Button(onClick = {
            page += 2
        }){
            Row {
                val painter2 = painterResource(R.drawable.whatsapp_image_2024_11_21_at_02_00_14_ccd28281)
                Image(painter = painter2, contentDescription = "decrypt", modifier = Modifier.size(100.dp))

                Column {
                    Text("Extract Image", color = primaryTheme.color1)
                    Text("unveil secret images that are hidden", color= primaryTheme.color1)
                }
            }
        }

    }

    Column(modifier = Modifier.background(color = primaryTheme.color2)
                            .fillMaxSize()) {
        Spacer(modifier = Modifier.padding(10.dp))

        Column(modifier = Modifier.weight(9f)) {

            Switch(checked = colorTheme,
                onCheckedChange = {
                    colorTheme = if(colorTheme){false}else{true}
                },
                colors = SwitchColors(
                    checkedThumbColor = primaryTheme.color2,
                    checkedTrackColor = primaryTheme.color1,
                    checkedBorderColor = primaryTheme.color2,
                    checkedIconColor = primaryTheme.color1,
                    uncheckedThumbColor = primaryTheme.color1,
                    uncheckedTrackColor = primaryTheme.color2,
                    uncheckedBorderColor = primaryTheme.color1,
                    uncheckedIconColor = primaryTheme.color2,

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

            when (page) {
                1 -> Welcome(primaryTheme)
                2 -> extract(primaryTheme)
                3 -> hide(primaryTheme)
            }
        }

    }
}
