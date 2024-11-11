package id.deval.recipe.ui.welcome.event

sealed interface WelcomeScreenEvent {
    data class OnGetStartedClicked(val isLogin : Boolean) : WelcomeScreenEvent
}