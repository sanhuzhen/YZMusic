pluginManagement {
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
    }
}

rootProject.name = "YZMusic"
include(":app")
include("lib_base")
include(":lib_net")
include(":module_login")

include(":module_recommend")
include(":module_mine")
include(":module_hot")
include(":module_musicplayer")
include(":module_songlist")
include(":module_search")