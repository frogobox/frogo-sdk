import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

            extensions.findByType(LibraryExtension::class.java)?.apply {
                buildFeatures {
                    compose = true
                }
            }

            extensions.findByType(ApplicationExtension::class.java)?.apply {
                buildFeatures {
                    compose = true
                }
            }
        }
    }
}
