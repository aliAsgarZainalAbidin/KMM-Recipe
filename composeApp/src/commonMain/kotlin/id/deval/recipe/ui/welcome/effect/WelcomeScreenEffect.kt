package id.deval.recipe.ui.welcome.effect

sealed interface WelcomeScreenEffect {
    data object NavigateToLogin : WelcomeScreenEffect
    data object NavigateToMain : WelcomeScreenEffect
}