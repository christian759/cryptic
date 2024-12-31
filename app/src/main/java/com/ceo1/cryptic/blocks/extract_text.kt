package com.ceo1.cryptic.blocks

import androidx.compose.runtime.Composable
import com.ceo1.cryptic.Theme

@RequiresApi(Build.VERSION_CODES.O)
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

    BasicText(
        //content =  image
    )
}