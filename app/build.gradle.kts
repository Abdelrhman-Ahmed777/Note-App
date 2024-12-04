plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")

}

android {
    namespace = "com.example.note"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.note"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt") ,
                "proguard-rules.pro"
            )
        }
        buildFeatures {
            viewBinding = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18

    }
    kotlinOptions {
        jvmTarget = "18"

    }
}

dependencies {

    implementation(libs.androidx.core.ktx)

    implementation(libs.androidx.appcompat)

    implementation(libs.material)

    implementation(libs.androidx.activity)

    implementation(libs.androidx.constraintlayout)

    implementation(libs.support.annotations)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)

    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.recyclerview)

    implementation(libs.androidx.cardview)

    implementation(libs.material)

    implementation(libs.androidx.appcompat)

    implementation(libs.androidx.appcompat.resources) // Check for the latest version

    implementation(libs.material)

    implementation(libs.androidx.room.runtime)

    implementation(libs.material)

    ksp(libs.androidx.room.compiler)       // To use Kotlin Symbol Processing (KSP)

    implementation(libs.androidx.room.ktx)    // optional - Kotlin Extensions and Coroutines support for Room

    implementation(libs.androidx.room.rxjava2)    // optional - RxJava2 support for Room

    implementation(libs.androidx.room.rxjava3)    // optional - RxJava3 support for Room

    implementation(libs.androidx.room.guava)    // optional - Guava support for Room, including Optional and ListenableFuture

    testImplementation(libs.androidx.room.testing)     // optional - Test helpers

    implementation(libs.androidx.room.paging)     // optional - Paging 3 Integration

    implementation(libs.kotlinx.coroutines.android)    // optional - Kotlin Coroutines for Android

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
}


