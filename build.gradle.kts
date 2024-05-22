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
    main {
        java {
            srcDir("HwProject/src/")
        }
    }
    test {
        java {
            srcDirs("HwProject/tests", "HwProject/tests/HelperClasses", "HwProject/tests/gui")
        }
    }

    //Creating JMH sourceSet for benchmark classes
    create("jmh") {
        java.srcDirs("HwProject/jmh", "HwProject/src", "HwProject/tests/HelperClasses")
        compileClasspath += sourceSets.main.get().output + sourceSets.main.get().compileClasspath + sourceSets.main.get().runtimeClasspath
        runtimeClasspath += sourceSets.main.get().output + sourceSets.main.get().compileClasspath + sourceSets.main.get().runtimeClasspath

    }
}

dependencies {

    // This dependency is used internally, and not exposed to consumers on their own compile classpath.
    sourceSets.named("main") {
        implementation(libs.guava)
    }

    // Use JUnit Jupiter API for testing.
    implementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")

    //Using JMH for benchmarks
    "jmhImplementation"("org.openjdk.jmh:jmh-core:1.37")
    "jmhAnnotationProcessor"("org.openjdk.jmh:jmh-generator-annprocess:1.37")

    //AsserJ Swing for GUI tests
    implementation("org.assertj:assertj-swing:3.17.1")
    testImplementation("org.assertj:assertj-swing:3.17.1")

    testImplementation("org.openjdk.jmh:jmh-core:1.35")
}

tasks.test {
    useJUnitPlatform()
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

tasks.named<Test>("test") {
    useJUnitPlatform()
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

//Tasks for proper benchmarks
tasks.register<JavaExec>("jmh") {
    dependsOn("jmhClasses")
    mainClass.set("org.openjdk.jmh.Main")
    classpath = sourceSets.named("jmh").get().compileClasspath + sourceSets.named("jmh").get().runtimeClasspath
}

tasks.named("classes") {
    finalizedBy("jmhClasses")
}
