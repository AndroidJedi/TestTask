import Versions.jetpack
import Versions.kotlin
import Versions.material

object Versions {
    const val kotlin           = "1.2.71"
    const val androidGradle    = "3.2.0"
    const val material         = "1.0.0"
    const val jetpack          = "1.0.0"
    const val lifecycle        = "2.0.0"
    const val constraintLayout = "2.0.0-alpha2"
    const val navigation       = "1.0.0-alpha06"
    const val mockito          = "2.22.0"
    const val junit            = "4.12"
    const val espresso         = "3.1.0-alpha4"
    const val gson             = "2.8.5"
    const val okhttp           = "3.11.0"
    const val retrofit         = "2.4.0"
    const val rxjava           = "2.2.2"
    const val rxandroid        = "2.1.0"
    const val glide            = "4.8.0"
    const val mockitoKotlin    = "1.6.0"
    const val viewmodelKtx     = "2.0.0"
    const val koin             = "1.0.1"
    const val robolectric      = "4.0-alpha-3"
    const val kotlinArgParser  = "2.0.6"
    const val timber           = "4.7.1"
}


object BuildPlugins {
    val androidGradle      = "com.android.tools.build:gradle:${Versions.androidGradle}"
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin"
}

object Android {
    val buildToolsVersion  = "27.0.3"
    val minSdkVersion      = 23
    val targetSdkVersion   = 28
    val compileSdkVersion  = 28
    val applicationId      = "com.medisafe.task"
    val versionCode        = 1
    val versionName        = "0.1"
}

object Libs {
    val kotlin_std          = "org.jetbrains.kotlin:kotlin-stdlib:$kotlin"
    val appcompat           = "androidx.appcompat:appcompat:$jetpack"
    val cardview            = "androidx.cardview:cardview:$jetpack"
    val fragmentKtx         = "androidx.fragment:fragment-ktx:$jetpack"
    val coreKtx             = "androidx.core:core-ktx:$jetpack"
    val material            = "com.google.android.material:material:${Versions.material}"
    val constraintLayout    = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val lifecycle           = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    val lifecycleExtentions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val okhttp              = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    val okhttp_interceptor  = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    val retrofit            = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofit_gson       = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val retrofit_rx_adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val rxjava              = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
    val rxandroid           = "io.reactivex.rxjava2:rxandroid:${Versions.rxandroid}"
    val glide               = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler       = "com.github.bumptech.glide:compiler:${Versions.glide}"
    val koinViewModel       = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    val timber              = "com.jakewharton.timber:timber:${Versions.timber}"
}

object TestLibs {
    val junit             = "junit:junit:4.12"
    val mockito           = "org.mockito:mockito-core:${Versions.mockito}"
    val espresso          = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    val mockitoKotlin     = "com.nhaarman:mockito-kotlin:${Versions.mockitoKotlin}"
    val testRunner        = "androidx.arch.core:core-testing:${Versions.lifecycle}"
}