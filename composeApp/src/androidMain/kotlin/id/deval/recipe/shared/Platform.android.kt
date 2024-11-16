package id.deval.recipe.shared

import android.os.Build

class AndroidPlatform : Platform {
    override val name: PlatformTarget = PlatformTarget.ANDROID
}

actual fun getPlatform(): Platform = AndroidPlatform()
