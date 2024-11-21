package id.deval.recipe.ui.profile.state

import id.deval.recipe.domain.model.Recipe
import id.deval.recipe.domain.model.User

data class ProfileScreenState(
    val user: User = User(0, "", 0, 0, 0, arrayListOf(), arrayListOf()),
    val selectedTabType : ProfileTabType = ProfileTabType.RECIPES
)
