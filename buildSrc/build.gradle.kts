plugins {
    `kotlin-dsl`
}

group = "dev.shiza"
version = "1.0.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation(kotlin("script-runtime"))
}