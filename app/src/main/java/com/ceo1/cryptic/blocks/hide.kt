package com.ceo1.cryptic.blocks

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ceo1.cryptic.ui.theme.*

@Composable
fun hide(){
    Card(colors = CardColors(White, White, White, White)) {
        Text("Upload your image")
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                // do nothing
            }
        ){
            Row {
                Text("Select image")
            }
        }
    }

    Card(colors = CardColors(White, White, White, White)) {
        Text("Upload your image")
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                // do nothing
            }
        ){
            Row{
                Text("Select image")
            }
        }

    }
    Text("Note: please Do not use this app for any illegal purpose, as the developers will not be responsible",
        color = Black)
}