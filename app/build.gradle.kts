plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")

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
    implementation(libs.support.annotations)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.cardview)
    implementation(libs.material)
    implementation(libs.androidx.appcompat)

    implementation("androidx.appcompat:appcompat-resources:1.7.0") // Check for the latest version


    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")

    ksp("androidx.room:room-compiler:$room_version")       // To use Kotlin Symbol Processing (KSP)

    implementation("androidx.room:room-ktx:$room_version")    // optional - Kotlin Extensions and Coroutines support for Room

    implementation("androidx.room:room-rxjava2:$room_version")    // optional - RxJava2 support for Room

    implementation("androidx.room:room-rxjava3:$room_version")    // optional - RxJava3 support for Room

    implementation("androidx.room:room-guava:$room_version")    // optional - Guava support for Room, including Optional and ListenableFuture

    testImplementation("androidx.room:room-testing:$room_version")     // optional - Test helpers

    implementation("androidx.room:room-paging:$room_version")     // optional - Paging 3 Integration
}