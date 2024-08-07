plugins {
//    alias(libs.plugins.android.application)
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
}

android {
    namespace = "com.sanhuzhen.module.mvplayer"
    compileSdk = 34

    defaultConfig {
//        applicationId = "com.sanhuzhen.module.mvplayer"
        minSdk = 24
        targetSdk = 34
//        versionCode = 1
//        versionName = "1.0"

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
    implementation(project(":module_musicplayer"))
    kapt("cn.therouter:apt:1.2.2")
    implementation("cn.therouter:router:1.2.2")
    implementation ("com.github.uaoan:DkPlayer:dkplayer-1.1")
    implementation ("xyz.doikki.android.dkplayer:dkplayer-java:3.3.7")
    implementation ("com.tencent:mmkv-static:1.3.0")
    //第三方播放库
    implementation("androidx.media3:media3-ui:1.3.1")
    implementation("androidx.media3:media3-session:1.3.1")
    implementation("androidx.media3:media3-exoplayer:1.3.1")

    implementation ("com.squareup.retrofit2:adapter-rxjava3:2.9.0")
    // 正常 RxJava 依赖
    implementation ("io.reactivex.rxjava3:rxjava:3.0.13")
    //RxJava 与 Android 绑定需要的库，⾥⾯有调度类，允许我们与安卓的线程结合达到切换线程⽬的
    implementation ("io.reactivex.rxjava3:rxandroid:3.0.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    implementation("com.github.bumptech.glide:compiler:4.12.0")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}