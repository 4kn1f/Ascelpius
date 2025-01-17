package com.dicoding.asclepius.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.asclepius.R
import com.dicoding.asclepius.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //hasil gambar predict
        //confidence score
        val imageUriString = intent.getStringExtra(EXTRA_IMAGE)
        val imageUri = Uri.parse(imageUriString)
        val confidence = intent.getIntExtra(EXTRA_CONFIDENCE, 0)
        val prediction = intent.getStringExtra(EXTRA_PREDICTION)

        binding.resultImage.setImageURI(imageUri)
        binding.resultText.text = "Prediction: $prediction\nConfidence: $confidence%"
    }

    companion object{
        const val EXTRA_IMAGE = "extra_image"
        const val EXTRA_CONFIDENCE = "extra_confidence"
        const val EXTRA_PREDICTION = "extra_prediction"
    }


}