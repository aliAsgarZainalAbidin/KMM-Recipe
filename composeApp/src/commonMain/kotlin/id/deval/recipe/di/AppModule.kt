package id.deval.recipe.di

import org.kodein.di.DI

val appRecipeModule = DI {
    import(viewModelModule)
}
