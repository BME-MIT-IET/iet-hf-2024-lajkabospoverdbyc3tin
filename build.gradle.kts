/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java library project to get you started.
 * For more details on building Java & JVM projects, please refer to https://docs.gradle.org/8.7/userguide/building_java_projects.html in the Gradle documentation.
 */

plugins {
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
    id("application")
}

application {
    mainClass.set("src.Graphic")
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

sourceSets  {
    create("Sources") {
        java.srcDir("src")
    }
}

dependencies {
    // Use JUnit test framework.
    testImplementation(libs.junit)

    // This dependency is exported to consumers, that is to say found on their compile classpath.
    api(libs.commons.math3)

    // This dependency is used internally, and not exposed to consumers on their own compile classpath.
    sourceSets.named("Sources") {
        implementation(libs.guava)
    }
}

tasks.jar {
    manifest.attributes["Main-Class"] = application.mainClass
    manifest.attributes["Class-Path"] = configurations
        .runtimeClasspath
        .get()
        .joinToString(separator = " ") { file ->
            "libs/${file.name}"
        }
}


// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
