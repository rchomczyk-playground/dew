plugins {
    `java-library`
    `maven-publish`
}

group = "dev.shiza"
version = "1.0.0-SNAPSHOT"

java {
    withSourcesJar()
}

publishing {
    repositories {
        mavenLocal()
    }
}

interface DewPublishExtension {
    var artifactId: String
}

val extension = extensions.create<DewPublishExtension>("dewPublish")

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                artifactId = extension.artifactId
                from(project.components["java"])
            }
        }
    }
}
