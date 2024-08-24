plugins {
    java
    id("io.freefair.lombok") version "8.10"
    id("com.gradleup.shadow") version "8.3.0"
    id("io.papermc.paperweight.userdev") version "1.7.1"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "dev.j3fftw"
version = "MODIFIED"
description = "LiteXpansion"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
//    maven {
//        url = uri("https://repo.maven.apache.org/maven2/")
//    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
    paperweight.paperDevBundle("1.20.4-R0.1-SNAPSHOT")
    implementation(libs.org.bstats.bstats.bukkit)

    compileOnly(libs.com.github.slimefun.slimefun4)
    implementation(libs.com.github.slimefun.addon.community.extrautils)
}

tasks {
    runServer {
        downloadPlugins {
            hangar("viaversion", "5.0.3")
        }
        minecraftVersion("1.20.4")
    }
    shadowJar {
        relocate("org.bstats", "dev.j3fftw.litexpansion.libs.bstats")
    }
}

val targetJavaVersion = 17

java {
    val javaVersion = JavaVersion.toVersion(targetJavaVersion)
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion

    if (JavaVersion.current() < javaVersion) {
        toolchain.languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"

    if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible()) {
        options.release.set(targetJavaVersion)
    }
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}
