val Project.projectRoot: String
  get() = projectDir.absolutePath

plugins {
  java
  id("com.github.johnrengelman.shadow") version "7.0.0"
  id("maven-publish")
}

group = "me.chrommob"
version = "1.0.2"

repositories {
  maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
  maven("https://oss.sonatype.org/content/repositories/snapshots")
  maven("https://oss.sonatype.org/content/repositories/central")
  maven("https://repo.janmm14.de/repository/intave-releases/")
  mavenCentral()
}


dependencies {
  // Pick only one of these and read the comment in the repositories block.
  compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
  compileOnly("de.jpx3.intave.access:intave-access:14.4.2")
  implementation("org.bstats:bstats-bukkit:3.0.0")
}

tasks.shadowJar {
  minimize()
  relocate("org.bstats", "me.chrommob.bstats")
}