plugins {
    id("com.android.library")}

android {
    namespace = "edu.sdgku.wearfitness.shared"

    compileSdk {
        version = release(37) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = 30

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)

    implementation(
        platform("com.google.firebase:firebase-bom:34.16.0")
    )

    implementation(
        "com.google.firebase:firebase-firestore"
    )

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
}