package id.deval.recipe.shared

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!, number : $num"
    }
}