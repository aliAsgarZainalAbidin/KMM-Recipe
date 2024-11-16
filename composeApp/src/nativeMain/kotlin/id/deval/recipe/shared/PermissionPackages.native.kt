package id.deval.recipe.shared

actual enum class PermissionPackages {
    CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, READ_CONTACTS
}

actual fun PermissionPackages.toTarget(): String {
    TODO("Not yet implemented")
}