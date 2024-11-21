package id.deval.recipe.ui.profile

import androidx.lifecycle.ViewModel
import id.deval.recipe.domain.model.User
import id.deval.recipe.ui.profile.effect.ProfileScreenEffect
import id.deval.recipe.ui.profile.event.ProfileScreenEvent
import id.deval.recipe.ui.profile.state.ProfileScreenState
import id.deval.recipe.ui.profile.state.ProfileTabType
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel : ViewModel(){
    private var _profileScreenState : MutableStateFlow<ProfileScreenState> = MutableStateFlow(
        ProfileScreenState()
    )
    val profileScreenState : StateFlow<ProfileScreenState> = _profileScreenState

    private var _profileScreenEffect : MutableSharedFlow<ProfileScreenEffect> = MutableSharedFlow()
    val profileScreenEffect : SharedFlow<ProfileScreenEffect> = _profileScreenEffect

    fun onEvent(event : ProfileScreenEvent){
        when(event){
            is ProfileScreenEvent.OnShareProfileClicked -> {
                onShareProfile(event.user)
            }
            is ProfileScreenEvent.OnNavigateBackClicked -> {}
            is ProfileScreenEvent.OnTabMenuClicked -> {
                onTabMenuClicked(event.profileTabType)
            }
            is ProfileScreenEvent.OnFollowClicked -> {}
        }
    }

    private fun onShareProfile(user : User){

    }

    private fun onNavigateBack(){

    }

    private fun onTabMenuClicked(tabMenu : ProfileTabType){
        _profileScreenState.update {
            it.copy(
                selectedTabType = tabMenu
            )
        }
    }

    private fun onFollowClicked(){

    }
}