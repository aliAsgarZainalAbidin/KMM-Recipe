package id.deval.recipe.util

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

class AdaptiveModifier(windowSizeClass: WindowSizeClass) {
    val isCompact = windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT
    val isMedium = windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED
    val isExpanded = windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.MEDIUM

    @Composable
    operator fun <T : Any> invoke(
        compactModifier: () -> Modifier,
        mediumModifier: () -> Modifier,
        expandedModifier: () -> Modifier,
    ) : Modifier {
        return when {
            isCompact -> compactModifier()
            isMedium -> mediumModifier()
            isExpanded -> expandedModifier()
            else -> {
                Modifier
            }
        }
    }
}