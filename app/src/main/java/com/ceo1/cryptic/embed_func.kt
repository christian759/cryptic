package com.ceo1.cryptic

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.nio.ByteBuffer

@RequiresApi(Build.VERSION_CODES.O)
fun embedImageInImageAndroid(context: Context, coverImageUri: Uri?, hiddenImageUri: Uri?, outputFileName: String): Uri? {
    val coverImage = BitmapFactory.decodeStream(coverImageUri?.let { context.contentResolver.openInputStream(it) })
    val hiddenImage = BitmapFactory.decodeStream(hiddenImageUri?.let { context.contentResolver.openInputStream(it) })

    // Convert the hidden image to byte array
    val byteArrayOutputStream = ByteArrayOutputStream()
    hiddenImage.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    val hiddenImageBytes = byteArrayOutputStream.toByteArray()
    val hiddenImageSizeBytes = ByteBuffer.allocate(8).putLong(hiddenImageBytes.size.toLong()).array()

    // Combine cover image and hidden image
    val combinedData = ByteArrayOutputStream()
    coverImage.compress(Bitmap.CompressFormat.PNG, 100, combinedData)
    combinedData.write(hiddenImageBytes)
    combinedData.write(hiddenImageSizeBytes)

    // Save combined data to a file and get the Uri
    val resolver = context.contentResolver
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, outputFileName)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
    }
    val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

    uri?.let {
        resolver.openOutputStream(it).use { outputStream ->
            outputStream?.write(combinedData.toByteArray())
        }
        println("Hidden image embedded successfully in: $it")
    }

    return uri
}


@RequiresApi(Build.VERSION_CODES.O)
fun extractImageFromImageAndroid(context: Context, embeddedImageUri: Uri?, outputFileName: String) {
    val inputStream = embeddedImageUri?.let { context.contentResolver.openInputStream(it) } ?: run {
        println("Error: Could not read embedded image data.")
        return
    }

    val embeddedImageData = inputStream.readBytes()
    inputStream.close()

    // Read the last 8 bytes to get the size of the hidden image
    val hiddenImageSizeBytes = embeddedImageData.copyOfRange(embeddedImageData.size - 8, embeddedImageData.size)
    val hiddenImageSize = ByteBuffer.wrap(hiddenImageSizeBytes).long

    // Extract the hidden image bytes
    val hiddenImageBytes = embeddedImageData.copyOfRange(
        embeddedImageData.size - 8 - hiddenImageSize.toInt(),
        embeddedImageData.size - 8
    )

    // Save the extracted hidden image to a file and get the Uri
    val resolver = context.contentResolver
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, outputFileName)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
    }
    val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

    uri?.let {
        resolver.openOutputStream(it).use { outputStream ->
            outputStream?.write(hiddenImageBytes)
        }
        println("Hidden image extracted and saved successfully in: $uri")
    }
}

fun decodeTextFromImage(imagePath: String) {
    val image = BitmapFactory.decodeFile(imagePath)
    val bits = StringBuilder()

    outer@ for (y in 0 until image.height) {
        for (x in 0 until image.width) {
            val color = image.getPixel(x, y)
            val blue = Color.blue(color)
            bits.append(blue and 1)  // Extract LSB from blue channel

            if (bits.length % 8 == 0 && bits.takeLast(8).all { it == '0' }) break@outer  // Stop if stop sequence found
        }
    }

    val result =  bits.toString()
        .chunked(8)
        .map { it.toInt(2).toChar() }
        .joinToString("")
        .trimEnd('\u0000')

    println(result)
}


fun encodeTextInImage(imagePath: String, outputPath: String, text: String) {
    val image = BitmapFactory.decodeFile(imagePath)
    val binaryText = text.toByteArray().joinToString("") { it.toString(2).padStart(8, '0') } + "00000000"  // Add a stop sequence

    var index = 0
    outer@ for (y in 0 until image.height) {
        for (x in 0 until image.width) {
            if (index >= binaryText.length) break@outer

            val color = image.getPixel(x, y)
            val red = Color.red(color)
            val green = Color.green(color)
            val blue = Color.blue(color)

            val newBlue = (blue and 0xFE) or binaryText[index].digitToInt()  // Modify LSB of the blue channel
            image.setPixel(x, y, Color.rgb(red, green, newBlue))

            index++
        }
    }

    val outputFile = File(outputPath)
    FileOutputStream(outputFile).use { outputStream ->
        image.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    }
    println("Text encoded in image successfully.")
}