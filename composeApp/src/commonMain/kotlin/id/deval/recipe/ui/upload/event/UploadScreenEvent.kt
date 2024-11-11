package id.deval.recipe.ui.upload.event

sealed interface UploadScreenEvent {
    data object OnAddCoverImageClicked : UploadScreenEvent
    data object OnAddStepImageClicked : UploadScreenEvent
    data class OnAddStepClicked(val id: Int) : UploadScreenEvent
    data object OnAddIngredientClicked : UploadScreenEvent
    data class OnDragIngredient(val fromIndex: Int, val toIndex: Int) : UploadScreenEvent
    data object OnNextClicked : UploadScreenEvent
    data object OnBackClicked : UploadScreenEvent
    data class ShowDialog(val showDialog: Boolean) : UploadScreenEvent
    data object NavigateToHome : UploadScreenEvent
    data class OnFoodNameChanged(val foodName: String) : UploadScreenEvent
    data class OnDescriptionChanged(val description: String) : UploadScreenEvent
    data class OnIngredientChanged(val ingredient: String, val index: Int) : UploadScreenEvent
    data class OnDurationChanged(val duration: String) : UploadScreenEvent
    data class OnStepChanged(val idStep: Int, val stepDescription: String) : UploadScreenEvent
}