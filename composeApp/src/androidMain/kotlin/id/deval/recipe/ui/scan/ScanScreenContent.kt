package id.deval.recipe.ui.scan

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import id.deval.recipe.R
import id.deval.recipe.theme.RecipeAppTheme
import id.deval.recipe.ui.scan.event.ScanScreenEvent
import id.deval.recipe.ui.scan.state.ScanScreenState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
actual fun ScanScreenContent(
    state : ScanScreenState,
    onEvent : (ScanScreenEvent) -> Unit
){
    val cameraPermission = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )

    val permissionsStatus by remember { mutableStateOf(cameraPermission.status) }

    if (permissionsStatus.isGranted){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text("Camera Permission Granted")
        }
    } else {
        SideEffect {
            cameraPermission.launchPermissionRequest()
        }
    }
}

@Preview
@Composable
fun TestPreview(){
    RecipeAppTheme {
        Text("Preview", fontFamily = FontFamily(Font(R.font.inter)))
    }
}
