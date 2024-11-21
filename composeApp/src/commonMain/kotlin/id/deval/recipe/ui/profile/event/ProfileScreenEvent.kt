package id.deval.recipe.ui.profile.event

import id.deval.recipe.domain.model.User
import id.deval.recipe.ui.profile.state.ProfileTabType

sealed interface ProfileScreenEvent {
    data class OnShareProfileClicked(val user : User) : ProfileScreenEvent
    data object OnNavigateBackClicked : ProfileScreenEvent
    data class OnTabMenuClicked(val profileTabType : ProfileTabType) : ProfileScreenEvent
    data class OnFollowClicked(val user : User) : ProfileScreenEvent
}