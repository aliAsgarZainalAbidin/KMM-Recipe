@file:OptIn(ExperimentalPermissionsApi::class)

package id.deval.recipe.shared

import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import java.util.ArrayList

@Composable
actual fun FeatureRequirePermission(
    permission : ArrayList<String>,
    onPermissionGranted : @Composable () -> Unit,
    onPermissionDenied : @Composable () -> Unit,
    showReason : @Composable () -> Unit
) {
    val permissionsState = rememberMultiplePermissionsState(
        permission
    )

    if (permissionsState.allPermissionsGranted) {
        onPermissionGranted()
    } else {
        if (permissionsState.shouldShowRationale) {
            showReason()
        } else {
            onPermissionDenied()
        }
    }
}