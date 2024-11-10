package id.deval.recipe.ui.upload.effect

sealed interface UploadScreenEffect {
    data class ShowToast(val message: String) : UploadScreenEffect
    data object NavigateToHome : UploadScreenEffect
    data object ShowDialog : UploadScreenEffect
    data object NavigateToDetail : UploadScreenEffect
    data object NavigateToSecondStep : UploadScreenEffect
}