plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.daggerhilt)
    alias (libs.plugins.kotlin.parcelize)
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "rachman.forniandi.core"
    compileSdk = 36

    defaultConfig {
        minSdk = 27
        targetSdk = 36

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        buildConfigField("String", "BASE_URL", "\"https://api.spaceflightnewsapi.net/v4/\"")
        buildConfigField("String", "DB_PASSPHRASE", "\"debug_passphrase_2026\"")

    }

    buildTypes {
        debug{
            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

        }

        release {
            isMinifyEnabled = true
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
        buildConfig= true
    }


}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.activity.ktx)


    //Retrofit
    implementation (libs.okhttp)
    implementation (libs.logging.interceptor)
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)

    //dynamic features



    //facebook shimmer
    implementation(libs.facebook.shimmer)

    //glide
    implementation(libs.glide)

    //coil
    implementation(libs.coil3.coil)
    implementation(libs.coil3.okhttp)


    implementation(libs.androidx.navigation.fragment.ktx)


    //gson
    implementation(libs.gson)

    //Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.paging)
    implementation(libs.room.ktx)
    implementation(libs.paging.runtime.ktx)
    implementation(libs.android.database.sqlcipher)
    implementation(libs.androidx.sqlite.ktx)
    ksp(libs.room.compiler)


    //datastore
    implementation(libs.androidx.datastore.preferences)

    //dagger hilt
    implementation (libs.hilt.android)
    ksp (libs.hilt.compiler)
    ksp (libs.dagger.compiler)

    //chucker
    debugImplementation(libs.chucker.library)
    releaseImplementation(libs.chucker.no.op)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}