import org.gradle.kotlin.dsl.maven

rootProject.name = "imcapi"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven { url = uri("https://packages.confluent.io/maven/") }
    }
}
