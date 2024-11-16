package id.deval.recipe.ui.scan

import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.ObjectDetector
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions

private class RecipeAnalyzer : ImageAnalysis.Analyzer {

    private var objectDetector: ObjectDetector

    init {
        val options = ObjectDetectorOptions.Builder()
            .setDetectorMode(ObjectDetectorOptions.STREAM_MODE)
            .enableClassification()
            .enableMultipleObjects()
            .build()

        objectDetector = ObjectDetection.getClient(options)
    }

    @OptIn(ExperimentalGetImage::class)
    override fun analyze(image: ImageProxy) {
        val mediaImage = image.image
        if (mediaImage != null){
            val inputImage = InputImage.fromMediaImage(mediaImage, image.imageInfo.rotationDegrees)

            objectDetector.process(inputImage)
                .addOnSuccessListener { detectedObjects ->
                    Log.d("RecipeAnalyzer Success", "analyze: $detectedObjects")
                }
                .addOnFailureListener { e ->
                    Log.d("RecipeAnalyzer Error", "analyze: $e")
                }
        }
    }
}