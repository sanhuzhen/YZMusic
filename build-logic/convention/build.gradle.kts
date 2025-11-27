import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.sanhuzhen.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

// 验证插件是否正确
tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    // 注册插件
    plugins {
        register("yzmusicAndroidLibraryConventionPlugin") {
            id = "yzmusic.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
    }

    plugins {
        register("yzmusicAndroidApplicationConventionPlugin"){
            id = "yzmusic.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
    }
}