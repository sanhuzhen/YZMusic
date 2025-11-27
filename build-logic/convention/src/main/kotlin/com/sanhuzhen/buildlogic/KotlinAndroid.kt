package com.sanhuzhen.buildlogic

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

/**
 * @description:
 * @author: sanhuzhen
 * @date: 2025/11/27 16:12
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        var pathName = project.path.drop(1).replace(":", ".").replace("_", ".")
        // 没有新立一个模块当作MainActivity，这里只能这么处理了
        if(pathName == "app") pathName = "yzmusic"
        namespace = "com.sanhuzhen.${pathName}"
        compileSdk = libVersion("android-compileSdk").toInt()

        defaultConfig {
            minSdk = libVersion("android-minSdk").toInt()
        }

        viewBinding {
            enable = true
        }

        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        configureKotlinAndroid()

        dependencies {
            "coreLibraryDesugaring"(lib("android-desugarJdkLibs"))
        }
    }
}

private fun Project.configureKotlinAndroid() =
    extensions.configure<KotlinAndroidProjectExtension> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
            // 启动协程Experimental
            freeCompilerArgs.add("-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi")
        }
    }

private fun Project.configureKotlinJvm() =
    extensions.configure<KotlinJvmProjectExtension> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }