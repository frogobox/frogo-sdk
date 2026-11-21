import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.variant.HasUnitTestBuilder
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            extensions.configure<ApplicationExtension> {
                compileSdk {
                    version = release(ProjectSetting.PROJECT_COMPILE_SDK) {
                        minorApiLevel = ProjectSetting.PROJECT_COMPILE_SDK_MINOR
                    }
                }

                defaultConfig {
                    minSdk = ProjectSetting.PROJECT_MIN_SDK
                    targetSdk = ProjectSetting.PROJECT_TARGET_SDK

                    vectorDrawables.useSupportLibrary = true
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                buildFeatures {
                    buildConfig = true
                    resValues = true
                    viewBinding = true
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }

                testOptions {
                    unitTests {
                        isIncludeAndroidResources = true
                    }
                }

                lint {
                    abortOnError = false
                    checkReleaseBuilds = false
                }
            }

            extensions.configure<KotlinAndroidProjectExtension> {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_17)
                    freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
                }
            }

            extensions.configure<ApplicationAndroidComponentsExtension> {
                beforeVariants { builder ->
                    (builder as? HasUnitTestBuilder)?.enableUnitTest = true
                }
            }
        }
    }
}
