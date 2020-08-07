object Sdk {
    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = 29
    const val COMPILE_SDK_VERSION = 29
}

object Versions {
    const val KTLINT = "0.36.0"
    const val RETROFIT = "2.7.1"
    const val OKHTTP = "4.4.0"
    const val FRESCO = "2.0.0"
    const val GLIDE = "4.9.0"
    const val DAGGER = "2.26"
    const val ROOM = "2.1.0"
    const val NAVIGATION = "2.3.0-alpha02"
    const val COROUTINES = "1.3.3"
    const val ARROW = "0.10.2"
}

object Plugins {
    const val AGP = "com.android.tools.build:gradle:${BuildPluginsVersion.AGP}"
    const val DETEKT = "1.7.4"
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${BuildPluginsVersion.KOTLIN}"
    const val KTLINT = "9.2.1"
    const val DEPENDENCY_VERSION_CHECKER =
        "com.github.ben-manes:gradle-versions-plugin:${BuildPluginsVersion.DEPENDENCY_VERSION_CHECKER}"
    const val GOOGLE_SERVICES = "com.google.gms:google-services:${BuildPluginsVersion.GOOGLE_SERVICES}"
    const val CRASHLYTICS = "com.google.firebase:firebase-crashlytics-gradle:${BuildPluginsVersion.CRASHLYTICS}"
    const val PERFORMANCE = "com.google.firebase:perf-plugin:1.3.1"
}

object BuildPluginsVersion {
    const val AGP = "4.0.1"
    const val DETEKT = "1.7.4"
    const val KOTLIN = "1.3.72"
    const val KTLINT = "9.2.1"
    const val DEPENDENCY_VERSION_CHECKER = "0.28.0"
    const val GOOGLE_SERVICES = "4.3.3"
    const val CRASHLYTICS = "2.1.1"
}

object Support {
    const val appCompat = "androidx.appcompat:appcompat:1.2.0-alpha02"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.0-beta4"
    const val recyclerview = "androidx.recyclerview:recyclerview:1.2.0-alpha01"
    const val annotations = "androidx.annotation:annotation:1.1.0"
    const val material = "com.google.android.material:material:1.2.0-alpha05"
    const val cardViewX = "androidx.cardview:cardview:1.0.0"
    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:2.2.0"
}

object Libs {
    const val gson = "com.google.code.gson:gson:2.8.6"
    const val kotlinpref = "com.chibatching.kotpref:kotpref:2.8.0"
    const val otto = "com.squareup:otto:1.3.8"
    const val reactiveNetwork = "com.github.pwittchen:reactivenetwork-rx2:3.0.3"
    const val butterKnife = "com.jakewharton:butterknife:8.8.1"
    const val butterKnifeCompiler = "com.jakewharton:butterknife-compiler:8.8.1"
    const val googleLocation = "com.google.android.gms:play-services-location:16.0.0"

    // *** Toasty (leave this at 1.3.0 as the newer versions took away functionality)
    const val toast = "com.github.GrenderG:Toasty:1.3.0"
    const val groupie = "com.xwray:groupie:2.7.0"
    const val groupieKtx = "com.xwray:groupie-kotlin-android-extensions:2.7.0"
    const val materialPreLolipop = "com.github.rey5137:material:1.2.5"
    const val lottie = "com.airbnb.android:lottie:3.0.7"
    const val zxing = "com.google.zxing:core:3.2.1"
    const val boommenu              = "com.nightonke:boommenu:2.1.1"
    const val lovelydialog = "com.yarolegovich:lovely-dialog:1.1.0"
    const val bottomnav = "it.sephiroth.android.library.bottomnavigation:bottom-navigation:3.0.0"
    const val bubbleShowCase = "com.elconfidencial.bubbleshowcase:bubbleshowcase:1.3.1"
    const val flashbar = "com.andrognito.flashbar:flashbar:1.0.3"
    const val bubbleNavigation = "com.gauravk.bubblenavigation:bubblenavigation:1.0.7"
    const val materialEditText = "com.github.florent37:materialtextfield:1.0.7"
    const val shimmer = "com.facebook.shimmer:shimmer:0.5.0"
    const val switches = "com.github.angads25:toggle:1.1.0"
    const val bounceWithSound = "com.cleveroad:audiovisualization:1.0.1"
    const val animations1 = "com.daimajia.easing:library:2.1@aar"
    const val animations2 = "com.daimajia.androidanimations:library:2.3@aar"
    const val gif = "pl.droidsonroids.gif:android-gif-drawable:1.2.17"
    const val timberLogger = "com.jakewharton.timber:timber:4.7.1"
    const val kotlinLinter = "com.pinterest:ktlint:0.33.0"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:2.2"
    const val rxBindingMaterial = "com.jakewharton.rxbinding3:rxbinding-material:3.0.0-alpha2"
    const val rxView                = "com.jakewharton.rxbinding2:rxbinding:2.1.1" 
    const val rxViewV7              = "com.jakewharton.rxbinding2:rxbinding-appcompat-v7:2.1.1" 
    const val rxViewDesign          = "com.jakewharton.rxbinding2:rxbinding-design:2.1.1"
}

object RxJava {
    const val rxJava2 = "io.reactivex.rxjava2:rxjava:2.2.18"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.1"
}

object Permissions {
    const val rx = "com.github.tbruyelle:rxpermissions:0.10.2"
}

object Imaging {
    const val glide = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"
    const val fresco = "com.facebook.fresco:fresco:${Versions.FRESCO}"
    const val frescoWebP = "com.facebook.fresco:webpsupport:${Versions.FRESCO}"
    const val frescoAnimatedWebP = "com.facebook.fresco:animated-webp:${Versions.FRESCO}"
    const val frescoAnimatedBase = "com.facebook.fresco:animated-base:${Versions.FRESCO}"
}

object Navigation {
    const val navigationFragments = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
}

object Dagger {
    const val dagger = "com.google.dagger:dagger:${Versions.DAGGER}"
    const val compiler = "com.google.dagger:dagger-compiler:${Versions.DAGGER}"
    const val android = "com.google.dagger:dagger-android:${Versions.DAGGER}"
    const val androidSupport = "com.google.dagger:dagger-android-support:${Versions.DAGGER}"
    const val processor = "com.google.dagger:dagger-android-processor:${Versions.DAGGER}"
}

object Room {
    const val database = "android.arch.persistence.room:runtime:${Versions.ROOM}"
    const val kotlinExtensions = "androidx.room:room-ktx:${Versions.ROOM}"
    const val rxSupport = "androidx.room:room-rxjava2:${Versions.ROOM}"
    const val compiler = "android.arch.persistence.room:compiler:${Versions.ROOM}"
}

object Firebase {
    const val analytics = "com.google.firebase:firebase-analytics:17.2.2"
    const val performance = "com.google.firebase:firebase-perf:19.0.5"
    const val crashlytics = "com.google.firebase:firebase-crashlytics:17.0.0-beta01"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
    const val rxJava = "com.squareup.retrofit2:adapter-rxjava2:${Versions.RETROFIT}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"
}

object Ktx {
    const val core = "androidx.core:core-ktx:1.3.0-alpha01"
    const val fragment = "androidx.fragment:fragment-ktx:1.2.2"
    const val preference = "androidx.preference:preference-ktx:1.1.0"
    const val palette = "androidx.palette:palette-ktx:1.0.0"
    const val sqlite = "androidx.sqlite:sqlite-ktx:1.0.0"
    const val collections = "androidx.collection:collection-ktx:1.1.0"
    const val reactiveStreams = "androidx.lifecycle:lifecycle-reactivestreams-ktx:2.2.0"
    const val workManager = "android.arch.work:work-runtime-ktx:1.0.0"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0"
    const val savedState = "androidx.savedstate:savedstate-ktx:1.1.0-alpha01"
}

object Coroutines {
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"
}

object Arrow {
    const val arrowCore = "io.arrow-kt:arrow-core:${Versions.ARROW}"
    const val arrowSyntax = "io.arrow-kt:arrow-syntax:${Versions.ARROW}"
}

object TestingLib {
    const val jUnit = "androidx.test.ext:junit:1.1.2-alpha01"
    const val jUnitKotlin = "org.jetbrains.kotlin:kotlin-test-junit:1.3.40"
    const val testCoreX = "androidx.test:core:1.2.1-alpha01"
    const val testArchCompX = "androidx.arch.core:core-testing:2.1.0"
    const val robolectric = "org.robolectric:robolectric:4.3"
    const val mockitoCore = "org.mockito:mockito-core:3.0.0"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0"
    const val mockitoInline = "org.mockito:mockito-inline:3.0.0"
    const val runner = "androidx.test:runner:1.3.0-alpha01"
    const val espresso = "androidx.test.espresso:espresso-core:3.3.0-alpha01"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:3.1.0"
    const val espressoIdleConcurrent = "androidx.test.espresso.idling:idling-concurrent:3.1.0"
    const val espressoIdleResources = "androidx.test.espresso:espresso-idling-resource:3.1.0"
    const val espressoIntents = "androidx.test.espresso:espresso-intents:3.3.0-alpha01"
    const val rules = "androidx.test:rules:1.3.0-alpha01"
    const val truth = "androidx.test.ext:truth:1.3.0-alpha01"
    const val kluent = "org.amshove.kluent:kluent:1.51"
    const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.2"

    // use debugImplementation for fragment scenario!
    const val fragmentScenario = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.2"
    const val kakaoUiTesting = "com.agoda.kakao:kakao:2.0.0"
}
