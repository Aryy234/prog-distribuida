plugins {
    id("java")
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jboss.resteasy:resteasy-core:7.0.5.Final")
    implementation("org.jboss.resteasy:resteasy-undertow:7.0.5.Final")
}

tasks.test {
    useJUnitPlatform()
}