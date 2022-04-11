import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.compose") version DependencyGradle.COMPOSE_MULTIPLATFORM_VERSION
    id("kotlin-kapt")
}

android {

    compileSdk = ProjectSetting.PROJECT_COMPILE_SDK

    defaultConfig {
        applicationId = ProjectSetting.PROJECT_APP_ID
        minSdk = ProjectSetting.PROJECT_MIN_SDK
        targetSdk = ProjectSetting.PROJECT_TARGET_SDK
        versionCode = ProjectSetting.PROJECT_VERSION_CODE
        versionName = ProjectSetting.PROJECT_VERSION_NAME

        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Declaration build config
        buildConfigField("String", "DATABASE_NAME", ProjectSetting.DB)

        // Naming APK // AAB
        setProperty("archivesBaseName", "${ProjectSetting.NAME_APK}(${versionName})")

        // Declaration apps name debug mode
        val debugAttribute = "Development"
        val nameAppDebug = "${ProjectSetting.NAME_APP} $debugAttribute"
        resourceConfigurations += setOf("en", "id")

        // Inject app name for debug
        resValue("string", "app_name", nameAppDebug)

    }

    signingConfigs {
        create("release") {
            // You need to specify either an absolute path or include the
            // keystore file in the same directory as the build.gradle file.
            // [PROJECT FOLDER NAME/app/[COPY YOUT KEY STORE] .jks in here
            storeFile = file(ProjectSetting.PLAYSTORE_STORE_FILE)
            storePassword = ProjectSetting.PLAYSTORE_STORE_PASSWORD
            keyAlias = ProjectSetting.PLAYSTORE_KEY_ALIAS
            keyPassword = ProjectSetting.PLAYSTORE_KEY_PASSWORD
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            // Generated Signed APK / AAB
            signingConfig = signingConfigs.getByName("release")

            // Inject app name for release
            resValue("string", "app_name", ProjectSetting.APP_NAME)

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

    implementation(project(DependencyGradle.FROGO_PATH_CORE_SDK))
    implementation(project(DependencyGradle.FROGO_PATH_SDK))
    implementation(project(DependencyGradle.FROGO_PATH_LOG))

    implementation(compose.ui)
    implementation(compose.runtime)
    implementation(compose.preview)
    implementation(compose.uiTooling)
    implementation(compose.material)
    implementation(compose.materialIconsExtended)

    implementation(Androidx.appCompat)
    implementation(Androidx.appCompatResources)
    implementation(Androidx.activityKtx)
    implementation(Androidx.fragmentKtx)
    implementation(Androidx.constraintLayout)
    implementation(Androidx.preferenceKtx)

    implementation(Androidx.Core.ktx)
    implementation(Androidx.Work.runtimeKtx)
    implementation(Androidx.Lifecycle.runtimeKtx)
    implementation(Androidx.Compose.activity)

    implementation(Androidx.Room.runtime)
    implementation(Androidx.Room.ktx)
    implementation(Androidx.Room.rxJava3)

    implementation(Google.material)

    implementation(Koin.core)
    implementation(Koin.android)
    implementation(Koin.androidCompat)
    implementation(Koin.androidxWorkManager)
    implementation(Koin.androidxCompose)

    kapt(Androidx.Lifecycle.compiler)
    kapt(Androidx.Room.compiler)

    debugImplementation(compose.ui)
    debugImplementation(compose.uiTooling)

}