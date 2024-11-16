package id.deval.recipe.shared

interface Platform {
    val name: PlatformTarget
}

expect fun getPlatform(): Platform
