plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

group = "org.example"
version = ""

tasks.named<Jar>("jar") {
    manifest {
        attributes["Main-Class"] = "org.example.WSQViewer"
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    implementation("com.machinezoo.sourceafis:sourceafis:3.18.1")
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks {
    withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
        archiveBaseName.set("wsqView")
        archiveClassifier.set("")
        manifest {
            attributes["Main-Class"] = "org.example.WSQViewer"
        }
    }
}

tasks.test {
    useJUnitPlatform()
}