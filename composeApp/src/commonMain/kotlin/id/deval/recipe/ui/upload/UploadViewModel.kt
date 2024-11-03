package id.deval.recipe.ui.upload

import androidx.lifecycle.ViewModel
import id.deval.recipe.ui.upload.effect.UploadScreenEffect
import id.deval.recipe.ui.upload.event.UploadScreenEvent
import id.deval.recipe.ui.upload.state.UploadScreenState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

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
            is UploadScreenEvent.OnFoodNameChanged -> {
                onFoodNameChanged(event.foodName)
            }
            is UploadScreenEvent.OnDescriptionChanged -> {
                onDescriptionChanged(event.description)
            }
            is UploadScreenEvent.OnIngredientChanged -> {}
            is UploadScreenEvent.OnStepChanged -> {}
            is UploadScreenEvent.OnDurationChanged -> {
                onDurationChanged(event.duration)
            }
        }
    }

    private fun onFoodNameChanged(foodName : String){
        _uploadScreenState.update {
            it.copy(foodName = foodName)
        }
    }

    private fun onDescriptionChanged(description : String){
        _uploadScreenState.update {
            it.copy(description = description)
        }
    }

    private fun onDurationChanged(duration : String){
        _uploadScreenState.update {
            it.copy(duration = duration)
        }
    }
}