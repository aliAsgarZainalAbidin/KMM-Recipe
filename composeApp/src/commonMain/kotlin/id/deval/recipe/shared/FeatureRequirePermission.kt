package id.deval.recipe.shared

import androidx.compose.runtime.Composable

@Composable
expect fun FeatureRequirePermission(
    permission : ArrayList<String>,
    onPermissionGranted : @Composable () -> Unit,
    onPermissionDenied : @Composable () -> Unit,
    showReason : @Composable () -> Unit
)