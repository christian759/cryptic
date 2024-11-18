package com.ceo1.cryptic

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.ByteBuffer

@RequiresApi(Build.VERSION_CODES.O)
fun embedImageInImageAndroid(context: Context, coverImageUri: Uri?, hiddenImageUri: Uri?, outputFileName: String) {

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

    // Save combined data to a file
    val outputDir = context.getExternalFilesDir(null) // App's external files directory
    val outputFile = File(outputDir, outputFileName)
    outputFile.writeBytes(combinedData.toByteArray())
    println("Hidden image embedded successfully in: ${outputFile.absolutePath}")
}

@RequiresApi(Build.VERSION_CODES.O)
fun extractImageFromImageAndroid(context: Context, embeddedImageUri: Uri, outputFileName: String) {
    val embeddedImageData = context.contentResolver.openInputStream(embeddedImageUri)?.readBytes()

    if (embeddedImageData == null) {
        println("Error: Could not read embedded image data.")
        return
    }

    // Read the last 8 bytes to get the size of the hidden image
    val hiddenImageSizeBytes = embeddedImageData.copyOfRange(embeddedImageData.size - 8, embeddedImageData.size)
    val hiddenImageSize = ByteBuffer.wrap(hiddenImageSizeBytes).long

    if (hiddenImageSize <= 0 || hiddenImageSize > embeddedImageData.size - 8) {
        println("Error: Invalid size for the hidden image.")
        return
    }

    // Extract the hidden image bytes
    val hiddenImageBytes = embeddedImageData.copyOfRange(
        embeddedImageData.size - 8 - hiddenImageSize.toInt(),
        embeddedImageData.size - 8
    )

    // Save the extracted hidden image to a file
    val outputDir = context.getExternalFilesDir(null) // App's external files directory
    val outputFile = File(outputDir, outputFileName)
    outputFile.writeBytes(hiddenImageBytes)
    println("Hidden image extracted and saved successfully in: ${outputFile.absolutePath}")
}