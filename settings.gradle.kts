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

rootProject.name = "FrogoAndroid"

include(
    ":app",
    ":frogo-core",
    ":frogo-core-android",
    ":frogo-compose-android",
    ":frogo-ui-base",
    ":frogo-ui-recyclerview",
    ":frogo-ext-ads",
)