package id.deval.recipe.shared

import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: PlatformTarget = PlatformTarget.IOS
}

actual fun getPlatform(): Platform = IOSPlatform()