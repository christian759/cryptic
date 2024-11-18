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
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ceo1.cryptic.ui.theme.*

@Composable
fun hide(){

    // first image info
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult( contract = ActivityResultContracts.GetContent() ) {
            uri: Uri? -> imageUri = uri
    }
    // same value of painter1
    // value going to be used for operation
    var refPainter1: AsyncImagePainter


    // second image info
    var imageUri2 by remember { mutableStateOf<Uri?>(null) }
    val context2 = LocalContext.current
    val launcher2 = rememberLauncherForActivityResult( contract = ActivityResultContracts.GetContent() ) {
            uri: Uri? -> imageUri2 = uri
    }
    // same value of painter2
    // value going to be used for operation
    var refPainter2: AsyncImagePainter


    Spacer(modifier = Modifier.padding(10.dp))

    Text("Hide Image", color = Color.White, fontSize = TextUnit(30f, TextUnitType.Sp),
        modifier = Modifier.padding(10.dp))


    // first card for cover image
    Text("Select a cover image", color = Color.White, fontSize = TextUnit(18f, TextUnitType.Sp),
        modifier = Modifier.padding(10.dp))

    Card(colors = CardColors(White, White, White, White),
        modifier = Modifier.padding(horizontal = 10.dp).height(70.dp)) {

        Row(modifier = Modifier.padding(3.dp)) {
            Button(border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .weight(0.5f)
                    .padding(5.dp)
                    .fillMaxHeight(),
                onClick = {
                    launcher.launch("image/*")
                }
            ) {
                Text("Pick an image", fontSize = TextUnit(18f, TextUnitType.Sp))
            }

            Spacer(modifier = Modifier.padding(2.dp).weight(0.1f))

            imageUri?.let {
                    uri -> val painter = rememberAsyncImagePainter( model = ImageRequest.Builder(context).data(uri).build())
                Image( painter = painter, contentDescription = "Selected Image", modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.3f))
            }

        }
    }

    Spacer(modifier = Modifier.padding(17.dp))


    // second image card, image to hide
    Text("Select an image to hide", color = Color.White, fontSize = TextUnit(18f, TextUnitType.Sp),
        modifier = Modifier.padding(10.dp))

    Card(colors = CardColors(White, White, White, White),
        modifier = Modifier.padding(horizontal = 10.dp).height(70.dp)) {

        Row(modifier = Modifier.padding(3.dp)) {
            Button(border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .weight(0.5f)
                    .padding(5.dp)
                    .fillMaxHeight(),
                onClick = {
                    launcher2.launch("image/*")
                }
            ) {
                Text("Pick an image", fontSize = TextUnit(18f, TextUnitType.Sp))
            }

            Spacer(modifier = Modifier.padding(2.dp).weight(0.1f))

            imageUri2?.let {
                uri2 -> val painter2 = rememberAsyncImagePainter( model = ImageRequest.Builder(context2).data(uri2).build() )
                refPainter2 = painter2
                Image( painter = painter2, contentDescription = "Selected Image", modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.3f))
            }

        }
    }


    // button for hiding
    Button(border = BorderStroke(1.dp, Color.White),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(25.dp),
        onClick = {

        }){
        Text("Hide Image", fontSize = TextUnit(18f, TextUnitType.Sp))
    }
}