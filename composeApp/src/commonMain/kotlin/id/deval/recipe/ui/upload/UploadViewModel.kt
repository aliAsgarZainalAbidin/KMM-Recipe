package id.deval.recipe.ui.upload

import androidx.lifecycle.ViewModel
import co.touchlab.kermit.Logger
import id.deval.recipe.domain.model.RecipeStep
import id.deval.recipe.ui.upload.effect.UploadScreenEffect
import id.deval.recipe.ui.upload.event.UploadScreenEvent
import id.deval.recipe.ui.upload.state.UploadScreenState
import id.deval.recipe.util.launchCatchError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class UploadViewModel : ViewModel() {

    private val _uploadScreenState: MutableStateFlow<UploadScreenState> = MutableStateFlow(
        UploadScreenState()
    )
    val uploadScreenState: StateFlow<UploadScreenState> = _uploadScreenState

    private val _uploadScreenEffect: MutableSharedFlow<UploadScreenEffect> = MutableSharedFlow()
    val uploadScreenEffect: SharedFlow<UploadScreenEffect> = _uploadScreenEffect

    fun onEvent(event: UploadScreenEvent) {
        when (event) {
            is UploadScreenEvent.OnAddCoverImageClicked -> {}
            is UploadScreenEvent.OnAddStepImageClicked -> {}
            is UploadScreenEvent.OnAddStepClicked -> {
                onAddStep(event.id)
            }

            is UploadScreenEvent.OnAddIngredientClicked -> {
                onAddIngredient()
            }

            is UploadScreenEvent.OnDragIngredient -> {
                onDraggable(event.fromIndex, event.toIndex)
            }

            is UploadScreenEvent.OnNextClicked -> {
                onNextClicked(event.navigateToSecondStep)
            }

            is UploadScreenEvent.OnBackClicked -> {}
            is UploadScreenEvent.OnNavigateToHome -> {}
            is UploadScreenEvent.OnFoodNameChanged -> {
                onFoodNameChanged(event.foodName)
            }

            is UploadScreenEvent.OnDescriptionChanged -> {
                onDescriptionChanged(event.description)
            }

            is UploadScreenEvent.OnIngredientChanged -> {
                onIngredientChanged(event.ingredient, event.index)
            }

            is UploadScreenEvent.OnStepChanged -> {
                onStepChanged(event.idStep, event.stepDescription)
            }
            is UploadScreenEvent.OnDurationChanged -> {
                onDurationChanged(event.duration)
            }
        }
    }

    private fun onAddIngredient() {
        val updatedIngredient = _uploadScreenState.value.ingredients.toMutableList()
        updatedIngredient.add("")
        _uploadScreenState.update {
            it.copy(
                ingredients = updatedIngredient
            )
        }
    }

    private fun onFoodNameChanged(foodName: String) {
        _uploadScreenState.update {
            it.copy(foodName = foodName)
        }
    }

    private fun onDescriptionChanged(description: String) {
        _uploadScreenState.update {
            it.copy(description = description)
        }
    }

    private fun onDurationChanged(duration: String) {
        _uploadScreenState.update {
            it.copy(duration = duration)
        }
    }

    private fun onNextClicked(navigateToSecondStep: Boolean) {
        CoroutineScope(Dispatchers.Default).launchCatchError(
            block = {
                if (navigateToSecondStep) {
                    _uploadScreenEffect.emit(UploadScreenEffect.NavigateToSecondStep)
                } else {
                    _uploadScreenEffect.emit(UploadScreenEffect.ShowDialog)
                }
            },
            onError = {
                Logger.d(
                    tag = TAG,
                    messageString = it.message.toString()
                )
            }
        )
    }

    private fun onDraggable(fromIndex: Int, toIndex: Int) {
        _uploadScreenState.update {
            it.copy(
                ingredients = it.ingredients.toMutableList()
                    .apply { add(toIndex, removeAt(fromIndex)) }
            )
        }
    }

    private fun onAddStep(id : Int) {
        val updatedSteps = _uploadScreenState.value.steps.toMutableList()
        updatedSteps.add(RecipeStep(id, "", null))
        _uploadScreenState.update {
            it.copy(steps = updatedSteps)
        }
    }

    private fun onStepChanged(id: Int, description: String) {
        val updatedSteps = _uploadScreenState.value.steps.mapIndexed { i, recipeStep ->
            if (id == i) {
                val newDescription = recipeStep.description.replace(
                    recipeStep.description,
                    description
                )
                recipeStep.copy(description = newDescription)
            } else recipeStep
        }

        _uploadScreenState.update {
            it.copy(
                steps = updatedSteps
            )
        }
    }

    private fun onIngredientChanged(
        ingredient: String,
        index: Int
    ) {
        val updatedIngredient = _uploadScreenState.value.ingredients.mapIndexed { i, s ->
            if (i == index) {
                s.replace(s, ingredient)
            } else {
                s
            }
        }

        _uploadScreenState.update {
            it.copy(
                ingredients = updatedIngredient
            )
        }
    }

    companion object {
        const val TAG = "UploadViewModel"
    }
}