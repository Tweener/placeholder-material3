plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
    namespace = Dependencies.Versions.PlaceholderMaterial3.namespace
    compileSdk = Dependencies.Versions.PlaceholderMaterial3.compileSDK

    defaultConfig {
        minSdk = Dependencies.Versions.PlaceholderMaterial3.minSDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        getByName("debug") {
        }
    }

    buildFeatures {
        compose = true
        buildConfig = false
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Versions.composeCompilerExtension
    }

    compileOptions {
        sourceCompatibility = Dependencies.Versions.Compiler.javaCompatibility
        targetCompatibility = Dependencies.Versions.Compiler.javaCompatibility

        isCoreLibraryDesugaringEnabled = true
    }

    kotlinOptions {
        jvmTarget = Dependencies.Versions.Compiler.jvmTarget
    }
}

dependencies {

    coreLibraryDesugaring(Dependencies.Libraries.desugarJdkLibs)

    // Android
    implementation(Dependencies.Libraries.AndroidX.material3)
    implementation(Dependencies.Libraries.AndroidX.Compose.ui)
    implementation(Dependencies.Libraries.AndroidX.Compose.uiUtil)
    implementation(Dependencies.Libraries.AndroidX.Compose.uiTooling)
    implementation(Dependencies.Libraries.AndroidX.Compose.uiToolingPreview)
    implementation(Dependencies.Libraries.AndroidX.Compose.foundation)

    implementation(Dependencies.Libraries.Coroutines.core)
    implementation(Dependencies.Libraries.Coroutines.android)

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

afterEvaluate {
    publishing {
        publications {
            register("release", MavenPublication::class) {
                group = Dependencies.Versions.PlaceholderMaterial3.Jitpack.group
                artifactId = Dependencies.Versions.PlaceholderMaterial3.Jitpack.artifactId
                version = Dependencies.Versions.PlaceholderMaterial3.versionName

                from(components["release"])
            }
        }
    }
}
