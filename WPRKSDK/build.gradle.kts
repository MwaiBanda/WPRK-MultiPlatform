plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
}

version = "1.0"

kotlin {
    android()
    iosX64()
   iosArm64()
    iosSimulatorArm64() //sure all ios dependencies support this target

    cocoapods {
        summary = "WPRK SDK allows seamlessly integration with WPRK auth, shows & podcast functionality"
        homepage = "https://wprk.org"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "WPRKSDK"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("dev.gitlive:firebase-auth:1.4.3")
                implementation("io.insert-koin:koin-core:3.1.5")
                implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
            }
            }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
           iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }
    namespace = "com.mwaibanda.wprksdk"
}