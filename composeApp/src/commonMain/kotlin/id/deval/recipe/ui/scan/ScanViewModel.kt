package id.deval.recipe.ui.scan

import androidx.lifecycle.ViewModel
import id.deval.recipe.ui.scan.effect.ScanScreenEffect
import id.deval.recipe.ui.scan.event.ScanScreenEvent
import id.deval.recipe.ui.scan.state.ScanScreenState
import id.deval.recipe.ui.scan.state.ScanType
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ScanViewModel : ViewModel() {

    private var _scanScreenState : MutableStateFlow<ScanScreenState> = MutableStateFlow(
        ScanScreenState()
    )
    val scanScreenState : StateFlow<ScanScreenState> = _scanScreenState

    private var _scanScreenEffect : MutableSharedFlow<ScanScreenEffect> = MutableSharedFlow()
    val scanScreenEffect : MutableSharedFlow<ScanScreenEffect> = _scanScreenEffect

    fun onEvent(event : ScanScreenEvent){
        when(event){
            is ScanScreenEvent.OnScanTypeSelected -> {
                onScanTypeSelected(event.type)
            }
        }
    }

    private fun onScanTypeSelected(type: ScanType) {
        _scanScreenState.update {
            it.copy(
                typeItem = type
            )
        }
    }

    companion object {
        const val TAG = "ScanScreenViewModel"
    }
}