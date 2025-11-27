import com.android.build.gradle.LibraryExtension
import com.sanhuzhen.buildlogic.configureKotlinAndroid
import com.sanhuzhen.buildlogic.lib
import com.sanhuzhen.buildlogic.libVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

/**
 * @description: Android模块共享构建逻辑
 * @author: sanhuzhen
 * @date: 2025/11/27 15:23
 */
class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                // Android Library Module 都需要这 2 个插件
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                // ksp，用来解析therouter
                apply(plugin = "com.google.devtools.ksp")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = libVersion("android-targetSdk").toInt()
                defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                // 在测试时禁用系统动画，提高 UI 测试稳定性
                testOptions.animationsDisabled = true
                //为 module 的资源加前缀限制
                resourcePrefix =
                    path.split("""\W""".toRegex()).drop(1).distinct().joinToString(separator = "_")
                        .lowercase() + "_"
            }

            dependencies {
                "androidTestImplementation"(lib("androidx-espresso-core"))
                "androidTestImplementation"(lib("androidx-junit"))
                "testImplementation"(lib("junit"))
            }
        }
    }
}