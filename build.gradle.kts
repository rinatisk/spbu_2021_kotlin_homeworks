import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.30"
    id("io.gitlab.arturbosch.detekt") version "1.15.0"
    application
    id("org.jetbrains.dokka") version "1.4.20"
}

group = "me.user"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    testImplementation(kotlin("test-junit"))

    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.14.2")
}

detekt {
    failFast = true // fail build on any finding
    detekt.buildUponDefaultConfig = true
    config = files("./config/detekt/detekt.yml")
}

tasks {
    dokkaHtml {
        dokkaSourceSets {
            configureEach() {
                includes.setFrom(listOf("src/main/kotlin/action/action.md", "src/main/kotlin/thirdtask/3-task.md", "src/main/kotlin/commandstorage/commandStorage.md"))
            }
        }
    }
}

tasks.test {
    useJUnit()
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