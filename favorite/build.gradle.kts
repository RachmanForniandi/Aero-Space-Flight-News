plugins {
    alias(libs.plugins.android.dynamic.feature)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias (libs.plugins.kotlin.parcelize)
    id("androidx.navigation.safeargs.kotlin")

}
android {
    namespace = "rachman.forniandi.favorite"
    compileSdk = 36



    defaultConfig {

        minSdk = 27
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
    api(libs.androidx.appcompat)

    //dagger hilt
    api (libs.hilt.android)
    //ksp(libs.androidx.navigation.safe.args.generator)
    ksp (libs.hilt.compiler)
    ksp (libs.dagger.compiler)

    //viewmodel
    api (libs.androidx.lifecycle.viewmodel.ktx)
    api (libs.androidx.activity.ktx)

    //Navigation
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(libs.androidx.navigation.dynamic.features.fragment)
    implementation(libs.gms.play.services.base)
    implementation(libs.gms.play.services.base.ktx)

    //glide
    api(libs.glide)

    //coil
    api(libs.coil3.coil)
    api(libs.coil3.okhttp)


    api(libs.androidx.constraintlayout)
    api(libs.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

}