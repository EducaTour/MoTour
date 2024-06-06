import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)

}

android {
    val secretPropertiesFile = rootProject.file("gradle.properties")
    val secretProperties = Properties()
    secretProperties.load(FileInputStream(secretPropertiesFile))


    namespace = "com.dicoding.motour"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dicoding.motour"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "BASE_URL", "\"${secretProperties["BASE_URL"]}\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    // ViewModel
    implementation(libs.androidx.viewmodel)
    // LiveData
    implementation(libs.androidx.liveData)
    // Saved state module for ViewModel
    implementation(libs.androidx.savedState)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.databinding.runtime)
    //Annotation processor
    ksp(libs.androidx.anotation)
    // Room
    implementation(libs.androidx.room)
    // Room ktx
    implementation(libs.androidx.room.ktx)
    // anotation processor for room
    annotationProcessor(libs.androidx.roomCompiler)
    // To use Kotlin annotation processing tool (ksp)
    ksp(libs.androidx.roomCompiler)
    // Coroutines core
    implementation(libs.kotlinx.coroutines.core)
    // Coroutines Android
    implementation(libs.kotlinx.coroutines.android)
    // dagger
    implementation(libs.dagger)
    // ksp dagger
    ksp(libs.daggerCompiler)
    // square retrofit
    implementation(libs.square.retrofit)
    // square converter
    implementation(libs.square.gson)
    // square logging interceptor
    implementation(libs.okhttp.interceptor)
    // glide
    implementation(libs.glide)
    // glide compiler
    ksp(libs.glideCompiler)
//     navigation fragment
    implementation(libs.navigation.fragment.ktx)
//     navigation ui
    implementation(libs.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.circleimageview)

    // CameraX
    implementation(libs.camerax.camera2)
    implementation(libs.camerax.lifecycle)
    implementation(libs.camerax.view)

}