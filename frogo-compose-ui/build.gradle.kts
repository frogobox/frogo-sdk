import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    `maven-publish`
}

android {
    compileSdk = ProjectSetting.PROJECT_COMPILE_SDK
    namespace = ProjectSetting.PROJECT_LIB_ID_COMPOSE_UI

    defaultConfig {
        minSdk = ProjectSetting.PROJECT_MIN_SDK

        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFile("consumer-rules.pro")
    }

    publishing {
        singleVariant("release") {
            // withSourcesJar()
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
        freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
    }
}

dependencies {
    // Bergantung ke frogo-compose-android jika diperlukan base compose class
    api(project(DependencyGradle.FROGO_PATH_COMPOSE)) 
    
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

    debugApi(libs.androidx.compose.ui.tooling)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.espresso.core)
}

afterEvaluate {
    publishing {
        publications {
            register("release", MavenPublication::class) {
                from(components["release"])
                groupId = ProjectSetting.PROJECT_LIB_ID_COMPOSE_UI
                artifactId = ProjectSetting.MODULE_NAME_COMPOSE_UI
                version = ProjectSetting.PROJECT_VERSION_NAME
            }
        }
    }
}
