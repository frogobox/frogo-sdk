plugins {
    id("frogo.android.application")
    id("frogo.android.compose")
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.parcelize)
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}

base {
    archivesName = "${ProjectSetting.NAME_APK}-[${ProjectSetting.PROJECT_VERSION_NAME}]"
}

android {
    namespace = ProjectSetting.PROJECT_NAME_SPACE

    defaultConfig {
        applicationId = ProjectSetting.PROJECT_APP_ID
        versionCode = ProjectSetting.PROJECT_VERSION_CODE
        versionName = ProjectSetting.PROJECT_VERSION_NAME

        buildConfigField("String", "DATABASE_NAME", ProjectSetting.DB)

        val debugAttribute = "Development"
        val nameAppDebug = "${ProjectSetting.NAME_APP} $debugAttribute"

        androidResources.localeFilters += setOf("en", "id")

        resValue("string", "app_name", nameAppDebug)
    }

    signingConfigs {
        create("release") {
            storeFile = file(ProjectSetting.PLAYSTORE_STORE_FILE)
            storePassword = ProjectSetting.PLAYSTORE_STORE_PASSWORD
            keyAlias = ProjectSetting.PLAYSTORE_KEY_ALIAS
            keyPassword = ProjectSetting.PLAYSTORE_KEY_PASSWORD
        }
    }

    buildTypes {
        getByName("release") {
            isDebuggable = false
            isJniDebuggable = false
            isPseudoLocalesEnabled = false
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            signingConfig = signingConfigs.getByName("release")

            resValue("string", "app_name", ProjectSetting.NAME_APP)
        }
    }
}

dependencies {
    implementation(project(DependencyGradle.FROGO_PATH_CORE_SDK))
    implementation(project(DependencyGradle.FROGO_PATH_SDK))
    implementation(project(DependencyGradle.FROGO_PATH_COMPOSE))

    implementation(libs.androidx.work.ktx)
    implementation(libs.material)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    ksp(libs.androidx.room.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.gson)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.androidx.test.runner)
    testImplementation(libs.androidx.test.rules)
    testImplementation(libs.androidx.test.ext.junit)
    testImplementation(libs.espresso.core)
    testImplementation(libs.kotlinx.coroutines.test)
}
