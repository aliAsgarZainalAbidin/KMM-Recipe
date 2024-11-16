package id.deval.recipe.ui.scan

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import id.deval.recipe.di.appRecipeModule
import id.deval.recipe.ui.scan.effect.ScanScreenEffect
import id.deval.recipe.ui.scan.event.ScanScreenEvent
import id.deval.recipe.ui.scan.state.ScanScreenState
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.close
import kmm_recipe.composeapp.generated.resources.scan_menu
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.kodein.di.instance

class ScanScreen : Screen {

    @Composable
    override fun Content() {
        val scanViewModel by appRecipeModule.instance<ScanViewModel>()
        val scanScreenState by scanViewModel.scanScreenState.collectAsStateWithLifecycle()

        LaunchedEffect(Unit){
            scanViewModel.scanScreenEffect.collectLatest { effect ->
                when(effect){
                    is ScanScreenEffect.OnScanTypeSelected -> {

                    }
                }
            }
        }

        ScanScreenContent(
            scanScreenState,
            scanViewModel::onEvent
        )
    }
}