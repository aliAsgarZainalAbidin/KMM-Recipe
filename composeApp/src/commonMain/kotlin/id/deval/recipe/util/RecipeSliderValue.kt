package id.deval.recipe.util

enum class RecipeSliderValue(val value: Float, val inMinutesValue : String) {
    LESS_THAN_10(0.0f, "<10 Minutes"),
    EQUAL_30(0.5f, "30 Minutes"),
    MORE_THAN_60(1.0f, ">60 Minutes")
}