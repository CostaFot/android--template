@file:Suppress("SpellCheckingInspection")

object Support {
    const val appCompat = "androidx.appcompat:appcompat:1.3.0-alpha01"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.0-rc1"
    const val recyclerview = "androidx.recyclerview:recyclerview:1.2.0-alpha05"
    const val annotations = "androidx.annotation:annotation:1.1.0"
    const val material = "com.google.android.material:material:1.3.0-alpha02"
    const val cardViewX = "androidx.cardview:cardview:1.0.0"
}

object Libs {
    const val gson = "com.google.code.gson:gson:2.8.6"
    const val kotlinpref = "com.chibatching.kotpref:kotpref:2.11.0"
    const val otto = "com.squareup:otto:1.3.8"
    const val reactiveNetwork = "com.github.pwittchen:reactivenetwork-rx2:3.0.8"
    const val butterKnife = "com.jakewharton:butterknife:8.8.1"
    const val butterKnifeCompiler = "com.jakewharton:butterknife-compiler:8.8.1"
    const val googleLocation = "com.google.android.gms:play-services-location:16.0.0"

    // *** Toasty (leave this at 1.3.0 as the newer versions took away functionality)
    const val toast = "com.github.GrenderG:Toasty:1.4.2"
    const val groupie = "com.xwray:groupie:2.7.0"
    const val groupieKtx = "com.xwray:groupie-kotlin-android-extensions:2.7.0"
    const val lottie = "com.airbnb.android:lottie:3.0.7"
    const val zxing = "com.google.zxing:core:3.2.1"
    const val boommenu = "com.nightonke:boommenu:2.1.1"
    const val lovelydialog = "com.yarolegovich:lovely-dialog:1.1.1"
    const val bottomnav = "it.sephiroth.android.library.bottomnavigation:bottom-navigation:3.0.0"
    const val bubbleShowCase = "com.elconfidencial.bubbleshowcase:bubbleshowcase:1.3.1"
    const val flashbar = "com.andrognito.flashbar:flashbar:1.0.3"
    const val bubbleNavigation = "com.gauravk.bubblenavigation:bubblenavigation:1.0.7"
    const val shimmer = "com.facebook.shimmer:shimmer:0.5.0"
    const val bounceWithSound = "com.cleveroad:audiovisualization:1.0.1"
    const val animations1 = "com.daimajia.easing:library:2.1@aar"
    const val animations2 = "com.daimajia.androidanimations:library:2.3@aar"
    const val gif = "pl.droidsonroids.gif:android-gif-drawable:1.2.20"
    const val timberLogger = "com.jakewharton.timber:timber:4.7.1"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:2.4"
    const val rxBindingMaterial = "com.jakewharton.rxbinding3:rxbinding-material:3.1.0"
    const val rxView = "com.jakewharton.rxbinding2:rxbinding:2.1.1"
    const val rxViewV7 = "com.jakewharton.rxbinding2:rxbinding-appcompat-v7:2.1.1"
    const val rxViewDesign = "com.jakewharton.rxbinding2:rxbinding-design:2.1.1"
}

object RxJava {
    const val rxJava2 = "io.reactivex.rxjava2:rxjava:2.2.19"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.1"
}

object Permissions {
    const val rx = "com.github.tbruyelle:rxpermissions:2.x.v0.9.3"
}

object Imaging {
    const val glide = "com.github.bumptech.glide:glide:4.9.0"
    const val glideCompiler = "com.github.bumptech.glide:compiler:4.9.0"
    const val fresco = "com.facebook.fresco:fresco:2.0.0"
    const val frescoWebP = "com.facebook.fresco:webpsupport:2.0.0"
    const val frescoAnimatedWebP = "com.facebook.fresco:animated-webp:2.0.0"
    const val frescoAnimatedBase = "com.facebook.fresco:animated-base:2.0.0"
}

object Navigation {
    const val navigationFragments = "androidx.navigation:navigation-fragment-ktx:2.3.0"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:2.3.0"
}

object Dagger {
    const val dagger = "com.google.dagger:dagger:2.28.3"
    const val compiler = "com.google.dagger:dagger-compiler:2.28.3"
    const val android = "com.google.dagger:dagger-android:2.28.3"
    const val androidSupport = "com.google.dagger:dagger-android-support:2.28.3"
    const val processor = "com.google.dagger:dagger-android-processor:2.28.3"
}

object Room {
    const val database = "android.arch.persistence.room:runtime:2.1.0"
    const val kotlinExtensions = "androidx.room:room-ktx:2.1.0"
    const val rxSupport = "androidx.room:room-rxjava2:2.1.0"
    const val compiler = "android.arch.persistence.room:compiler:2.1.0"
}

object Firebase {
    const val analytics = "com.google.firebase:firebase-analytics-ktx:17.5.0"
    const val crashlytics = "com.google.firebase:firebase-crashlytics:17.2.1"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:2.9.0"
    const val rxJava = "com.squareup.retrofit2:adapter-rxjava2:2.9.0"
    const val okHttp = "com.squareup.okhttp3:okhttp:4.8.1"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.8.1"
}

object Ktx {
    const val core = "androidx.core:core-ktx:1.5.0-alpha01"
    const val fragment = "androidx.fragment:fragment-ktx:1.2.5"
    const val preference = "androidx.preference:preference-ktx:1.1.0"
    const val palette = "androidx.palette:palette-ktx:1.0.0"
    const val sqlite = "androidx.sqlite:sqlite-ktx:1.0.0"
    const val collections = "androidx.collection:collection-ktx:1.1.0"
    const val reactiveStreams = "androidx.lifecycle:lifecycle-reactivestreams-ktx:2.2.0"
    const val workManager = "android.arch.work:work-runtime-ktx:1.0.1"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0"
    const val savedState = "androidx.savedstate:savedstate-ktx:1.1.0-alpha01"
}

object TestingLib {
    const val jUnit = "androidx.test.ext:junit:1.1.2-rc03"
    const val jUnitKotlin = "org.jetbrains.kotlin:kotlin-test-junit:1.4.0"
    const val testCoreX = "androidx.test:core:1.3.0-rc03"
    const val testArchCompX = "androidx.arch.core:core-testing:2.1.0"
    const val robolectric = "org.robolectric:robolectric:4.3"
    const val mockitoCore = "org.mockito:mockito-core:3.4.6"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0"
    const val mockitoInline = "org.mockito:mockito-inline:3.4.6"
    const val runner = "androidx.test:runner:1.3.0-rc03"
    const val espresso = "androidx.test.espresso:espresso-core:3.3.0-rc03"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:3.1.0"
    const val espressoIdleConcurrent = "androidx.test.espresso.idling:idling-concurrent:3.1.0"
    const val espressoIdleResources = "androidx.test.espresso:espresso-idling-resource:3.1.0"
    const val espressoIntents = "androidx.test.espresso:espresso-intents:3.3.0-rc03"
    const val rules = "androidx.test:rules:1.3.0-rc03"
    const val truth = "androidx.test.ext:truth:1.3.0-rc03"
    const val kluent = "org.amshove.kluent:kluent:1.61"
    const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.9"

    // use debugImplementation for fragment scenario!
    const val fragmentScenario = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.9"
    const val kakaoUiTesting = "com.agoda.kakao:kakao:2.0.0"
}
