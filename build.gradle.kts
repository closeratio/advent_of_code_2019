import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61")
    }
}

plugins {
    java
    kotlin("jvm") version "1.3.61"
}

allprojects {

    apply(plugin = "kotlin")
    apply(plugin = "java")

    repositories {
        mavenCentral()
    }

    dependencies {

        if (project.name.startsWith("day_")) {
            implementation(project(":aoc_2019:common"))
        }

        implementation(kotlin("stdlib-jdk8"))

        testImplementation("junit:junit:4.12")
        testImplementation("org.hamcrest:hamcrest-all:1.3")
    }

    val compileKotlin: KotlinCompile by tasks
    compileKotlin.kotlinOptions {
        jvmTarget = "1.8"
    }

    val compileTestKotlin: KotlinCompile by tasks
    compileTestKotlin.kotlinOptions {
        jvmTarget = "1.8"
    }
}


