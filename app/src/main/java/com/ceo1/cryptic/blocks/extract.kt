package com.ceo1.cryptic.blocks

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.*
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ceo1.cryptic.ui.theme.*

@Composable
fun extract(){
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult( contract = ActivityResultContracts.GetContent() ) {
            uri: Uri? -> imageUri = uri
    }


    Card(colors = CardColors(White, White, White, White),
        modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp)) {
        Text("Upload the Cover Image",
            color = Black,
            fontSize = TextUnit(20f, TextUnitType.Sp),
            modifier = Modifier.padding(12.dp)
        )

        Button(modifier = Modifier.padding(5.dp),
            border = BorderStroke(3.dp, Color.White),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonColors(Color.White, DarkPurple, Black, Black),
            onClick = {

            }
        ){
            Row{
                Text("Select your image")
                Icon(imageVector = Icons.Default.AccountBox, "upload image")
            }
        }

        imageUri?.let {
                uri -> val painter = rememberAsyncImagePainter( model = ImageRequest.Builder(context).data(uri).build() )
            Image( painter = painter, contentDescription = "Selected Image", modifier = Modifier.size(300.dp).padding(16.dp) )
        }
    }

    Spacer(modifier = Modifier.padding(60.dp))


    Text("Note: only images and text that have been hidden by this app can be extracted",
        color = Black)


    Column( modifier = Modifier.fillMaxSize().padding(16.dp) )
    {
        Button(onClick = {
            launcher.launch("image/*")
        })
        {
            Text("Select Image")
        }

        Spacer(modifier = Modifier.height(16.dp))

    }

}