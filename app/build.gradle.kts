plugins {
    alias(libs.plugins.yzmusic.android.application)
}
dependencies {
    implementation(projects.libBase)
    implementation(projects.libNet)
    implementation(projects.moduleLogin)
    implementation(projects.moduleRecommend)
    implementation(projects.moduleMine)
    implementation(projects.moduleHot)
    implementation(projects.moduleSonglist)
    implementation(projects.moduleSearch)
    implementation(projects.moduleMvplayer)
    implementation(projects.moduleMusicplayer)

    implementation(libs.bundles.glide)

    //第三方播放库
    implementation(libs.bundles.media3)
    implementation(libs.bundles.projectBase)
}