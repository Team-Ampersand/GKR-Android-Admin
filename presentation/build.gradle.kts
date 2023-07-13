import java.io.FileInputStream
import java.util.Properties

plugins {
    id(ProjectProperties.Gradle.LIBRARY)
    id(ProjectProperties.Gradle.KOTLIN)
    id(ProjectProperties.Gradle.HILT_PLUGIN)
    kotlin(ProjectProperties.Gradle.KAPT)
}

android {
    namespace = ProjectProperties.NameSpace.PRESENTATION
    compileSdk = ProjectProperties.Versions.COMPILE_SDK

    defaultConfig {
        minSdk = ProjectProperties.Versions.MIN_SDK

        testInstrumentationRunner = ProjectProperties.Test.TEST_RUNNER
        consumerProguardFiles(ProjectProperties.Files.CONSUMER_PROGUARD)

        buildConfigField(
            "String",
            "CLIENT_ID",
            getApiKey("CLIENT_ID")
        )

        buildConfigField(
            "String",
            "REDIRECT_URI",
            getApiKey("REDIRECT_URI")
        )
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }
}

dependencies {

    implementation(project(":domain"))

    implementation(Dependencies.Libraries.RETROFIT_CONVERTER_GSON)

    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.APPCOMPAT)
    implementation(Dependencies.AndroidX.NAVIGATION_COMPOSE)
    implementation(Dependencies.AndroidX.HILT_NAVIGATION_COMPOSE)
    implementation(Dependencies.Google.MATERIAL)
    implementation(Dependencies.Google.HILT)
    implementation(Dependencies.Compose.COMPOSE)
    implementation(Dependencies.Compose.COMPOSE_TOOLING)
    implementation(Dependencies.Compose.COMPOSE_MATERIAL)
    implementation(Dependencies.Compose.COMPOSE_PREVIEW)
    implementation(Dependencies.Compose.LIVEDATA_COMPOSE)
    implementation(Dependencies.Msg.GAUTH)
    implementation(Dependencies.Libraries.COIL)
    kapt(Dependencies.Google.HILT_COMPILER)
    testImplementation(Dependencies.Test.JUNIT)
    androidTestImplementation(Dependencies.Test.ANDROID_JUNIT)
    androidTestImplementation(Dependencies.Test.ESPRESSO)
}

fun getApiKey(propertyKey: String): String {
    val propFile = rootProject.file("./local.properties")
    val properties = Properties()
    properties.load(FileInputStream(propFile))
    return properties.getProperty(propertyKey)
}
