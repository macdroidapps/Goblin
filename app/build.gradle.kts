plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {

    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.packageName
        minSdk = Config.minSDK
        targetSdk = Config.targetSDK
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(Modules.login_feature))

    //Compose
    implementation(Compose.compiler)
    implementation(Compose.ui)
    implementation(Compose.material)
    implementation(Compose.uiToolingPreview)
    implementation(Compose.uiTooling)
    implementation(Compose.accompanist)
    implementation(Compose.navigation)
    implementation(Compose.livedata)

    //Hilt
    implementation(Hilt.android)
    implementation(Hilt.navigation)
    kapt(Hilt.compiler)

    // Retrofit
    implementation(Retrofit.okHttp)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.okHttpLoggingInterceptor)
    implementation(Retrofit.converteGson)

    // Coil
    implementation(Coil.coilCompose)

    //Room
    kapt(Room.roomCompiler)
    implementation(Room.roomKtx)
    implementation(Room.roomRuntime)

    // -- Lifecycle Components (ViewModel, LiveData and ReactiveStreams)
    implementation(LyfeCycle.lifecycleRuntime)
    implementation(LyfeCycle.lifecycleRuntimeKtx)
    implementation(LyfeCycle.lifecycleViewmodel)
    implementation(LyfeCycle.lifecycleViewmodelKtx)
    implementation(LyfeCycle.lifecycleLivedata)

    // Coroutines
    implementation(Coroutines.coroutinesCore)
    implementation(Coroutines.coroutinesAndroid)
    implementation(Coroutines.coroutinesLyfeCycleScope)

    //Tests

    testImplementation(Testing.composeUiTest)
    debugImplementation(Testing.testManifest)


    testImplementation(Testing.junit4)
//    testImplementation(Testing.junitAndroidExt)
//    testImplementation(Testing.truth)
//    testImplementation(Testing.coroutines)
//    testImplementation(Testing.turbine)
//
//    testImplementation(Testing.mockk)
//    testImplementation(Testing.mockWebServer)
//
    androidTestImplementation(Testing.junit4)
//    androidTestImplementation(Testing.junitAndroidExt)
//    androidTestImplementation(Testing.truth)
//    androidTestImplementation(Testing.coroutines)
//    androidTestImplementation(Testing.turbine)
    androidTestImplementation(Testing.composeUiTest)
//    androidTestImplementation(Testing.mockkAndroid)
//    androidTestImplementation(Testing.mockWebServer)
//    androidTestImplementation(Testing.hiltTesting)
//    kaptAndroidTest(Hilt.compiler)
//    androidTestImplementation(Testing.testRunner)
//
    debugImplementation(Compose.uiTooling)
}