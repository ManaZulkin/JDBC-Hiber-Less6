plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.hibernate.orm:hibernate-core:6.2.5.Final")
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("log4j:log4j:1.2.17")
}

tasks.test {
    useJUnitPlatform()
}