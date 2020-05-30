plugins {
    java
    idea
}

apply(plugin = "java")
apply(plugin = "idea")

group = "com.billyyccc"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots") {
        mavenContent {
            snapshotsOnly()
        }
    }
}

val vertxVersion = "4.0.0-milestone5"
val testcontainersVersion = "1.12.3"

dependencies {
    testImplementation("io.vertx:vertx-mysql-client:$vertxVersion")
    testImplementation("io.vertx:vertx-junit5:$vertxVersion")
    testImplementation("org.testcontainers:junit-jupiter:$testcontainersVersion")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.test {
    useJUnitPlatform()
}