import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.frogobox.sdk.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    compileOnly(libs.plugins.android.application.get().let { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" })
    compileOnly(libs.plugins.android.library.get().let { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" })
    compileOnly(libs.plugins.compose.compiler.get().let { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" })
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "frogo.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "frogo.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidCompose") {
            id = "frogo.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
        register("jvmLibrary") {
            id = "frogo.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("mavenPublish") {
            id = "frogo.maven.publish"
            implementationClass = "MavenPublishConventionPlugin"
        }
    }
}
