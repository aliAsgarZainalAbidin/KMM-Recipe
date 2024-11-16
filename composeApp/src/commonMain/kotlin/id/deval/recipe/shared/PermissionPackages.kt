package id.deval.recipe.shared

expect enum class PermissionPackages {
    CAMERA,
    READ_EXTERNAL_STORAGE,
    WRITE_EXTERNAL_STORAGE,
    READ_CONTACTS
}

expect fun PermissionPackages.toTarget() : String