import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
        }
        binaries.executable()
    }
    
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    jvm("desktop")
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        val desktopMain by getting
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            implementation(libs.google.mlkit.objectdetection)

            //CameraX
            implementation(libs.android.camerax.core)
            implementation(libs.android.camerax.camera2)
            implementation(libs.android.camerax.mlkit)

            //accompanist permission
            implementation(libs.google.accompanist.permission)

            //Desktop + Android Voyager
//            implementation(libs.desktop.android.voyager.kodein)
//            implementation(libs.desktop.android.voyager.rxjava)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(compose.material)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.androidx.lifecycle.viewmodel.compose)

            //Navigation Compose
            implementation(libs.navigation.compose)

            //Material3
            implementation(compose.material3)
            implementation(libs.common.material3.window.size)
            implementation(libs.common.material3.adaptive)
            implementation(libs.common.material3.adaptive.layout)
            implementation(libs.common.material3.adaptive.navigation)

            //Kodein
            implementation(libs.kodein)

            //Compose Coil
//            implementation(libs.compose.coil)

            //Kermit Logger
            implementation(libs.kermit.logger)

            //Date Time Kotlinx
            implementation(libs.datetime.kotlinx)

            //Multiplatform Voyager
            implementation(libs.kmp.voyager.navigator)
            implementation(libs.kmp.voyager.screenmodel)
            implementation(libs.kmp.voyager.bottomsheet.navigator)
            implementation(libs.kmp.voyager.tab.navigator)
            implementation(libs.kmp.voyager.transitions)

            //TEST LIBRARIES
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)

            //Desktop + Android Voyager
//            implementation(libs.desktop.android.voyager.kodein)
//            implementation(libs.desktop.android.voyager.rxjava)
        }
    }
}

android {
    namespace = "id.deval.recipe"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "id.deval.recipe"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
//    implementation(libs.kodein)
}

compose.desktop {
    application {
        mainClass = "id.deval.recipe.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "id.deval.recipe"
            packageVersion = "1.0.0"
        }
    }
}
