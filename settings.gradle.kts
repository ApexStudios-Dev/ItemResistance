pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.apexmodder.com/releases")
    }

    if(file("../../ApexGradle").exists()) {
        includeBuild("../../ApexGradle")
    } else {
        resolutionStrategy {
            eachPlugin {
                if(requested.id.namespace == "apex-conventions") {
                    useVersion("0.1.101")
                }
            }
        }
    }
}

dependencyResolutionManagement {
    versionCatalogs.create("libs") {
        version("neoforge", "26.3.0.0-beta")

        library("registree", "dev.apexstudios", "registree").version("26.3.1-beta-pr-37")
        library("apexcore", "dev.apexstudios", "apexcore").version("26.3.0-beta-pr-96")
        bundle("apexcore", listOf("registree", "apexcore"))
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "ItemResistance"
