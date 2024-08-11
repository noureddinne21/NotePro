import org.gradle.internal.impldep.com.amazonaws.auth.profile.internal.AllProfiles

plugins {
    id("com.android.application")
}


android {
    namespace = "com.nouroeddinne.notepro"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.nouroeddinne.notepro"
        minSdk = 28
        targetSdk = 34
        versionCode = 2
        versionName = "1.1"

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
}

dependencies {

    implementation("de.raphaelebner:roomdatabasebackup:1.0.1")


    implementation("com.readystatesoftware.sqliteasset:sqliteassethelper:+")
    implementation( "androidx.room:room-runtime:2.6.1")
    annotationProcessor( "androidx.room:room-compiler:2.6.1")



    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}