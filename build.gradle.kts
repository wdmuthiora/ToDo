buildscript {
    dependencies {
        classpath (libs.kotlin.gradle.plugin)
        classpath (libs.hilt.android.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
}

