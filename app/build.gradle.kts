plugins {
        id("com.android.application")
        id("org.jetbrains.kotlin.android")
        id("kotlin-kapt")
}

android {
    namespace = "com.example.thousandcourses"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.thousandcourses"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":feature_login"))
    implementation(project(":feature_main"))
    implementation(project(":feature_favorites"))
    implementation(project(":feature_account"))

    // Koin
    implementation(libs.koin.android)

    // Navigation
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    // Lifecycle
    implementation(libs.lifecycle.viewmodel)

    // Coroutines
    implementation(libs.coroutines.android)
    implementation(libs.coroutines.core)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
}