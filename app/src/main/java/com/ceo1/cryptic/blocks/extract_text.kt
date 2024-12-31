package com.ceo1.cryptic.blocks


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
// import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.ceo1.cryptic.Theme


@Composable
fun extractText(primaryTheme: Theme){
    val context = LocalContext.current

    Column{
        Button(colors = ButtonColors(containerColor = primaryTheme.color3, contentColor = primaryTheme.color1, disabledContentColor = Color.Unspecified,
            disabledContainerColor = Color.Unspecified),
            border = BorderStroke(5.dp, primaryTheme.color1),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .weight(0.5f)
                .padding(5.dp)
            .fillMaxHeight(),
            onClick = {
                // extract image
            }){ Text("Extract Image", fontSize = TextUnit(18f, TextUnitType.Sp))}
    }
    Spacer(modifier = Modifier.padding(10.dp))
    Text("extract text")
/*
    BasicText(
        text = TODO(),
        modifier = TODO(),
        style = TODO(),
        onTextLayout = TODO(),
        overflow = TODO(),
        softWrap = TODO(),
        maxLines = TODO(),
        minLines = TODO(),
        color = TODO()
        //content =  image
    )

 */
}