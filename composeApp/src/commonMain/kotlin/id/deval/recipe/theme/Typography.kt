package id.deval.recipe.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kmm_recipe.composeapp.generated.resources.Res
import kmm_recipe.composeapp.generated.resources.inter
import org.jetbrains.compose.resources.Font

@Composable
fun getRecipeFontFamilty() = FontFamily(
    Font(Res.font.inter)
)

fun getTypography(fontFamily: FontFamily) = Typography(
    headlineLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        letterSpacing = 0.5.sp,
        color = mainTextColor,
        fontFamily = fontFamily
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
        letterSpacing = 0.5.sp,
        color = mainTextColor,
        fontFamily = fontFamily
    ),
    headlineSmall = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        letterSpacing = 0.5.sp,
        color = mainTextColor,
        fontFamily = fontFamily
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp,
        letterSpacing = 0.5.sp,
        color = secondaryTextColor,
        fontFamily = fontFamily
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        letterSpacing = 0.5.sp,
        color = secondaryTextColor,
        fontFamily = fontFamily
    ),
    bodySmall = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        letterSpacing = 0.5.sp,
        color = secondaryTextColor,
        fontFamily = fontFamily
    )
)