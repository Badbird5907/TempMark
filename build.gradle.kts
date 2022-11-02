plugins {
    id("java")
    id("io.freefair.lombok") version "6.5.1"
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "dev.badbird"
version = "0.0.1"
description = "A markdown templating engine"


repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:31.1-jre") {
        exclude(group = "org.checkerframework")
    }
}
tasks {
    shadowJar {
        archiveClassifier.set("")
        //relocate("com.google", "dev.badbird.markdown.relocated.google")
    }
}

publishing {
    repositories {
        maven {
            val releasesRepoUrl = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2"
            val snapshotsRepoUrl = "https://oss.sonatype.org/content/repositories/snapshots/"
            //url = version.endsWith("SNAPSHOT") ? snapshotsRepoUrl : releasesRepoUrl
            url = uri(releasesRepoUrl);
            credentials {
                val nexusUsername: String by project
                val nexusPassword: String by project
                username = nexusUsername
                password = nexusPassword
            }
        }
    }
    publications {
        create<MavenPublication>("shadow") {
            artifact(tasks["shadowJar"])
            pom {
                name.set("TempMark")
                description.set("A templating engine for markdown")
                url.set("https://badbird.dev/")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT/")
                    }
                }
                developers {
                    developer {
                        id.set("Badbird5907")
                        name.set("Badbird5907")
                        email.set("badbird@badbird5907.net")
                    }
                }
                scm {
                    url.set("https://github.com/Badbird5907/TempMark/")
                }
            }
        }
    }
}
