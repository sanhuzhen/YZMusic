plugins {
    alias(libs.plugins.yzmusic.android.library)
}

useTherouter()
dependencies {
    implementation(projects.libBase)
    implementation(projects.libNet)
    implementation(projects.moduleSonglist)
    implementation(libs.bundles.network)
    implementation(libs.bundles.glide)
    implementation(libs.bundles.projectBase)
    implementation (libs.androidx.browser)
    implementation(libs.androidx.swiperefreshlayout)
}