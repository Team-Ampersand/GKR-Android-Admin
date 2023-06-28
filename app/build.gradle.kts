plugins {
    id(ProjectProperties.Gradle.APPLICATION)
    id(ProjectProperties.Gradle.KOTLIN)
}

android {
    namespace = ProjectProperties.NameSpace.APP
    compileSdk = ProjectProperties.Versions.COMPILE_SDK

    defaultConfig {
        applicationId = ProjectProperties.NameSpace.APP
        minSdk = ProjectProperties.Versions.MIN_SDK
        targetSdk = ProjectProperties.Versions.TARGET_SDK
        versionCode = ProjectProperties.Versions.VERSION_CODE
        versionName = ProjectProperties.Versions.VERSION_NAME

        testInstrumentationRunner = ProjectProperties.Test.TEST_RUNNER
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile(ProjectProperties.Files.DEFAULT_PROGUARD), ProjectProperties.Files.PROGUARD)
        }
    }
    compileOptions {
        sourceCompatibility = ProjectProperties.Versions.JAVA_VERSION
        targetCompatibility = ProjectProperties.Versions.JAVA_VERSION
    }
    kotlinOptions {
        jvmTarget = ProjectProperties.Versions.JVM_TARGET
    }
}

dependencies {

    implementation(project(":presentation"))
    implementation(project(":di"))

    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.APPCOMPAT)
    implementation(Dependencies.Google.MATERIAL)
    testImplementation(Dependencies.Test.JUNIT)
    androidTestImplementation(Dependencies.Test.ANDROID_JUNIT)
    androidTestImplementation(Dependencies.Test.ESPRESSO)
}