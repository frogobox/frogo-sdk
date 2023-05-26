// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.0.1" apply false
    id("com.android.library") version "8.0.1" apply false
    id("com.google.devtools.ksp") version "1.8.21-1.0.11" apply false
    id("org.jetbrains.kotlin.android") version DependencyGradle.KOTLIN_VERSION apply false
    id("org.jetbrains.kotlin.jvm") version DependencyGradle.KOTLIN_VERSION apply false
}

buildscript {
    dependencies {
        classpath("com.github.dcendents:android-maven-gradle-plugin:2.1")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}