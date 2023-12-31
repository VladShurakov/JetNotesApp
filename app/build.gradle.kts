plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.vladshurakov.jetnotesapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.vladshurakov.jetnotesapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 5
        versionName = "1.2.0"
        resourceConfigurations += listOf("en")

        vectorDrawables {
            useSupportLibrary = true
        }
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = "1.5.3" }
    packaging { resources { excludes += "/META-INF/{AL2.0,LGPL2.1}" } }
    buildToolsVersion = "34.0.0"
}

dependencies {
    val room_version = "2.6.1"
    val hilt_version = "2.48.1"
    val coroutines_version = "1.7.3"

    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    ksp("com.google.dagger:hilt-android-compiler:$hilt_version")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.5")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")

    // Room
    implementation("androidx.room:room-runtime:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    // Accompanist - System UI Controller
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.32.0")

    // Pretty Time
    implementation ("org.ocpsoft.prettytime:prettytime:5.0.4.Final")

    // Gson
    implementation("com.google.code.gson:gson:2.10.1")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.compose.ui:ui:1.5.4")
    implementation("androidx.compose.ui:ui-graphics:1.5.4")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.4")
    implementation("androidx.compose.material3:material3:1.1.2")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01"))
}