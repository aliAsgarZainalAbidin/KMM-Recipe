package id.deval.recipe.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import id.deval.recipe.components.RecipeButton
import id.deval.recipe.components.RecipeLazyItem
import id.deval.recipe.components.RecipeTextField
import id.deval.recipe.di.appRecipeModule
import id.deval.recipe.theme.mainTextColor
import id.deval.recipe.ui.home.effect.HomeScreenEffect
import id.deval.recipe.ui.home.event.HomeScreenEvent
import id.deval.recipe.ui.home.state.HomeScreenState
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.back
import kmm_recipe.composeapp.generated.resources.category
import kmm_recipe.composeapp.generated.resources.circle_close
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

        LaunchedEffect(Unit) {
            homeScreenViewModel.homeScreenEffect.collectLatest { effect ->
                when (effect) {
                    is HomeScreenEffect.NavigateToDetail -> {}
                    is HomeScreenEffect.OnChangedSearchQuery -> {}
                    is HomeScreenEffect.OnChangedFilterCategory -> {}
                }
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
        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
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
                                tint = mainTextColor
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
                FilterCategory(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    categories = state.filterCategories,
                    stateCategory = state.selectedCategory,
                    onEvent = onEvent
                )
                Spacer(
                    modifier = Modifier.height(8.dp)
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.tertiary)
                )
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
                                modifier = Modifier.padding(vertical = 4.dp),
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
            }
        }
    }

    @Composable
    fun FilterCategory(
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
                RecipeButton.DefaultFilledChipButton(
                    onClick = {
                        onEvent(HomeScreenEvent.OnCategorySelected(categories[index]))
                    },
                    modifier = Modifier.wrapContentSize()
                        .padding(end = 8.dp)
                        .defaultMinSize(minWidth = 56.dp),
                    text = categories[index].name,
                    textStyle = MaterialTheme.typography.headlineSmall.copy(
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Normal
                    ),
                    enabled = stateCategory == categories[index]
                )
            }
        }
    }
}