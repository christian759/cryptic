package com.ceo1.cryptic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ceo1.cryptic.blocks.*
import com.ceo1.cryptic.ui.theme.*

class MainActivity : ComponentActivity() {
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


@Composable
fun Master() {
    Column(modifier = Modifier.background(color = DarkPurple)
                            .fillMaxSize())
    {
        Spacer(modifier = Modifier.padding(10.dp))
        var page by remember { mutableStateOf(1) }

        Column(modifier = Modifier.weight(9f)) {
            when (page) {
                1 -> extract()
                2 -> hide()

            }
        }

        Row(modifier = Modifier.weight(1f)
            .padding(horizontal = 10.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(30.dp)) {

                Button(
                    modifier = Modifier.background(Color.Unspecified)
                        .padding(10.dp),
                    onClick = {
                        page = 1
                    },
                    border = BorderStroke(1.dp, White),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Extract image")
                }
                Button(
                    modifier = Modifier.background(Color.Unspecified)
                        .padding(10.dp),
                    onClick = {
                        page = 2
                    },
                    border = BorderStroke(1.dp, White),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Hide image")
                }
        }
    }

}