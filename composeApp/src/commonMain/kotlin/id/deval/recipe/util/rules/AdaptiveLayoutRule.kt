package id.deval.recipe.util.rules

sealed class AdaptiveLayoutRule {
    abstract val compactRule : Int
    abstract val mediumRule : Int
    abstract val expandedRule : Int

    data object LargeComponentWidth : AdaptiveLayoutRule(){
        override val compactRule: Int = Int.MAX_VALUE
        override val mediumRule: Int = 600
        override val expandedRule: Int = 600
    }

}