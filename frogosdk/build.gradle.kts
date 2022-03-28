import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.compose") version DependencyGradle.COMPOSE_MULTIPLATFORM_VERSION
    id("kotlin-kapt")
    `maven-publish`
}

android {

    compileSdk = ProjectSetting.PROJECT_COMPILE_SDK

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
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.toString()
        }
    }

}

dependencies {

    implementation(project(DependencyGradle.FROGO_CORE_SDK_PATH))

    implementation(Androidx.appCompat)
    implementation(Androidx.appCompatResources)

    implementation(Androidx.activityKtx)
    implementation(Androidx.fragmentKtx)

    implementation(Androidx.constraintLayout)
    implementation(Androidx.collection)
    implementation(Androidx.savedState)
    implementation(Androidx.viewPager2)
    implementation(Androidx.preferenceKtx)

    implementation(Androidx.Core.ktx)

    implementation(compose.ui)
    implementation(compose.runtime)
    implementation(compose.preview)
    implementation(compose.uiTooling)
    implementation(compose.material)
    implementation(compose.materialIconsExtended)

    implementation(Androidx.Lifecycle.runtimeKtx)
    implementation(Androidx.Lifecycle.viewmodelKtx)
    implementation(Androidx.Lifecycle.livedataKtx)

    implementation(Androidx.Room.ktx)
    implementation(Androidx.Room.runtime)
    implementation(Androidx.Room.rxJava3)

    implementation(Google.material)
    implementation(Google.gson)

    implementation(Square.okhttp)
    implementation(Square.okhttpLogging)

    implementation(Square.Retrofit2.retrofit)
    implementation(Square.Retrofit2.converterGson)
    implementation(Square.Retrofit2.adapterRxJava3)

    implementation(Reactivex.rxJava3)
    implementation(Reactivex.rxAndroid3)

    implementation(Koin.core)
    implementation(Koin.android)
    implementation(Koin.androidCompat)
    implementation(Koin.androidxWorkManager)
    implementation(Koin.androidxCompose)
    implementation(Koin.ktor)

    implementation(Util.chucker)
    implementation(Util.glide)

    implementation("com.github.javiersantos:PiracyChecker:1.2.8")

    api(JetBrains.coroutinesCore)
    api(JetBrains.coroutinesAndroid)

    kapt(Androidx.Lifecycle.compiler)
    kapt(Androidx.Room.compiler)
    kapt(Util.glideCompiler)

    debugImplementation(compose.ui)
    debugImplementation(compose.uiTooling)

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