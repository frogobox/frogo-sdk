import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.compose") version DependencyGradle.COMPOSE_MULTIPLATFORM_VERSION
    `maven-publish`
}

group = ProjectSetting.PROJECT_LIB_ID_CORE_SDK
version = ProjectSetting.PROJECT_VERSION_NAME

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
}

dependencies {

    implementation(compose.ui)
    implementation(compose.runtime)
    implementation(compose.preview)
    implementation(compose.uiTooling)
    implementation(compose.material)
    implementation(compose.materialIconsExtended)

    implementation(Google.gson)

    implementation(Square.okhttp)
    implementation(Square.okhttpLogging)

    implementation(Square.Retrofit2.retrofit)
    implementation(Square.Retrofit2.converterGson)
    implementation(Square.Retrofit2.converterJackson)
    implementation(Square.Retrofit2.converterMoshi)
    implementation(Square.Retrofit2.converterProtobuf)
    implementation(Square.Retrofit2.converterWire)
    implementation(Square.Retrofit2.converterSimpleXml)
    implementation(Square.Retrofit2.converterJAXB)
    implementation(Square.Retrofit2.converterScalars)

    implementation(Square.Retrofit2.adapterRxJava3)

    implementation(Koin.core)

    implementation(Reactivex.rxJava3)
}

publishing {

    publications {

        // Creates a Maven publication called "release".
        register("release", MavenPublication::class) {

            from(components["java"])

            // Library Package Name (Example : "com.frogobox.androidfirstlib")
            // NOTE : Different GroupId For Each Library / Module, So That Each Library Is Not Overwritten
            groupId = ProjectSetting.PROJECT_LIB_ID_CORE_SDK

            // Library Name / Module Name (Example : "androidfirstlib")
            // NOTE : Different ArtifactId For Each Library / Module, So That Each Library Is Not Overwritten
            artifactId = ProjectSetting.MODULE_NAME_CORE_SDK

            // Version Library Name (Example : "1.0.0")
            version = ProjectSetting.PROJECT_VERSION_NAME

        }

    }

    repositories {
        maven(Util.jitpackUrl)
    }

}