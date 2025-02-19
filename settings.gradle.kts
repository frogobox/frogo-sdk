pluginManagement {
    repositories {
        gradlePluginPortal()
        google{
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        maven("https://jitpack.io")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

rootProject.name = "DeltadoxAndroid"

include(
    ":app",
    ":core-sdk",
    ":core-sdk-android",
    ":core-sdk-android-util",
    ":core-ui-android",
    ":frogorecyclerview",
    ":ad-core",
    ":ad-general",
    ":ad-unityad",
    ":ad-admob"
)