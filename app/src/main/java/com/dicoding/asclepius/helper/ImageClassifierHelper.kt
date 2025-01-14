package com.dicoding.asclepius.helper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import android.net.Uri
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.vision.classifier.Classifications
import org.tensorflow.lite.task.vision.classifier.ImageClassifier
import java.io.IOException
import java.io.InputStream


class ImageClassifierHelper(var threshold: Float = 0.1f, val maxResults: Int = 3, val modelName: String = "cancer_classification.tflite", val context: Context) {

    private var imageClassifier: ImageClassifier? = null

    init {
        setupImageClassifier()
    }

    private fun setupImageClassifier() {
        //untuk memproses gambar
        val optionsBuilder = ImageClassifier.ImageClassifierOptions.builder()
            .setScoreThreshold(threshold)
            .setMaxResults(maxResults)
        val baseOptionsBuilder = BaseOptions.builder()
            .setNumThreads(4)
        optionsBuilder.setBaseOptions(baseOptionsBuilder.build())

        try {
            imageClassifier = ImageClassifier.createFromFileAndOptions(
                context,
                modelName,
                optionsBuilder.build()
            )
        }catch (e: IOException){
            e.printStackTrace()
        }
    }

    fun classifyStaticImage(imageUri: Uri): List<Classifications>? {
        //klasifikasi image dari gambar
        return try {
            if (imageClassifier == null) {
                setupImageClassifier()
            }

            val inputStream: InputStream? = context.contentResolver.openInputStream(imageUri)
            val bitmap = BitmapFactory.decodeStream(inputStream)

            if (bitmap != null) {
                val tensorImage = TensorImage.fromBitmap(bitmap)
                imageClassifier?.classify(tensorImage)
            } else {
                null
            }

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    companion object {
        private const val TAG = "ImageClassifierHelper"
    }
}