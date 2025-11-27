enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url =uri("https://jitpack.io") }
    }
}

rootProject.name = "YZMusic"
// 自动包含所有模块
rootDir
    .listFiles { file -> file.isDirectory && (File(file, "build.gradle.kts").exists() || File(file, "build.gradle").exists()) }
    ?.forEach { dir ->
        include(":${dir.name}")
        project(":${dir.name}").projectDir = dir
    }

// 如果 build 窗口乱码，去 顶部栏 - Help - Edit Custom VM Options 里面添加 -Dfile.encoding=UTF-8，然后重启 AS
// 制作网址：http://patorjk.com/software/taag/
val redrock = """
░▒▓█▓▒░░▒▓█▓▒░▒▓████████▓▒░▒▓██████████████▓▒░░▒▓█▓▒░░▒▓█▓▒░░▒▓███████▓▒░▒▓█▓▒░░▒▓██████▓▒░  
░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ 
░▒▓█▓▒░░▒▓█▓▒░    ░▒▓██▓▒░░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░▒▓█▓▒░        
 ░▒▓██████▓▒░   ░▒▓██▓▒░  ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░░▒▓█▓▒░▒▓█▓▒░        
   ░▒▓█▓▒░    ░▒▓██▓▒░    ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░▒▓█▓▒░▒▓█▓▒░        
   ░▒▓█▓▒░   ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ 
   ░▒▓█▓▒░   ░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░░▒▓███████▓▒░░▒▓█▓▒░░▒▓██████▓▒░  
""".trimIndent()
println(redrock)

// 新增模块会出现在下面include(":xx")，请删除，上面会自动增加
