plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.pokemonfirebase"
    compileSdk = 34


    defaultConfig {
        applicationId = "com.example.pokemonfirebase"
        minSdk = 31
        targetSdk = 34
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Firebase dependencies using BOM
    implementation(platform(libs.firebase.bom.v3100)) // Agregar BOM
    implementation(libs.com.google.firebase.firebase.auth2)
    implementation(libs.google.firebase.firestore)

    // Google Sign-In
    implementation(libs.play.services.auth)

    // Retrofit and Gson Converter
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Picasso
    implementation (libs.picasso)

    // AndroidX and other dependencies
    implementation(libs.activity.ktx.v172)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    // Splash
    implementation(libs.core.splashscreen)

    // Test dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
