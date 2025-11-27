package com.sanhuzhen.buildlogic

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

/**
 * @description:
 * @author: sanhuzhen
 * @date: 2025/11/27 16:10
 */
/**
 * 获取 libs 版本目录
 */
val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

/**
 * 获取版本号
 */
fun Project.libVersion(name: String): String = libs.findVersion(name).get().toString()

/**
 * 获取 library
 */
fun Project.lib(name: String) = libs.findLibrary(name).get()

/**
 * 获取 plugin
 */
fun Project.libPlugin(name: String) = libs.findPlugin(name).get()