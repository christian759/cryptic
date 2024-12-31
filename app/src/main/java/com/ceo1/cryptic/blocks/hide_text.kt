package com.ceo1.cryptic.blocks

// imports
import androidx.compose.runtime.Composable
import com.ceo1.cryptic.Theme
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.unit.*
import coil.compose.rememberAsyncImagePainter
import coil.request.*
import com.ceo1.cryptic.*
import com.ceo1.cryptic.ui.theme.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun hideText(primaryTheme: Theme){
    val context = LocalContext.current

    // cover image info
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent() ) {
            uri: Uri? -> imageUri = uri
    }

    Column {
        Button(colors = ButtonColors(containerColor = primaryTheme.color3, contentColor = primaryTheme.color1, disabledContentColor = Color.Unspecified,
            disabledContainerColor = Color.Unspecified),
            border = BorderStroke(5.dp, primaryTheme.color1),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .weight(0.5f)
                .padding(5.dp)
                .fillMaxHeight(),
            onClick = {
                launcher.launch("image/*")
            }
        ) { Text("Pick an image", fontSize = TextUnit(18f, TextUnitType.Sp))}
        Spacer(modifier = Modifier.padding(10.dp))
        Text("Hide Text")
        // text to hide
        TextField()
    }

}