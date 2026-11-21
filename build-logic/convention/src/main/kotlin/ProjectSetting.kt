/**
 * Created by faisalamir on 19/09/21
 * Frogo SDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * GitHub   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.
 * All rights reserved
 *
 */

object ProjectSetting {

    const val NAME_APP = "Frogo SDK"

    const val APP_DOMAIN = "com"
    const val APP_PLAY_CONSOLE = "frogobox"

    // Module Names (Directory & Gradle Include)
    const val MODULE_NAME_CORE_SDK = "frogo-core"
    const val MODULE_NAME_SDK = "frogo-android"
    const val MODULE_NAME_COMPOSE = "frogo-compose"

    // Library Names (For Package ID / Namespace)
    const val LIBRARY_NAME_CORE_SDK = "coresdk"
    const val LIBRARY_NAME_SDK = "sdk"
    const val LIBRARY_NAME_COMPOSE = "compose"

    // ---------------------------------------------------------------------------------------------

    const val VERSION_MAJOR = 4
    const val VERSION_MINOR = 0
    const val VERSION_PATCH = 1

    // ---------------------------------------------------------------------------------------------

    const val PROJECT_MIN_SDK = 24
    const val PROJECT_COMPILE_SDK = 37
    const val PROJECT_COMPILE_SDK_MINOR = 1
    const val PROJECT_TARGET_SDK = PROJECT_COMPILE_SDK

    // ---------------------------------------------------------------------------------------------

    const val URI_PACKAGE_LIB = "https://maven.pkg.github.com/amirisback/frogo-sdk"

    const val BASE_PACKAGE_NAME = "$APP_DOMAIN.$APP_PLAY_CONSOLE"

    const val PROJECT_NAME_SPACE = "$APP_DOMAIN.$APP_PLAY_CONSOLE"

    const val PROJECT_APP_ID = "$BASE_PACKAGE_NAME.app"
    const val PROJECT_LIB_ID_SDK = "$BASE_PACKAGE_NAME.$LIBRARY_NAME_SDK"
    const val PROJECT_LIB_ID_CORE_SDK = "$BASE_PACKAGE_NAME.$LIBRARY_NAME_CORE_SDK"
    const val PROJECT_LIB_ID_COMPOSE = "$BASE_PACKAGE_NAME.$LIBRARY_NAME_COMPOSE"

    const val PROJECT_VERSION_CODE = (VERSION_MAJOR * 10000) + (VERSION_MINOR * 100) + VERSION_PATCH
    const val PROJECT_VERSION_NAME = "$VERSION_MAJOR.$VERSION_MINOR.$VERSION_PATCH"

    const val NAME_APK = "frogo-sdk"

    const val NAME_DB = "frogo_sdk"
    const val DB = "\"$NAME_DB.db\""

    // ---------------------------------------------------------------------------------------------

    val PLAYSTORE_STORE_FILE: String = System.getenv("FROGO_KEYSTORE_FILE") ?: "frogoboxmedia.jks"
    val PLAYSTORE_STORE_PASSWORD: String = System.getenv("FROGO_STORE_PASSWORD") ?: "amirisback"
    val PLAYSTORE_KEY_ALIAS: String = System.getenv("FROGO_KEY_ALIAS") ?: "frogoisback"
    val PLAYSTORE_KEY_PASSWORD: String = System.getenv("FROGO_KEY_PASSWORD") ?: "amirisback"

}
