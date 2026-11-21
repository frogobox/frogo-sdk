plugins {
    id("frogo.android.library")
    alias(libs.plugins.kotlin.parcelize)
    id("frogo.maven.publish")
}

android {
    namespace = ProjectSetting.PROJECT_LIB_ID_SDK

    buildFeatures {
        aidl = true
        viewBinding = true
        buildConfig = true
        resValues = true
    }

    tasks.withType<JavaCompile>().configureEach {
        options.compilerArgs.add("-Xlint:deprecation")
    }
}

dependencies {
    api(project(DependencyGradle.FROGO_PATH_CORE_SDK))

    api(libs.androidx.appcompat)

    api(libs.androidx.activity.ktx)
    api(libs.androidx.fragment.ktx)

    api(libs.androidx.constraintlayout)
    api(libs.androidx.viewpager2)

    api(libs.androidx.core.ktx)

    api(libs.androidx.lifecycle.runtime.ktx)
    api(libs.androidx.lifecycle.viewmodel.ktx)
    api(libs.androidx.lifecycle.livedata.ktx)
    api(libs.androidx.preference)
    api(libs.androidx.datastore.preferences)

    api(libs.androidx.room.ktx)
    api(libs.androidx.room.runtime)

    api(libs.github.glide)
    api(libs.github.customactivityoncrash)
    api(libs.kotlinx.coroutines.android)

    api(libs.material)

    compileOnly(libs.junit)
    compileOnly(libs.kotlinx.coroutines.test)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.robolectric)
    testImplementation(libs.mockwebserver)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.androidx.test.runner)
    testImplementation(libs.androidx.test.rules)
    testImplementation(libs.androidx.test.ext.junit)
    testImplementation(libs.espresso.core)
    testImplementation(libs.kotlinx.coroutines.test)
}

afterEvaluate {
    publishing {
        publications {
            register("release", MavenPublication::class) {
                from(components["release"])
                groupId = ProjectSetting.PROJECT_LIB_ID_SDK
                artifactId = ProjectSetting.MODULE_NAME_SDK
                version = ProjectSetting.PROJECT_VERSION_NAME
            }
        }
    }
}
