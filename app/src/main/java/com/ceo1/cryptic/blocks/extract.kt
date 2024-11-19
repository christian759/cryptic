package com.ceo1.cryptic.blocks

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
    val context = LocalContext.current

    var imageUri3 by remember { mutableStateOf<Uri?>(null) }

    val launcher3 = rememberLauncherForActivityResult( contract = ActivityResultContracts.GetContent() ) {
            uri: Uri? -> imageUri3 = uri
    }

    Card(colors = CardColors(White2, White2, White2, White2),
        modifier = Modifier.padding(horizontal = 10.dp).height(70.dp)){

        Row(modifier = Modifier.padding(3.dp)){
            Button(border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .weight(0.5f)
                    .padding(5.dp)
                    .fillMaxHeight(),
                onClick = {
                    launcher3.launch("image/*")
                }
            ){
                Text("pick an image", fontSize = TextUnit(18f, TextUnitType.Sp))
            }



            imageUri3?.let {
                    uri -> val painter = rememberAsyncImagePainter( model = ImageRequest.Builder(context).data(uri).build() )
                Image( painter = painter, contentDescription = "Selected Image", modifier = Modifier.size(300.dp).padding(16.dp) )
            }
        }
    }


    Spacer(modifier = Modifier.padding(60.dp))


    Text("Note: only images and text that have been hidden by this app can be extracted",
        color = Black)




}