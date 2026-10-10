import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.compose.compiler)
    `maven-publish`
}

android {
    compileSdk = ProjectSetting.PROJECT_COMPILE_SDK
    namespace = ProjectSetting.PROJECT_LIB_ID_ADS_ADMOB

    defaultConfig {
        minSdk = ProjectSetting.PROJECT_MIN_SDK
        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFile("consumer-rules.pro")
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
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
        viewBinding = true
        buildConfig = true
        resValues = true
        compose = true
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

configurations.configureEach {
    exclude(group = "com.google.android.gms", module = "play-services-ads")
    exclude(group = "com.google.android.gms", module = "play-services-ads-lite")
}

dependencies {
    implementation(project(DependencyGradle.FROGO_PATH_CORE_SDK))
    implementation(project(DependencyGradle.FROGO_PATH_SDK))
    implementation(project(DependencyGradle.FROGO_PATH_COMPOSE))
    api(project(DependencyGradle.FROGO_PATH_ADS_CORE))

    api(libs.ads.google.admob)
    api(libs.androidx.lifecycle.process)

    // Jetpack Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // Activity Compose
    implementation(libs.androidx.activity.compose)

    // Lifecycle / ViewModel Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)

    debugImplementation(libs.androidx.compose.ui.tooling)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.espresso.core)
}

afterEvaluate {
    publishing {
        repositories {
            maven {
                name = ProjectSetting.MODULE_NAME_ADS_ADMOB
                url = uri(ProjectSetting.URI_PACKAGE_LIB)
                credentials {
                    username = project.findProperty("gpr.user") as String? ?: ""
                    password = project.findProperty("gpr.key") as String? ?: ""
                }
            }
        }

        publications {
            register("release", MavenPublication::class) {
                from(components["release"])
                groupId = ProjectSetting.PROJECT_LIB_ID_ADS_ADMOB
                artifactId = ProjectSetting.MODULE_NAME_ADS_ADMOB
                version = ProjectSetting.PROJECT_VERSION_NAME
            }
        }
    }
}
