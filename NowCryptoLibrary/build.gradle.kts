//plugins {
//    id("com.android.library") version "8.13.2"
//    id("org.jetbrains.kotlin.android") version "2.1.0"
//    id("org.jetbrains.kotlin.plugin.compose") version "2.1.0"
//    id("com.google.devtools.ksp") version "2.1.0-1.0.29"
//}

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.0"
}

android {
    namespace = "io.nowcrypto.sdk"
    compileSdk = 35 // Standardized to stable API 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

kotlin {
    jvmToolchain(11)
    //explicitApi()
}

dependencies {
    // Standard Android Libraries
    implementation("androidx.core:core-ktx:1.12.0") // Downgraded slightly for better compatibility
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0") // More stable base

    // Compose - Changed to a 2024 BOM for maximum compatibility
    implementation(platform("androidx.compose:compose-bom:2024.04.01"))
    implementation("androidx.activity:activity-compose:1.8.2") // Matches 2024 BOM better
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.runtime:runtime-livedata")

    // NAVIGATION WARNING: For an SDK, it's best to manage screens manually.
    // If you keep this, use a more compatible version:
    implementation("androidx.navigation:navigation-compose:2.7.7")

    implementation("androidx.compose.material:material-icons-extended")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.04.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Retrofit & Network
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0") // 5.x is still alpha/beta, 4.12 is safer for SDKs
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Kotlinx Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3") // Matches older Kotlin better
    implementation("com.squareup.retrofit2:converter-kotlinx-serialization:2.11.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Coil for Images
    implementation("io.coil-kt:coil-compose:2.6.0")
}
