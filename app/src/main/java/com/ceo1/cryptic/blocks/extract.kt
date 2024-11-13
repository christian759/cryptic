package com.ceo1.cryptic.blocks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ceo1.cryptic.ui.theme.Black
import com.ceo1.cryptic.ui.theme.White

@Composable
fun extract(){
    var extractedText by remember { mutableStateOf("") }

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
        TextField(
            readOnly = true,
            value = extractedText,
            onValueChange = {extractedText = it},
            modifier = Modifier.size(20.dp)
                .background(Black)
        )
    }

    Text("Note: only images and text that have been hidden by this app can be extracted",
        color = Black)
}