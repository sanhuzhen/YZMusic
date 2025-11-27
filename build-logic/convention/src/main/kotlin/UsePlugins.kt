import com.sanhuzhen.buildlogic.lib
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * @description:
 * @author: sanhuzhen
 * @date: 2025/11/27 19:56
 */
fun Project.useTherouter() {
    pluginManager.apply("com.google.devtools.ksp")

    dependencies {
        "ksp"(lib("therouter-apt"))
        "implementation"(lib("therouter-router"))
    }
}