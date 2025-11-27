plugins {
    alias(libs.plugins.yzmusic.android.library)
}

useTherouter()
dependencies {
    implementation(projects.libBase)
    implementation(projects.libNet)
    implementation(projects.moduleMine)
    implementation(libs.bundles.network)
    implementation(libs.bundles.glide)
    implementation(libs.bundles.projectBase)
    implementation(libs.androidx.viewpager2)
    implementation(libs.androidx.lifecycle.viewmodel)
}