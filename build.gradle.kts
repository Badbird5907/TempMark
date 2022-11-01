plugins {
    id("java")
    id("io.freefair.lombok") version "6.5.1"
    `maven-publish`
}

group = "dev.badbird"
version = "1.0-SNAPSHOT"
description = "A markdown templating engine"


repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    implementation("com.google.guava:guava:31.1-jre")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
/*
task sourcesJar(type: Jar) {
    classifier 'sources'
    from sourceSets.main.allSource
}
task javadocJar(type: Jar) {
    classifier 'javadoc'
    from sourceSets.main.allJava
}
artifacts {
    archives javadocJar
    archives sourcesJar
}
*/
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
        create<MavenPublication>("mavenJava") {
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
