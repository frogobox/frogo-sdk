import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.variant.HasUnitTestBuilder
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }

            extensions.configure<LibraryExtension> {
                compileSdk {
                    version = release(ProjectSetting.PROJECT_COMPILE_SDK) {
                        minorApiLevel = ProjectSetting.PROJECT_COMPILE_SDK_MINOR
                    }
                }

                defaultConfig {
                    minSdk = ProjectSetting.PROJECT_MIN_SDK

                    vectorDrawables.useSupportLibrary = true
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    consumerProguardFile("consumer-rules.pro")
                }

                publishing {
                    singleVariant("release") {
                        withSourcesJar()
                    }
                }

                buildFeatures {
                    buildConfig = true
                    resValues = true
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }

                testOptions {
                    unitTests {
                        isIncludeAndroidResources = true
                        isReturnDefaultValues = true
                    }
                }

                lint {
                    abortOnError = false
                    checkReleaseBuilds = false
                }
            }

            tasks.withType<Test>().configureEach {
                systemProperty("robolectric.sdk", "34")
            }

            extensions.configure<KotlinAndroidProjectExtension> {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_17)
                    freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
                }
            }

            extensions.configure<LibraryAndroidComponentsExtension> {
                beforeVariants { builder ->
                    (builder as? HasUnitTestBuilder)?.enableUnitTest = true
                }
            }
        }
    }
}
