plugins {
    kotlin("jvm") version "1.7.20"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
}

group = "ru.spliterash"
version = "1.0.0"

bukkit {
    name = "ShulepaTestPlugin"
    main = "ru.spliterash.shulepaTestPlugin.ShulepaTestPlugin"
    apiVersion = "1.13"
    authors = listOf("Spliterash")
    libraries = listOf("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.4")

    commands {
        register("shulepa")
    }
}

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/") // PaperAPI

}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.2-R0.1-SNAPSHOT")
    compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.4")
}
kotlin {
    jvmToolchain(17)
}
