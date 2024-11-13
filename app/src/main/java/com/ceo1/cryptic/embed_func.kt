package com.ceo1.cryptic

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.nio.ByteBuffer
import java.nio.file.Files

@RequiresApi(Build.VERSION_CODES.O)
fun embedFileInImage(imagePath: String, filePath: String, outputImagePath: String) {
    val imageFile = File(imagePath)
    val fileToEmbed = File(filePath)

    val image = BitmapFactory.decodeFile(imageFile.absolutePath)
    val fileData: ByteArray = Files.readAllBytes(fileToEmbed.toPath())
    val fileSizeBytes = ByteBuffer.allocate(8).putLong(fileData.size.toLong()).array()  // Store file size as 8 bytes

    val byteArrayOutputStream = ByteArrayOutputStream()
    image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    val imageData: ByteArray = byteArrayOutputStream.toByteArray()

    // Combine image data, file data, and file size at the end
    val combinedData = imageData + fileData + fileSizeBytes

    File(outputImagePath).writeBytes(combinedData)
    println("File embedded in image successfully with file size info!")
}

fun extractFileFromImage(imageWithFile: String, outputFilePath: String) {
    val combinedData = File(imageWithFile).readBytes()

    // Read the last 8 bytes to get the file size
    val fileSizeBytes = combinedData.copyOfRange(combinedData.size - 8, combinedData.size)
    val fileSize = ByteBuffer.wrap(fileSizeBytes).long

    // Check if the file size is reasonable
    if (fileSize <= 0 || fileSize > combinedData.size - 8) {
        println("Error: Invalid file size embedded in the image.")
        return
    }

    // Extract the hidden file data using the exact size
    val fileData = combinedData.copyOfRange(combinedData.size - 8 - fileSize.toInt(), combinedData.size - 8)

    // Save the extracted data to a new file
    File(outputFilePath).writeBytes(fileData)
    println("File extracted successfully!")
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
