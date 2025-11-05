plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.daggerhilt)
    alias (libs.plugins.kotlin.parcelize)
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "rachman.forniandi.aerospaceflightnews"
    compileSdk = 36

    defaultConfig {
        applicationId = "rachman.forniandi.aerospaceflightnews"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled =false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled =false
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
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding= true

    }

    dynamicFeatures += setOf(":favorite")
}

dependencies {
    implementation (project(":core"))
    implementation (project(":favorite"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    //dagger hilt
    implementation (libs.hilt.android)
    ksp (libs.hilt.compiler)
    ksp (libs.dagger.compiler)

    //viewmodel
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.activity.ktx)

    //glide
    implementation(libs.glide)

    //coil
    implementation(libs.coil3.coil)
    implementation(libs.coil3.okhttp)

    //lifecycle
    implementation (libs.androidx.lifecycle.livedata.ktx)

    //Navigation
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)


    implementation(libs.androidx.swiperefreshlayout)

    //facebook shimmer
    implementation(libs.facebook.shimmer)

    //paging3
    implementation(libs.androidx.paging.runtime.ktx)
    
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}