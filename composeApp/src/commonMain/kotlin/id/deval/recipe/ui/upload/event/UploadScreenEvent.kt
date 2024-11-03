package id.deval.recipe.ui.upload.event

import id.deval.recipe.ui.upload.state.UploadScreenState

sealed interface UploadScreenEvent {
    data object OnAddCoverImageClicked : UploadScreenEvent
    data object OnAddStepImageClicked : UploadScreenEvent
    data object OnAddIngredientClicked : UploadScreenEvent
    data object OnNextClicked : UploadScreenEvent
    data object OnBackClicked : UploadScreenEvent
    data object OnNavigateToHome : UploadScreenEvent
    data class OnFoodNameChanged(val foodName: String) : UploadScreenEvent
    data class OnDescriptionChanged(val description: String) : UploadScreenEvent
    data class OnIngredientChanged(val ingredient: String) : UploadScreenEvent
    data class OnDurationChanged(val duration : String) : UploadScreenEvent
    data class OnStepChanged(val step: String) : UploadScreenEvent
}