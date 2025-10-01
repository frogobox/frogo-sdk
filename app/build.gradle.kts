import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.text.SimpleDateFormat
import java.util.Date

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    id("kotlin-parcelize")
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas") // If Using Ksp
}

base {
    // Naming APK // AAB
    val timestamp = SimpleDateFormat("dd-MM-yyyy_hh-mm").format(Date())
    archivesName = "${ProjectSetting.NAME_APK}-[${ProjectSetting.PROJECT_VERSION_NAME}]-$timestamp"
}


android {

    compileSdk = ProjectSetting.PROJECT_COMPILE_SDK
    namespace = ProjectSetting.PROJECT_NAME_SPACE

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

        // Declaration apps name debug mode
        val debugAttribute = "Development"
        val nameAppDebug = "${ProjectSetting.NAME_APP} $debugAttribute"

        androidResources.localeFilters += setOf("en", "id")

        // Inject app name for debug
        resValue("string", "app_name", nameAppDebug)

        // Inject admob id for debug
        resValue("string", "admob_app_id", AdValue.debugAdmobAppId)
        resValue("string", "admob_banner", AdValue.debugAdmobBanner)
        resValue("string", "admob_interstitial", AdValue.debugAdmobInterstitial)
        resValue("string", "admob_interstitial_video", AdValue.debugAdmobInterstitialVideo)
        resValue("string", "admob_rewarded", AdValue.debugAdmobRewarded)
        resValue("string", "admob_rewarded_interstitial", AdValue.debugAdmobRewardedInterstitial)
        resValue("string", "admob_native_advanced", AdValue.debugAdmobNativeAdvanced)
        resValue("string", "admob_native_advanced_video", AdValue.debugAdmobNativeAdvancedVideo)

        resValue("string", "unity_ad_game_id", AdValue.debugUnityAdGameId)
        resValue("string", "unity_ad_interstitial", AdValue.debugUnityAdInterstitial)

    }

    signingConfigs {
        create("release") {
            // You need to specify either an absolute path or include the
            // keystore file in the same directory as the build.gradle file.
            // [PROJECT FOLDER NAME/app/[COPY YOUR KEY STORE] .jks in here
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
            resValue("string", "app_name", ProjectSetting.NAME_APP)

            // Inject admob id for release
            resValue("string", "admob_app_id", AdValue.releaseAdmobAppId)
            resValue("string", "admob_banner", AdValue.releaseAdmobBanner)
            resValue("string", "admob_interstitial", AdValue.releaseAdmobInterstitial)
            resValue("string", "admob_interstitial_video", AdValue.releaseAdmobInterstitialVideo)
            resValue("string", "admob_rewarded", AdValue.releaseAdmobRewarded)
            resValue("string", "admob_rewarded_interstitial", AdValue.releaseAdmobRewardedInterstitial)
            resValue("string", "admob_native_advanced", AdValue.releaseAdmobNativeAdvanced)
            resValue("string", "admob_native_advanced_video", AdValue.releaseAdmobNativeAdvancedVideo)

            resValue("string", "unity_ad_game_id", AdValue.releaseUnityAdGameId)
            resValue("string", "unity_ad_interstitial", AdValue.releaseUnityAdInterstitial)

        }
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
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

    implementation(project(DependencyGradle.FROGO_PATH_UI))

    implementation(project(DependencyGradle.FROGO_PATH_RECYCLER_VIEW))

    implementation(project(DependencyGradle.MODULE_LIB_FROGO_AD))

    implementation(libs.androidx.work.ktx)
    implementation(libs.material)

    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.android.compat)
    implementation(libs.koin.androidx.workmanager)

    ksp(libs.androidx.lifecycle.compiler)
    ksp(libs.androidx.room.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.material)
    implementation(libs.gson)

    implementation(libs.material)

    api(libs.github.circleimageview)
    
}