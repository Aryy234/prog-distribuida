plugins {
    id("java")
    id("io.freefair.lombok") version "9.8.3"
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

val helidonVersion = "4.5.6"

dependencies {
    implementation("io.helidon.webserver:helidon-webserver:$helidonVersion")
    implementation("io.helidon.config:helidon-config-yaml:${helidonVersion}")
    implementation("io.helidon.dbclient:helidon-dbclient:${helidonVersion}")
    implementation("io.helidon.dbclient:helidon-dbclient-jdbc:${helidonVersion}")
    implementation("org.postgresql:postgresql:42.7.14")
}

tasks.test {
    useJUnitPlatform()
}