plugins {
    id("frogo.jvm.library")
    id("frogo.maven.publish")
}

group = ProjectSetting.PROJECT_LIB_ID_CORE_SDK
version = ProjectSetting.PROJECT_VERSION_NAME

dependencies {
    api(libs.gson)

    api(libs.square.okhttp)
    api(libs.square.logging.interceptor)

    api(libs.square.retrofit)
    api(libs.square.retrofit.converter.gson)

    api(libs.kotlinx.coroutines.core)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockwebserver)
}

publishing {
    publications {
        register("release", MavenPublication::class) {
            from(components["java"])
            groupId = ProjectSetting.PROJECT_LIB_ID_CORE_SDK
            artifactId = ProjectSetting.MODULE_NAME_CORE_SDK
            version = ProjectSetting.PROJECT_VERSION_NAME
        }
    }
}
