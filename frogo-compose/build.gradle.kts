plugins {
    id("frogo.android.library")
    id("frogo.android.compose")
    id("frogo.maven.publish")
}

android {
    namespace = "com.frogobox.compose"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(project(DependencyGradle.FROGO_PATH_SDK))

    // Jetpack Compose BOM
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.material.icons.extended)
    api(libs.androidx.compose.foundation)

    api(libs.coil.compose)
    api(libs.coil.network.okhttp)
    api(libs.glide.compose)

    // Legacy View interop dependencies (for migrated FrogoLoadingIndicatorView, themes, etc.)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // Activity Compose
    api(libs.androidx.activity.compose)

    // Lifecycle / ViewModel Compose
    api(libs.androidx.lifecycle.viewmodel.compose)
    api(libs.androidx.lifecycle.runtime.compose)

    // Navigation Compose
    api(libs.androidx.navigation.compose)

    debugApi(libs.androidx.compose.ui.tooling)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.espresso.core)
    testImplementation(libs.kotlinx.coroutines.test)
}

afterEvaluate {
    publishing {
        publications {
            register("release", MavenPublication::class) {
                from(components["release"])
                groupId = ProjectSetting.PROJECT_LIB_ID_COMPOSE
                artifactId = ProjectSetting.MODULE_NAME_COMPOSE
                version = ProjectSetting.PROJECT_VERSION_NAME
            }
        }
    }
}
