// Top-level build file where you can add configuration options common to all sub-projects/modules.


buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath (BuildPlugins.androidGradle)
        classpath (BuildPlugins.kotlinGradlePlugin)


        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

val clean: Delete by tasks { // creates a new object in the container
    delete("buildDir")
}
