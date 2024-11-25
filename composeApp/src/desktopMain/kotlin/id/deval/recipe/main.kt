package id.deval.recipe

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import id.deval.recipe.ui.app.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KMM-Recipe",
    ) {
        App()
    }
}