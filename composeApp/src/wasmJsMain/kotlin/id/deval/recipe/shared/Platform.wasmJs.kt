package id.deval.recipe.shared

class WasmPlatform: Platform {
    override val name: PlatformTarget = PlatformTarget.WEB
}

actual fun getPlatform(): Platform = WasmPlatform()
