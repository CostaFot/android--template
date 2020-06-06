plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    greet
    todo
    id("packaging")
}

android {
    compileSdkVersion(Sdk.COMPILE_SDK_VERSION)
    testOptions.unitTests.isIncludeAndroidResources = true

    defaultConfig {
        minSdkVersion(Sdk.MIN_SDK_VERSION)
        targetSdkVersion(Sdk.TARGET_SDK_VERSION)

        applicationId = AppCoordinates.APP_ID
        versionCode = AppCoordinates.APP_VERSION_CODE
        versionName = AppCoordinates.APP_VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true

        buildConfigField("String", "serverUrl", "\"https://jsonplaceholder.typicode.com/\"")
    }

    signingConfigs {
        create("release") {
            /*keyAlias keystoreProperties['releaseKeyAlias']
              keyPassword keystoreProperties['releaseKeyPassword']
              storeFile rootProject.file("keystore.jks")
              storePassword keystoreProperties['releaseStorePassword']*/
        }
    }

    buildTypes {
        releaseBuild {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            applicationIdSuffix = ".release"
            versionNameSuffix = "-release"
        }

        debugBuild {
            isMinifyEnabled = false
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
    }

    flavorDimensions("style", "monetize")
    productFlavors {
        create("templateStyle") {
            dimension = "style"
            applicationIdSuffix = ".templateStyle"
            versionNameSuffix = "-templateStyle"
            resValue("string", "app_name", "Android Template")
            versionCode = 1
            versionName = "0.1"
        }

        create("free") {
            dimension = "monetize"
            applicationIdSuffix = ".free"
            versionNameSuffix = "-free"
        }

        create("premium") {
            dimension = "monetize"
            applicationIdSuffix = ".premium"
            versionNameSuffix = "-premium"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
        unitTests.isIncludeAndroidResources = true
    }

    packagingBoilerPlate()

    buildFeatures {
        viewBinding = true
    }

    lintOptions {
        isWarningsAsErrors = true
        isAbortOnError = true
    }

    // Configure the extension using a DSL block
    greeting {
        // Replace defaults here if you want
    }

    todo {
        // Replace defaults here if you want
        id = 2
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk7"))

    implementation(Support.appCompat)
    implementation(Support.material)
    implementation(Support.cardViewX)
    implementation(Support.constraintLayout)
    implementation(Support.recyclerview)
    implementation(Support.annotations)
    implementation(Ktx.fragment)
    implementation(Ktx.liveData)
    implementation(Ktx.savedState)
    implementation(Support.lifecycle)
    implementation(Ktx.viewModel)
    implementation(Ktx.core)
    implementation(Ktx.collections)
    implementation(Navigation.navigationFragments)
    implementation(Navigation.navigationUI)
    implementation(RxJava.rxJava2)
    implementation(RxJava.rxAndroid)
    implementation(Permissions.rx)
    implementation(Libs.rxBindingMaterial)
    implementation(Libs.gson)
    implementation(Retrofit.okHttp)
    implementation(Retrofit.loggingInterceptor)
    implementation(Retrofit.retrofit) {
        exclude("com.squareup.okhttp3", "okhttp")
    }
    implementation(Retrofit.gsonConverter) {
        exclude("com.google.code.gson", "gson")
    }
    implementation(Retrofit.rxJava) {
        exclude("io.reactivex.rxjava2", "rxjava")
    }

    implementation(Libs.reactiveNetwork)
    implementation(Dagger.dagger)
    kapt(Dagger.compiler)
    implementation(Dagger.android)
    implementation(Dagger.androidSupport)
    kapt(Dagger.processor)
    implementation(Libs.kotlinpref)
    implementation(Libs.timberLogger)

    implementation(Libs.toast)
    implementation(Libs.lovelydialog)
    implementation(Libs.flashbar)
    implementation(Libs.materialEditText)
    implementation(Libs.shimmer)
    implementation(Libs.gif)
    implementation(Libs.animations1)
    implementation(Libs.animations2)
    implementation(Libs.otto)
    debugImplementation(Libs.leakCanary)
    implementation(Firebase.analytics)
    implementation(Firebase.crashlytics)

    testImplementation(TestingLib.jUnit)
    testImplementation(TestingLib.jUnitKotlin)
    implementation(TestingLib.testCoreX)

    testImplementation(TestingLib.testArchCompX)
    testImplementation(TestingLib.robolectric)
    testImplementation(TestingLib.mockitoCore)
    testImplementation(TestingLib.mockitoKotlin)
    testImplementation(TestingLib.mockitoInline)
    testImplementation(TestingLib.runner)
    testImplementation(TestingLib.espresso)
    testImplementation(TestingLib.espressoIntents)
    testImplementation(TestingLib.rules)
    testImplementation(TestingLib.truth)
    testImplementation(TestingLib.kluent)
    testImplementation(TestingLib.coroutineTest)

    androidTestImplementation(TestingLib.jUnit)
    androidTestImplementation(TestingLib.jUnitKotlin)
    androidTestImplementation(TestingLib.testCoreX)
    androidTestImplementation(TestingLib.runner)
    androidTestImplementation(TestingLib.espresso)

    debugImplementation(TestingLib.fragmentScenario)

}
