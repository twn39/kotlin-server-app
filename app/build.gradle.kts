/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/7.4.2/userguide/building_java_projects.html
 */

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.7.10"

    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.10")
    implementation("io.javalin:javalin:4.6.4")
    implementation("org.slf4j:slf4j-simple:1.7.31")
    implementation("org.jetbrains.exposed", "exposed-core", "0.39.2")
    implementation("org.jetbrains.exposed", "exposed-dao", "0.39.2")
    implementation("org.jetbrains.exposed", "exposed-jdbc", "0.39.2")
    implementation("org.jetbrains.exposed:exposed-java-time:0.39.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.3")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.3")
    implementation("com.h2database:h2:2.1.214")
    implementation("org.springframework.security:spring-security-crypto:5.7.3")
    implementation("commons-logging:commons-logging:1.2")
    implementation("org.bouncycastle:bcpkix-jdk15on:1.70")
    implementation("io.lettuce:lettuce-core:6.2.0.RELEASE")
    implementation("org.kodein.di:kodein-di:7.12.0")

    // This dependency is used by the application.
    implementation("com.google.guava:guava:30.1.1-jre")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    // Define the main class for the application.
    mainClass.set("kotlinapp.AppKt")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

kotlin {
    jvmToolchain {
        this.languageVersion.set(JavaLanguageVersion.of(11))
    }
}