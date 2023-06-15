plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    `maven-publish`
}

android {

    compileSdk = ProjectSetting.PROJECT_COMPILE_SDK
    namespace = ProjectSetting.PROJECT_LIB_ID_SDK

    defaultConfig {
        minSdk = ProjectSetting.PROJECT_MIN_SDK
        targetSdk = ProjectSetting.PROJECT_TARGET_SDK

        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        consumerProguardFile("consumer-rules.pro")
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
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain {
            languageVersion.set(JavaLanguageVersion.of("17"))
        }
    }
}

dependencies {

    api(project(DependencyGradle.FROGO_PATH_CORE_SDK))

    api(Androidx.appCompat)

    api(Androidx.activityKtx)
    api(Androidx.fragmentKtx)

    api(Androidx.constraintLayout)
    api(Androidx.viewPager2)

    api(Androidx.Core.ktx)

    api(Androidx.Lifecycle.runtimeKtx)
    api(Androidx.Lifecycle.viewmodelKtx)
    api(Androidx.Lifecycle.livedataKtx)

    api(Androidx.Room.ktx)
    api(Androidx.Room.runtime)
    api(Androidx.Room.rxJava3)

    api(Google.material)

    api(Reactivex.rxAndroid3)

    api(Koin.android)
    api(Koin.androidCompat)
    api(Koin.androidxWorkManager)

    api(GitHub.chucker)
    api(GitHub.glide)

    api(GitHub.piracyChecker)
    api(GitHub.customActivityOnCrash)

    ksp(Androidx.Lifecycle.compiler)
    ksp(Androidx.Room.compiler)
    ksp(GitHub.glideCompiler)

}

afterEvaluate {
    publishing {
        publications {

            // Creates a Maven publication called "release".
            register("release", MavenPublication::class) {

                // Applies the component for the release build variant.
                // NOTE : Delete this line code if you publish Native Java / Kotlin Library
                from(components["release"])

                // Library Package Name (Example : "com.frogobox.androidfirstlib")
                // NOTE : Different GroupId For Each Library / Module, So That Each Library Is Not Overwritten
                groupId = ProjectSetting.PROJECT_LIB_ID_SDK

                // Library Name / Module Name (Example : "androidfirstlib")
                // NOTE : Different ArtifactId For Each Library / Module, So That Each Library Is Not Overwritten
                artifactId = ProjectSetting.MODULE_NAME_SDK

                // Version Library Name (Example : "1.0.0")
                version = ProjectSetting.PROJECT_VERSION_NAME

            }
        }
    }
}