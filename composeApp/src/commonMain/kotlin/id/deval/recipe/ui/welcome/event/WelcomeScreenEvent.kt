package id.deval.recipe.ui.welcome.event

import id.deval.recipe.ui.welcome.effect.WelcomeScreenEffect

sealed interface WelcomeScreenEvent {
    data class OnGetStartedClicked(val isLogin : Boolean) : WelcomeScreenEvent
}