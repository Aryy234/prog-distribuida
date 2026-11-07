plugins {
    id("java")
    id("application")
    id("io.freefair.lombok") version "9.8.3"
    id("com.gradleup.shadow") version "9.6.1"
}

group = "org.example"
version = "unspecified"

application {
    mainClass.set("org.example.programacion.distribuida.web03.Web03Main")
}

repositories {
    mavenCentral()
}

val helidonVersion = "4.5.6"

dependencies {

    //HTTP
    implementation("io.helidon.webserver:helidon-webserver:$helidonVersion")

    //JSON
    implementation("io.helidon.http.media:helidon-http-media-jsonp:$helidonVersion")
    implementation("io.helidon.http.media:helidon-http-media-jsonb:$helidonVersion")

    //Configuracion
    implementation("io.helidon.config:helidon-config-yaml:${helidonVersion}")

    //
    implementation("io.helidon.dbclient:helidon-dbclient:${helidonVersion}")
    implementation("io.helidon.dbclient:helidon-dbclient-jdbc:${helidonVersion}")
    implementation("io.helidon.dbclient:helidon-dbclient-hikari:${helidonVersion}")
    implementation("org.postgresql:postgresql:42.7.14")



}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "org.example.programacion.web03.Web03Main"
    }
}

tasks.shadowJar{
    mergeServiceFiles()
}