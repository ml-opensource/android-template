object Libs {

    object Plugins {
        val gradle = "com.android.tools.build:gradle:7.4.1"
        val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Libs.Versions.kotlin}"
        val nstack = "dk.nodes.nstack:translation:${Libs.Versions.nstackGradlePlugin}"
        val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Libs.Versions.hilt}"
    }

    object Versions {
        val kotlin = "1.8.10"
        val appcompat = "1.6.1"
        val coroutines = "1.6.4"
        val json = "1.4.1"
        val constraintLayout = "2.1.4"
        val hilt = "2.45"
        val lifecycle = "2.5.1"
        val timber = "5.0.1"
        val junit = "4.13.2"
        val mockk = "1.13.4"
        val junitExt = "1.1.5"
        val espresso = "3.5.1"
        val nstack = "3.2.5"
        val retrofit = "2.9.0"
        val retrofitConverter = "0.8.0"
        val okhttp = "4.10.0"
        val ktxCore = "1.9.0"
        val material = "1.8.0"
        val navigation = "2.5.3"
        val chucker = "3.5.2"
        val leakCanary = "2.10"
        val desugaring = "1.0.9"
        val nstackGradlePlugin = "3.2.5"
        val datastore = "1.0.0"
        val accompanist = "0.28.0"
    }

    object Kotlin {
        val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${Versions.coroutines}"
        val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.json}"
    }

    object Android {

        val fragment = "androidx.fragment:fragment-ktx:1.5.5"
        val core = "androidx.core:core-ktx:${Versions.ktxCore}"
        val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        val material = "com.google.android.material:material:${Versions.material}"
        val contraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        val datastore = "androidx.datastore:datastore-preferences:${Versions.datastore}"
        val activityCompose = "androidx.activity:activity-compose:1.5.1"

        object Navigation {
            val fragNavigation =
                "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
            val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        }

        object Lifecycle {
            val core = "androidx.lifecycle:lifecycle-livedata-core-ktx:${Versions.lifecycle}"
            val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
            val common = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
            val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
            val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
            val runtimeCompose = "androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha03"
            val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1"
        }

    }

    object Injection {
        val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
        val hiltKapt = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    }

    object Networking {
        val retrofitSerializer =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.retrofitConverter}"
        val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    }

    object Compose {
        val bom = "androidx.compose:compose-bom:2023.01.00"
        val material = "androidx.compose.material:material"
        val preview = "androidx.compose.ui:ui-tooling-preview"
        val tooling = "androidx.compose.ui:ui-tooling"
        val icons = "androidx.compose.material:material-icons-extended"
        val windowSize = "androidx.compose.material3:material3-window-size-class"

        // Third party
        val coil = "io.coil-kt:coil-compose:2.2.2"
        val accompanistSystemUi =
            "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"
        val accompanistPlaceholder =
            "com.google.accompanist:accompanist-placeholder-material:${Versions.accompanist}"
        val accompanifestPager = "com.google.accompanist:accompanist-pager:${Versions.accompanist}"
    }

    object Test {
        val junit = "junit:junit:${Versions.junit}"
        val junitAndroid = "androidx.test.ext:junit:${Versions.junitExt}"
        val androidEspresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
        val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
        val mockkAgent = "io.mockk:mockk-agent:${Versions.mockk}"
        val coroutinesTest="org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    val nstack = "dk.nodes.nstack:nstack-kotlin:${Versions.nstack}"
    val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    val chuckerNoOp = "com.github.chuckerteam.chucker:library-no-op:${Versions.chucker}"
    val chuckerDebug = "com.github.chuckerteam.chucker:library:${Versions.chucker}"

}