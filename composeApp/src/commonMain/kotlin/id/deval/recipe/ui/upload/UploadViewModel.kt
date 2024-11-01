package id.deval.recipe.ui.upload

import androidx.lifecycle.ViewModel
import id.deval.recipe.ui.upload.effect.UploadScreenEffect
import id.deval.recipe.ui.upload.event.UploadScreenEvent
import id.deval.recipe.ui.upload.state.UploadScreenState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

class UploadViewModel : ViewModel() {

    private val _uploadScreenState: MutableStateFlow<UploadScreenState> = MutableStateFlow(
        UploadScreenState()
    )
    val uploadScreenState : StateFlow<UploadScreenState> = _uploadScreenState

    private val _uploadScreenEffect : MutableSharedFlow<UploadScreenEffect> = MutableSharedFlow()
    val uploadScreenEffect : SharedFlow<UploadScreenEffect> = _uploadScreenEffect

    fun onEvent(event : UploadScreenEvent){
        when(event){
            is UploadScreenEvent.OnAddCoverImageClicked -> {}
            is UploadScreenEvent.OnAddStepImageClicked -> {}
            is UploadScreenEvent.OnAddIngredientClicked -> {}
            is UploadScreenEvent.OnNextClicked -> {}
            is UploadScreenEvent.OnBackClicked -> {}
            is UploadScreenEvent.OnNavigateToHome -> {}
            is UploadScreenEvent.OnFoodNameChanged -> {}
            is UploadScreenEvent.OnDescriptionChanged -> {}
            is UploadScreenEvent.OnIngredientChanged -> {}
            is UploadScreenEvent.OnStepChanged -> {}
        }
    }
}