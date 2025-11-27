import com.android.build.api.dsl.ApplicationExtension
import com.sanhuzhen.buildlogic.configureKotlinAndroid
import com.sanhuzhen.buildlogic.lib
import com.sanhuzhen.buildlogic.libVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

/**
 * @description:
 * @author: sanhuzhen
 * @date: 2025/11/27 17:10
 */
class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            apply(plugin = "com.android.application")
            apply(plugin = "org.jetbrains.kotlin.android")
            // 检查非法依赖
            //apply(plugin = "com.dropbox.dependency-guard")
            // 使用TheRouter
            apply(plugin = "therouter")
            // ksp，用来解析therouter
            apply(plugin = "com.google.devtools.ksp")

            extensions.configure<ApplicationExtension>() {
                configureKotlinAndroid(this)
                defaultConfig {
                    applicationId = libVersion("application-namespace")
                    targetSdk = libVersion("android-targetSdk").toInt()
                    versionCode = libVersion("versionCode").toInt()
                    versionName = libVersion("versionName")
                }
                // 测试禁止动画
                @Suppress("UnstableApiUsage")
                testOptions.animationsDisabled = true
            }

            dependencies {
                // 小黄鸟
                "debugImplementation"(lib("leakcanary-android"))

                "ksp"(lib("therouter-apt"))
                "implementation"(lib("therouter-router"))
            }
        }
    }
}