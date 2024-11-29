package id.deval.recipe.ui.home

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.navigator.Navigator
import co.touchlab.kermit.Logger
import id.deval.recipe.domain.model.FilterCategory
import id.deval.recipe.domain.model.Recipe
import id.deval.recipe.ui.detail.RecipeViewModel
import id.deval.recipe.ui.detail.event.RecipeDetailEvent
import id.deval.recipe.ui.home.effect.HomeScreenEffect
import id.deval.recipe.ui.home.event.HomeScreenEvent
import id.deval.recipe.ui.home.state.HomeScreenState
import id.deval.recipe.ui.navigation.AppNavigation
import id.deval.recipe.util.DataDummy
import id.deval.recipe.util.RecipeSliderValue
import id.deval.recipe.util.launchCatchError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {
    private var _homeScreenState: MutableStateFlow<HomeScreenState> = MutableStateFlow(
        HomeScreenState(recipes = DataDummy.recipes, filteredRecipes = DataDummy.recipes)
    )
    val homeScreenState: StateFlow<HomeScreenState> = _homeScreenState

    private var _homeScreenEffect: MutableSharedFlow<HomeScreenEffect> = MutableSharedFlow()
    val homeScreenEffect: SharedFlow<HomeScreenEffect> = _homeScreenEffect


    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.OnCategorySelected -> {
                onCategorySelected(event.category)
            }

            is HomeScreenEvent.OnLikeClicked -> {
                onLikeClicked(event.recipe)
            }

            is HomeScreenEvent.OnRecipeClicked -> {
                onRecipeClicked(event.recipe)
            }

            is HomeScreenEvent.OnSearchQueryChanged -> {
                onSearchQueryChanged(event.query)
            }

            is HomeScreenEvent.OnCloseSearchField -> {
                onSearchCloseClicked()
            }

            is HomeScreenEvent.OnFilterClicked -> {
                onFilterClicked(event.state)
            }

            is HomeScreenEvent.OnDurationChanged -> {
                onDurationFilterChanged(event.duration)
            }

            is HomeScreenEvent.OnFilterDoneClicked -> {
                onFilterDoneClicked(event.duration, event.category, event.modalState)
            }
        }
    }

    private fun onCategorySelected(category: FilterCategory) {
        _homeScreenState.update {
            val updatedRecipes = homeScreenState.value.recipes.filter { rec ->
                (rec != null && rec.category == category.plainName || category.plainName == FilterCategory.default.plainName)
            }

            it.copy(selectedCategory = category, filteredRecipes = updatedRecipes)
        }
    }

    private fun onLikeClicked(recipe: Recipe) {
        _homeScreenState.update {
            val updatedRecipe = homeScreenState.value.filteredRecipes.map { rec ->
                if (rec?.id != null && rec.id == recipe.id) {
                    rec.copy(isLiked = !rec.isLiked)
                } else {
                    rec
                }
            }

            it.copy(filteredRecipes = updatedRecipe)
        }
    }

    private fun onRecipeClicked(recipe: Recipe) {
        CoroutineScope(Dispatchers.Default).launchCatchError(
            block = {
                _homeScreenState.update {
                    it.copy(selectedRecipe = recipe)
                }
                _homeScreenEffect.emit(HomeScreenEffect.NavigateToDetail(recipe))
            },
            onError = {
                Logger.d(
                    tag = TAG,
                    messageString = "Error onRecipeClicked: ${it.message}"
                )
            }
        )
    }

    private fun onSearchQueryChanged(query: String) {
        _homeScreenState.update {
            it.copy(searchQuery = query, isSearching = true)
        }
    }

    private fun onSearchCloseClicked() {
        _homeScreenState.update {
            it.copy(isSearching = false)
        }
    }

    private fun onFilterClicked(state: Boolean) {
        _homeScreenState.update {
            it.copy(showBottomModalFilter = state)
        }
    }

    private fun onDurationFilterChanged(duration: String) {
        _homeScreenState.update {
            it.copy()
        }
    }

    private fun onFilterDoneClicked(
        duration: RecipeSliderValue,
        category: FilterCategory,
        modalState: Boolean
    ) {
        _homeScreenState.update {
            it.copy(
                selectedDurationRecipe = duration,
                selectedCategory = category,
                showBottomModalFilter = modalState
            )
        }
    }

    fun onEffect(effect: HomeScreenEffect, localNavigator: Navigator? = null, recipeViewModel: RecipeViewModel) {
        when (effect) {
            is HomeScreenEffect.NavigateToDetail -> {
                recipeViewModel.onEvent(RecipeDetailEvent.OnRecipeClicked(effect.recipe))
                localNavigator?.parent?.push(AppNavigation.RecipeDetail.screen)
            }

            is HomeScreenEffect.OnChangedSearchQuery -> {}
            is HomeScreenEffect.OnChangedFilterCategory -> {}
        }
    }

    companion object{
        const val TAG = "HomeViewModel"
    }
}