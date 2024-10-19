package id.deval.recipe.util

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import co.touchlab.kermit.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.cancellation.CancellationException

fun CoroutineScope.launchCatchError(
    block: suspend CoroutineScope.() -> Unit,
    onError: suspend (Throwable) -> Unit
) {
    launch {
        try {
            block()
        } catch (e: Throwable) {
            if (e is CancellationException) throw e
            else {
                try {
                    onError(e)
                } catch (t: Throwable) {
                    t.printStackTrace()
                }
            }
        }
    }
}

fun CoroutineScope.launchCatchError(
    scope: CoroutineContext = coroutineContext,
    block: suspend CoroutineScope.() -> Unit,
    onError: suspend (Throwable) -> Unit
) {
    launch(scope) {
        try {
            block()
        } catch (e: Throwable) {
            if (e is CancellationException) throw e
            else {
                try {
                    onError(e)
                } catch (t: Throwable) {
                    t.printStackTrace()
                }
            }
        }
    }
}

fun NavController.safeNavigate(
    route : String,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator. Extras? = null
){
    try {
        navigate(route, navOptions, navigatorExtras)
    }catch (e : Exception){
        e.printStackTrace()
        Logger.e(e.toString())
    }
}

fun String.isEmailValid(): Boolean {
    val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    return emailRegex.toRegex().matches(this)
}

fun String.isPhoneNumberValid(): Boolean {
    val phoneRegex = "^\\d{10}$"
    return phoneRegex.toRegex().matches(this)
}