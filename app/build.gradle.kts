plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.baben.apps.appformation3"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.baben.apps.appformation3"
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


    dataBinding.enable = true
    viewBinding.enable = true
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.test:core-ktx:1.5.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    //SDP - a scalable size unit ->https://github.com/intuit/sdp
    implementation ("com.intuit.sdp:sdp-android:1.1.0")
    //SSP - a scalable size unit for texts -> https://github.com/intuit/ssp
    implementation("com.intuit.ssp:ssp-android:1.1.0")
    // RoundedImageView is no longer actively maintained as there are better alternatives available -> https://github.com/vinc3m1/RoundedImageView
    implementation("com.makeramen:roundedimageview:2.3.0")
    //A powerful image downloading and caching library for Android-> https://github.com/square/picasso
    implementation("com.squareup.picasso:picasso:2.71828")

    //https://github.com/google/gson
    implementation("com.google.code.gson:gson:2.10.1")
    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.51")
    kapt("com.google.dagger:hilt-android-compiler:2.51")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.5.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    //mokito


    // required if you want to use Mockito for unit tests
    testImplementation ("org.mockito:mockito-core:2.24.5")
    // required if you want to use Mockito for Android tests
    androidTestImplementation ("org.mockito:mockito-android:2.24.5")
}
// Allow references to generated code
kapt {
    correctErrorTypes = true
}