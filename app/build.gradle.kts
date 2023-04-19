plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {

    compileSdk = ProjectSetting.PROJECT_COMPILE_SDK
    namespace = ProjectSetting.PROJECT_APP_ID

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

    implementation(project(DependencyGradle.FROGO_PATH_CORE_SDK))
    implementation(project(DependencyGradle.FROGO_PATH_SDK))

    implementation(Androidx.Work.runtimeKtx)
    implementation(Google.material)

    implementation(Koin.core)
    implementation(Koin.android)
    implementation(Koin.androidCompat)
    implementation(Koin.androidxWorkManager)

    ksp(Androidx.Lifecycle.compiler)
    ksp(Androidx.Room.compiler)

}