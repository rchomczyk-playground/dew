plugins {
    id("java")
    id("maven-publish")
    id("pl.allegro.tech.build.axion-release") version("1.18.0")
}

group = "dev.shiza"
version = scmVersion.version

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

tasks.test {
    useJUnitPlatform()
}