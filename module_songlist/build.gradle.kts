plugins {
//    alias(libs.plugins.android.application)
    alias(libs.plugins.android.library)
    kotlin("kapt")
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.sanhuzhen.module.songlist"
    compileSdk = 34

    defaultConfig {
//        applicationId = "com.sanhuzhen.module.songlist"
        minSdk = 24
        targetSdk = 34
//        versionCode = 1
//        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":lib_base"))
    kapt("cn.therouter:apt:1.2.2")
    implementation("cn.therouter:router:1.2.2")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}