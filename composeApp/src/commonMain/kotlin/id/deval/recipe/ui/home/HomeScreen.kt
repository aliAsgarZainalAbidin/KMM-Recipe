package id.deval.recipe.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import id.deval.recipe.components.RecipeButton
import id.deval.recipe.components.RecipeCommonUI
import id.deval.recipe.components.RecipeLazyItem
import id.deval.recipe.components.RecipeTextField
import id.deval.recipe.di.appRecipeModule
import id.deval.recipe.domain.model.FilterCategory
import id.deval.recipe.theme.DefaultFilledButtonStyle
import id.deval.recipe.theme.mainTextColor
import id.deval.recipe.ui.home.effect.HomeScreenEffect
import id.deval.recipe.ui.home.event.HomeScreenEvent
import id.deval.recipe.ui.home.state.HomeScreenState
import id.deval.recipe.ui.navigation.AppNavigation
import id.deval.recipe.ui.detail.RecipeViewModel
import id.deval.recipe.ui.detail.event.RecipeDetailEvent
import id.deval.recipe.util.RecipeSliderValue
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.add_filter
import kmm_recipe.composeapp.generated.resources.back
import kmm_recipe.composeapp.generated.resources.cancel
import kmm_recipe.composeapp.generated.resources.category
import kmm_recipe.composeapp.generated.resources.circle_close
import kmm_recipe.composeapp.generated.resources.done
import kmm_recipe.composeapp.generated.resources.filter
import kmm_recipe.composeapp.generated.resources.search
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.kodein.di.instance

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val homeScreenViewModel by appRecipeModule.instance<HomeViewModel>()
        val homeScreenState by homeScreenViewModel.homeScreenState.collectAsStateWithLifecycle()
        val localNavigator = LocalNavigator.currentOrThrow

        val recipeViewModel by appRecipeModule.instance<RecipeViewModel>()

        LaunchedEffect(Unit) {
            homeScreenViewModel.homeScreenEffect.collectLatest { effect ->
                homeScreenViewModel.onEffect(effect, localNavigator, recipeViewModel)
            }
        }

        HomeScreenContent(
            homeScreenState,
            homeScreenViewModel::onEvent
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun HomeScreenContent(
        state: HomeScreenState,
        onEvent: (HomeScreenEvent) -> Unit
    ) {
        val scrollBehavior =
            TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
        val isShowNavFilterIcon = state.searchQuery.isNotEmpty() && state.isSearching

        Scaffold(
            modifier = Modifier.fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                CenterAlignedTopAppBar(
                    modifier = Modifier.padding(top = 16.dp, start = 24.dp, end = 24.dp),
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors().copy(
                        containerColor = Color.Transparent,
                        scrolledContainerColor = Color.Transparent,
                    ),
                    navigationIcon = {
                        if (isShowNavFilterIcon) {
                            Icon(
                                painter = painterResource(Res.drawable.back),
                                contentDescription = "back",
                                modifier = Modifier.clickable {
                                    onEvent(HomeScreenEvent.OnCloseSearchField)
                                }
                                    .padding(4.dp),
                                tint = mainTextColor
                            )
                        }
                    },
                    actions = {
                        if (isShowNavFilterIcon) {
                            Icon(
                                painter = painterResource(Res.drawable.filter),
                                contentDescription = "filter",
                                modifier = Modifier.clickable {
                                    onEvent(HomeScreenEvent.OnFilterClicked(true))
                                }
                            )
                        }
                    },
                    title = {
                        RecipeTextField.Filled(
                            value = state.searchQuery,
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = {
                                onEvent(HomeScreenEvent.OnSearchQueryChanged(it))
                            },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(Res.drawable.search),
                                    contentDescription = "search",
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                            },
                            trailingIcon = {
                                if (state.isSearching) {
                                    Icon(
                                        painter = painterResource(Res.drawable.circle_close),
                                        contentDescription = "close",
                                        tint = MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.clickable {
                                            onEvent(HomeScreenEvent.OnSearchQueryChanged(""))
                                            onEvent(HomeScreenEvent.OnCloseSearchField)
                                        }
                                    )
                                }
                            },
                            placeholder = {
                                Text(
                                    text = stringResource(Res.string.search),
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        )
                    },
                    scrollBehavior = scrollBehavior
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                Text(
                    text = stringResource(Res.string.category),
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 8.dp),
                    textAlign = TextAlign.Start
                )
                RecipeCommonUI.FilterCategory(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    categories = state.filterCategories,
                    stateCategory = state.selectedCategory,
                    onEvent = onEvent
                )
                RecipeCommonUI.RecipeSpacer()
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize()
                        .padding(start = 24.dp, end = 24.dp, bottom = 80.dp),
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(state.filteredRecipes) {
                        if (it != null) {
                            RecipeLazyItem.RecipeDefaultItem(
                                modifier = Modifier
                                    .padding(vertical = 4.dp),
                                recipe = it,
                                onClick = {
                                    onEvent(HomeScreenEvent.OnRecipeClicked(it))
                                },
                                likeButton = {
                                    RecipeButton.LikeIconButton(
                                        onClick = { onEvent(HomeScreenEvent.OnLikeClicked(it)) },
                                        checked = it.isLiked,
                                        modifier = Modifier
                                            .padding(16.dp).align(Alignment.TopEnd)
                                    )
                                }
                            )
                        }
                    }
                }

                if (state.showBottomModalFilter) {
                    FilterBottomModal(Modifier, state, onEvent)
                }
            }
        }
    }


    @Composable
    fun ChipCategory(
        modifier: Modifier,
        categories: List<FilterCategory>,
        stateCategory: FilterCategory,
        onEvent: (HomeScreenEvent) -> Unit
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp)
        ) {
            repeat(categories.size) { index ->
                val isSelected = stateCategory == categories[index]
                FilterChip(
                    modifier = Modifier.padding(end = if (index <= categories.size) 4.dp else 0.dp),
                    selected = isSelected,
                    onClick = {
                        onEvent(HomeScreenEvent.OnCategorySelected(categories[index]))
                    },
                    label = {
                        Text(
                            text = stringResource(categories[index].name),
                            style = MaterialTheme.typography.headlineSmall.copy(
                                color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                            )
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors().copy(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                    )
                )
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun FilterBottomModal(
        modifier: Modifier = Modifier,
        state: HomeScreenState,
        onEvent: (HomeScreenEvent) -> Unit
    ) {
        val startSliderValue = 0f
        val endSliderValue = 1f
        var sliderValue by remember { mutableStateOf(RecipeSliderValue.LESS_THAN_10) }

        val sliderState by remember {
            mutableStateOf(
                SliderState(
                    value = 0f,
                    steps = 1,
                    valueRange = startSliderValue..endSliderValue,
                )
            )
        }
        sliderState.onValueChangeFinished = {
            sliderValue = when (sliderState.value) {
                RecipeSliderValue.LESS_THAN_10.value -> RecipeSliderValue.LESS_THAN_10
                RecipeSliderValue.EQUAL_30.value -> RecipeSliderValue.EQUAL_30
                RecipeSliderValue.MORE_THAN_60.value -> RecipeSliderValue.MORE_THAN_60
                else -> RecipeSliderValue.LESS_THAN_10
            }
        }

        val tempCategories by remember { mutableStateOf(state.filterCategories) }
        var tempSelectedCategory by remember { mutableStateOf(state.selectedCategory) }

        ModalBottomSheet(
//            modifier = Modifier.fillMaxSize(),
            onDismissRequest = {
                onEvent(HomeScreenEvent.OnFilterClicked(false))
            },
            sheetState = rememberModalBottomSheetState()
        ) {
            Column(
                modifier = modifier.fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(Res.string.add_filter),
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(
                        bottom = 12.dp
                    )
                )
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(Res.string.category),
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)
                    )
                    RecipeCommonUI.FilterCategoryCallback(
                        modifier = Modifier,
                        categories = tempCategories,
                        stateCategory = tempSelectedCategory,
                        callback = {
                            tempSelectedCategory = it
                        }
                    )
                }
                RecipeCommonUI.RecipeDurationSlider(sliderState, startSliderValue, endSliderValue)
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RecipeButton.DefaultFilledButton(
                        modifier = Modifier.weight(1f),
                        onClick = { onEvent(HomeScreenEvent.OnFilterClicked(false)) },
                        text = stringResource(Res.string.cancel),
                        color = DefaultFilledButtonStyle().copy(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                            contentColor = mainTextColor,
                        )
                    )
                    RecipeButton.DefaultFilledButton(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            onEvent(
                                HomeScreenEvent.OnFilterDoneClicked(
                                    sliderValue,
                                    tempSelectedCategory,
                                    false
                                )
                            )
                        },
                        text = stringResource(Res.string.done)
                    )
                }
            }
        }
    }

}