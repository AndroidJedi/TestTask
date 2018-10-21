plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}



android {
    compileSdkVersion(Android.compileSdkVersion)
    defaultConfig {
        applicationId = Android.applicationId
        versionCode = Android.versionCode
        versionName = Android.versionName
        minSdkVersion(Android.minSdkVersion)
        targetSdkVersion(Android.targetSdkVersion)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        dataBinding {
            isEnabled = true
        }
        compileOptions {
            setSourceCompatibility(JavaVersion.VERSION_1_8)
            setTargetCompatibility(JavaVersion.VERSION_1_8)
        }
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libs.kotlin_std)
    implementation(Libs.appcompat)
    implementation(Libs.constraintLayout)
    implementation(Libs.material)
    implementation(Libs.lifecycle)
    implementation(Libs.lifecycleExtentions)
    implementation(Libs.okhttp)
    implementation(Libs.okhttp_interceptor)
    implementation(Libs.retrofit)
    implementation(Libs.retrofit_gson)
    implementation(Libs.retrofit_rx_adapter)
    implementation(Libs.rxjava)
    implementation(Libs.rxandroid)
    implementation(Libs.glide)
    kapt          (Libs.glideCompiler)
    implementation(Libs.koinViewModel)
    implementation(Libs.timber)
    implementation(Libs.fragmentKtx)
    implementation(Libs.coreKtx)

    testImplementation(TestLibs.junit)
    testImplementation(TestLibs.testRunner)
    testImplementation(TestLibs.mockitoKotlin)
    androidTestImplementation(TestLibs.espresso)
}
