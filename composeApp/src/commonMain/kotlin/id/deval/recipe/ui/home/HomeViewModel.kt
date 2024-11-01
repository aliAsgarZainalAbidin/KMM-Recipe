package id.deval.recipe.ui.home

import androidx.lifecycle.ViewModel
import id.deval.recipe.domain.model.Recipe
import id.deval.recipe.ui.home.effect.HomeScreenEffect
import id.deval.recipe.ui.home.event.HomeScreenEvent
import id.deval.recipe.ui.home.state.HomeScreenState
import id.deval.recipe.util.DataDummy
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {
    private var _homeScreenState: MutableStateFlow<HomeScreenState> = MutableStateFlow(
        HomeScreenState(recipes = DataDummy.dummyRecipes, filteredRecipes = DataDummy.dummyRecipes)
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
        _homeScreenState.update {
            it.copy(selectedRecipe = recipe)
        }
    }

    private fun onSearchQueryChanged(query: String) {
        _homeScreenState.update {
            it.copy(searchQuery = query, isSearching = true)
        }
    }

    private fun onSearchCloseClicked(){
        _homeScreenState.update {
            it.copy(isSearching = false)
        }
    }
}