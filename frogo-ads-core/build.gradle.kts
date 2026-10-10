import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.parcelize)
    `maven-publish`
}

android {
    compileSdk = ProjectSetting.PROJECT_COMPILE_SDK
    namespace = ProjectSetting.PROJECT_LIB_ID_ADS_CORE

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
        buildConfig = true
        resValues = true
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
    implementation(project(DependencyGradle.FROGO_PATH_CORE_SDK))
    implementation(project(DependencyGradle.FROGO_PATH_SDK))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.square.retrofit)
    implementation(libs.gson)

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
                name = ProjectSetting.MODULE_NAME_ADS_CORE
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
                groupId = ProjectSetting.PROJECT_LIB_ID_ADS_CORE
                artifactId = ProjectSetting.MODULE_NAME_ADS_CORE
                version = ProjectSetting.PROJECT_VERSION_NAME
            }
        }
    }
}
