package com.ceo1.cryptic.blocks

// imports
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import coil.compose.rememberAsyncImagePainter
import coil.request.*
import com.ceo1.cryptic.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun hide(primaryTheme: Theme){
    val context = LocalContext.current

    // first image info
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult( contract = ActivityResultContracts.GetContent() ) {
            uri: Uri? -> imageUri = uri
    }

    // second image info
    var imageUri2 by remember { mutableStateOf<Uri?>(null) }
    val launcher2 = rememberLauncherForActivityResult( contract = ActivityResultContracts.GetContent() ) {
            uri: Uri? -> imageUri2 = uri
    }

    Spacer(modifier = Modifier.padding(10.dp))

    Text("Hide Image", color = primaryTheme.color3, fontSize = TextUnit(30f, TextUnitType.Sp), fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(10.dp))

    Card(colors = CardColors(primaryTheme.color2, primaryTheme.color2, primaryTheme.color2, primaryTheme.color2),
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp).height(115.dp)) {
        // first card for cover image
        Text("Select a cover image", color = primaryTheme.color3, fontSize = TextUnit(18f, TextUnitType.Sp),
            modifier = Modifier.padding(10.dp))

        Row(modifier = Modifier.padding(5.dp)) {
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

            Spacer(modifier = Modifier.padding(2.dp).weight(0.1f))
            imageUri?.let {
                    uri -> val painter = rememberAsyncImagePainter( model = ImageRequest.Builder(context).data(uri).build())
                Image( painter = painter, contentDescription = "Selected Image", modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.3f))
            }}}

    Spacer(modifier = Modifier.padding(15.dp))

    // second image card, image to hide
    Card(colors = CardColors(primaryTheme.color2, primaryTheme.color2, primaryTheme.color2,
        primaryTheme.color2),
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp).height(115.dp)) {

        Text("Select an image to hide", color = primaryTheme.color3, fontSize = TextUnit(18f, TextUnitType.Sp),
            modifier = Modifier.padding(10.dp))

        Row(modifier = Modifier.padding(3.dp)) {
            Button(colors = ButtonColors(containerColor = primaryTheme.color3, contentColor = primaryTheme.color1, disabledContentColor = Color.Unspecified,
                disabledContainerColor = Color.Unspecified),
                border = BorderStroke(5.dp, primaryTheme.color1),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .weight(0.5f)
                    .padding(5.dp)
                    .fillMaxHeight(),
                onClick = {
                    println("done")
                    launcher2.launch("image/*") }
            ) { Text("Pick an image", fontSize = TextUnit(18f, TextUnitType.Sp))}

            Spacer(modifier = Modifier.padding(2.dp).weight(0.1f))

            imageUri2?.let {
                uri2 -> val painter2 = rememberAsyncImagePainter( model = ImageRequest
                    .Builder(context)
                    .data(uri2)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .build()
                )
                Image( painter = painter2, contentDescription = "Selected Image", modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.3f))
            }}}

    // button for hiding
    Button(border = BorderStroke(1.dp, Color.White),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(25.dp),
        onClick = {

       if ((imageUri != null) and (imageUri2 != null)) {
           embedImageInImageAndroid(context = context, coverImageUri = imageUri, hiddenImageUri = imageUri2, "embedded_image.png")
       }else{
           Toast.makeText(context, "Please select both image", Toast.LENGTH_LONG).show()
       }
        }){
        Text("Hide Image", fontSize = TextUnit(18f, TextUnitType.Sp))
    }
}