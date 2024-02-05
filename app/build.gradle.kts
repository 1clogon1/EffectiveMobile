plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.effective_mobile"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.effective_mobile"
        minSdk = 28
        //noinspection OldTargetApi
        targetSdk = 33
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
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures{
        viewBinding =true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
//    implementation("androidx.databinding:baseLibrary:3.2.0-alpha11")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("androidx.fragment:fragment-ktx:1.3.6")

    //Слайдер
    implementation("com.github.denzcoskun:ImageSlideshow:0.1.0")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    //OkHttp
    implementation ("com.squareup.okhttp3:logging-interceptor:4.7.2")
    implementation ("com.squareup.okhttp3:okhttp:4.7.2")

    //Room
    implementation("androidx.room:room-ktx:2.4.3")
    kapt("androidx.room:room-compiler:2.4.3")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
}