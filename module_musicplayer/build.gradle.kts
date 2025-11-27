plugins {
    alias(libs.plugins.yzmusic.android.library)
}

useTherouter()
dependencies {
    implementation(projects.libBase)
    implementation(projects.libNet)
    implementation(libs.bundles.network)
    implementation(libs.bundles.glide)
    implementation(libs.bundles.projectBase)
    implementation(libs.androidx.ui.graphics.android)
    implementation(libs.bundles.media3)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.paging.runtime.ktx)
}