package id.deval.recipe.shared

actual enum class PermissionPackages {
    CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, READ_CONTACTS
}

actual fun PermissionPackages.toTarget(): String {
    return when (this) {
        PermissionPackages.CAMERA -> android.Manifest.permission.CAMERA
        PermissionPackages.READ_EXTERNAL_STORAGE -> android.Manifest.permission.READ_EXTERNAL_STORAGE
        PermissionPackages.WRITE_EXTERNAL_STORAGE -> android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        PermissionPackages.READ_CONTACTS -> android.Manifest.permission.READ_CONTACTS
    }
}