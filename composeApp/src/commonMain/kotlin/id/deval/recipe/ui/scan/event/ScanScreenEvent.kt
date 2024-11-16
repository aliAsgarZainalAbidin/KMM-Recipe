package id.deval.recipe.ui.scan.event

import id.deval.recipe.ui.scan.state.ScanType

sealed interface ScanScreenEvent {
    data class OnScanTypeSelected(val type : ScanType) : ScanScreenEvent
}