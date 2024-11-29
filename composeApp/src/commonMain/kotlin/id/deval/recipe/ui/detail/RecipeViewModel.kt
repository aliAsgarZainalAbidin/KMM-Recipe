package id.deval.recipe.ui.detail

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.navigator.Navigator
import co.touchlab.kermit.Logger
import id.deval.recipe.domain.model.Recipe
import id.deval.recipe.domain.model.User
import id.deval.recipe.ui.detail.effect.RecipeDetailEffect
import id.deval.recipe.ui.detail.event.RecipeDetailEvent
import id.deval.recipe.ui.detail.state.RecipeDetailState
import id.deval.recipe.util.launchCatchError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RecipeViewModel : ViewModel() {
    private var _recipeDetailState: MutableStateFlow<RecipeDetailState> =
        MutableStateFlow(RecipeDetailState())
    val recipeDetailState: StateFlow<RecipeDetailState> = _recipeDetailState

    private var _recipeDetailEffect: MutableSharedFlow<RecipeDetailEffect> = MutableSharedFlow()
    val recipeDetailEffect: SharedFlow<RecipeDetailEffect> = _recipeDetailEffect


    fun onEffect(effect: RecipeDetailEffect, navigator: Navigator? =null){
        when (effect) {
            is RecipeDetailEffect.OnNavigateBack -> {
                navigator?.pop()
            }

            is RecipeDetailEffect.OnRecipeOwnerClicked -> {

            }
        }
    }

    fun onEvent(event: RecipeDetailEvent) {
        when (event) {
            is RecipeDetailEvent.OnRecipeOwnerClicked -> {
                onRecipeOwnerClicked(event.user)
            }

            is RecipeDetailEvent.OnNavigateBackClicked -> {
                onNavigateBackClicked()
            }

            is RecipeDetailEvent.OnRecipeClicked -> {
                onRecipeClicked(event.recipe)
            }
        }
    }

    private fun onRecipeOwnerClicked(user: User) {
        CoroutineScope(Dispatchers.Default).launchCatchError(
            block = {
                _recipeDetailEffect.emit(
                    RecipeDetailEffect.OnRecipeOwnerClicked(user)
                )
            },
            onError = {
                Logger.d(
                    tag = TAG,
                    messageString = "Error onRecipeOwnerClicked $it"
                )
            }
        )
    }

    private fun onNavigateBackClicked(){
        CoroutineScope(Dispatchers.Default).launchCatchError(
            block = {
                _recipeDetailEffect.emit(
                    RecipeDetailEffect.OnNavigateBack
                )
            },
            onError = {
                Logger.d(
                    tag = TAG,
                    messageString = "Error onNavigateBackClicked $it"
                )
            }
        )
    }

    private fun onRecipeClicked(recipe: Recipe){
        _recipeDetailState.update {
            it.copy(
                recipe = recipe
            )
        }
    }

    companion object {
        const val TAG = "RecipeViewModel"
    }

}