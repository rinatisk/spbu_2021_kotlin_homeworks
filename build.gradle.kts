import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.tasks.testing.logging.*

plugins {
    kotlin("jvm") version "1.4.31"
    id("io.gitlab.arturbosch.detekt") version "1.15.0"
    kotlin("plugin.serialization") version "1.4.31"
    id("org.jetbrains.dokka") version "1.4.20"
    id("org.openjfx.javafxplugin") version "0.0.8"
    application

}

group = "me.user"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("com.charleskorn.kaml:kaml:0.28.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.14.2")
    implementation("com.squareup:kotlinpoet:1.7.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.0")
    implementation("no.tornado:tornadofx:1.7.20")
    implementation("org.openjfx:javafx-base:11.0.2")
    implementation("org.openjfx:javafx:11.0.2")
    implementation("org.openjfx:javafx-controls:11.0.2")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.4.0")
}

javafx {
    modules("javafx.controls")
}

detekt {
    failFast = true // fail build on any finding
    detekt.buildUponDefaultConfig = true
    config = files("./config/detekt/detekt.yml")
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events(
            TestLogEvent.STANDARD_ERROR,
            TestLogEvent.STARTED,
            TestLogEvent.PASSED,
            TestLogEvent.FAILED,
            TestLogEvent.SKIPPED
        )
    }
}

tasks.withType<KotlinCompile>() {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Werror")
    }
}

application {
    mainClass.set("MainKt")
}
