plugins {
    id("com.android.application") version "9.5.2" apply false
    id("com.android.library") version "9.5.2" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "2.4.21" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.4.21" apply false
}

android {
    namespace "com.geode.launcher"
    compileSdk 35

    defaultConfig {
        applicationId "com.geode.launcher"
        minSdk 21
        targetSdk 35
        versionCode 1
        versionName "1.0.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        view false
        compose false
    }

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    externalNativeBuild {
        cmake {
            cppFlags "-std=c++17 -frtti -fexceptions"
            abiFilters "armeabi-v7a", "arm64-v8a"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-ktx:1.7.2")
    implementation("androidx.compose.material3:material3:1.2.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
}

externalNativeBuild {
    cmake {
        path "app/src/main/cpp/CMakeLists.txt"
        cppStandard "17"
    }
}
