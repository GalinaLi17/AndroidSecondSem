import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import ru.itis.android.core.plugin.ext.libs

class HiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.devtools.ksp")
            pluginManager.apply("com.google.dagger.hilt.android")

            dependencies {
                "implementation"(libs.findLibrary("hilt").get())
                "ksp"(libs.findLibrary("hilt.compiler").get())
                "implementation"("androidx.hilt:hilt-navigation-compose:1.2.0")
            }
        }
    }
}
/*
class HiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply(plugin = libs.findPlugin("ksp").get().get().pluginId)
            apply(plugin = libs.findPlugin("hilt").get().get().pluginId)

            dependencies {
                "implementation"(libs.findLibrary("hilt").get())
                "ksp"(libs.findLibrary("hilt.compiler").get())
            }
        }
    }
}*/