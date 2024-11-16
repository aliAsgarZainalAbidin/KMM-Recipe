package id.deval.recipe.shared


class JVMPlatform: Platform {
    override val name: PlatformTarget = PlatformTarget.DESKTOP
}

actual fun getPlatform(): Platform = JVMPlatform()
