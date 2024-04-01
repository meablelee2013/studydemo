plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.ruma.moshidemo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ruma.moshidemo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.moshi:moshi-kotlin-codegen:1.15.0") // 或者使用最新版本
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")

    // Moshi核心库
    implementation("com.squareup.moshi:moshi:1.13.0") // 使用最新稳定版
    // 对于Kotlin的数据类，需要添加Moshi Kotlin扩展
    implementation("com.squareup.moshi:moshi-kotlin:1.13.0") // 如果使用反射模式
    // 添加Kotlin Coroutines的核心库依赖
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4") // 替换为最新的稳定版
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4") // 对于Android项目，还需要添加这个依赖

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-core-ktx:2.7.0")


    implementation("com.kunminx.arch:unpeek-livedata:7.8.0")



    implementation("com.google.android.gms:play-services-mlkit-document-scanner:16.0.0-beta1")
    implementation("com.github.bumptech.glide:glide:4.15.0")


    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

}
// Allow references to generated code
kapt {
    correctErrorTypes = true
}