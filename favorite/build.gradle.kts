plugins {
    alias(libs.plugins.android.dynamic.feature)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias (libs.plugins.kotlin.parcelize)

}
android {
    namespace = "rachman.forniandi.favorite"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":app"))
    implementation(project(":core"))
    implementation(libs.androidx.appcompat)

    //dagger hilt
    implementation (libs.hilt.android)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.material)
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

    //Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    //feature delivery
    implementation(libs.feature.delivery.ktx)
}