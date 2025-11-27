plugins {
    alias(libs.plugins.yzmusic.android.library)
}

useTherouter()
dependencies {
    implementation(projects.libBase)
    implementation(projects.libNet)
    implementation(projects.moduleMusicplayer)
    implementation(libs.bundles.network)
    implementation(libs.bundles.glide)
    implementation(libs.bundles.projectBase)
    implementation (libs.bundles.dkplayer)
    implementation (libs.mmkv.static)
    implementation(libs.bundles.media3)
}