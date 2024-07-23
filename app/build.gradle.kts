plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("therouter")
    id("kotlin-kapt")
}

android {
    namespace = "com.sanhuzhen.yzmusic"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sanhuzhen.yzmusic"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    viewBinding{
        enable = true
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
    implementation(project(":lib_net"))
    implementation(project(":module_login"))
    implementation(project(":module_recommend"))
    implementation(project(":module_mine"))
    implementation(project(":module_hot"))
    implementation(project(":module_songlist"))
    implementation(project(":module_search"))
    implementation(project(":module_mvplayer"))
    implementation(project(":module_musicplayer"))

    implementation ("com.github.bumptech.glide:glide:4.12.0")
    implementation("com.github.bumptech.glide:compiler:4.12.0")

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