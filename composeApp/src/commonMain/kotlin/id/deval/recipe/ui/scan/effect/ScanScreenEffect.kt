package id.deval.recipe.ui.scan.effect

import id.deval.recipe.ui.scan.state.ScanType

sealed interface ScanScreenEffect {
    data class OnScanTypeSelected(val type : ScanType) : ScanScreenEffect
}