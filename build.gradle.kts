plugins {
    java
    id("net.ltgt.errorprone") version "4.0.1"
    jacoco
}
allprojects {
    apply(plugin = "java")
    apply(plugin = "jacoco")
    apply(plugin = "net.ltgt.errorprone")

    repositories {
        mavenCentral()
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    dependencies {
        //Slf4j
        implementation ("org.slf4j:slf4j-api:2.0.13")
        //logback-classic
        implementation ("ch.qos.logback:logback-classic:1.5.6")
        //JUnit
        testImplementation(platform("org.junit:junit-bom:5.10.0"))
        testImplementation("org.junit.jupiter:junit-jupiter")

        errorprone("com.google.errorprone:error_prone_core:2.28.0")
    }

    tasks.test {
        useJUnitPlatform()
        finalizedBy(tasks.jacocoTestReport)
    }

    tasks.jacocoTestReport {
        dependsOn(tasks.test)
        reports {
            xml.required.set(true)
            csv.required.set(false)
            html.required.set(false)
        }
    }
}