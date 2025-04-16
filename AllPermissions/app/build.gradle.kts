plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kapt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.protobuf)
}

android {
    namespace = "in.kaligotla.allpermissions"
    compileSdk = 35

    defaultConfig {
        applicationId = "in.kaligotla.allpermissions"
        minSdk = 26
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Lifecycle
    implementation(libs.androidx.lifecycle.livedata.ktx)

    //Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.compiler)

    //room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    //gson
    implementation(libs.converter.gson)

    //retrofit
    implementation(libs.retrofit)

    //probuf datastore
    implementation(libs.androidx.datastore)
    implementation(libs.datastore.proto)
    implementation(libs.protobuf.javalite)

    //navigation-compose
    implementation(libs.androidx.navigation.compose)
    implementation(platform(libs.androidx.compose.bom))

    //accompanist
    implementation(libs.accompanist.permissions)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.accompanist.webview)

    //coil
    implementation(libs.coil.compose)

    //biometric
    implementation(libs.androidx.biometric)

    //play-services-location
    implementation(libs.play.services.location)

    //material-icons-extended
    implementation(libs.androidx.material.icons.extended)

    //media3-exoplayer
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.ui)
    implementation(libs.androidx.media3.common)

    //dagger.hilt.android.gradle.plugin
    implementation(libs.com.google.dagger.hilt.android.gradle.plugin)
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:4.30.2"
    }

    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                create("java") {
                    option("lite")
                }
            }
            task.plugins {
                kotlin {}
            }
        }
    }
}