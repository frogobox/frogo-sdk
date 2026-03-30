/**
 * Created by faisalamir on 19/09/21
 * FrogoRecyclerView
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.
 * All rights reserved
 *
 */

object ProjectSetting {

    const val NAME_APP = "Frogo SDK"

    const val APP_DOMAIN = "com"
    const val APP_PLAY_CONSOLE = "frogobox"

    const val LIBRARY_NAME_CORE_SDK = "coresdk"
    const val LIBRARY_NAME_SDK = "sdk"
    const val LIBRARY_NAME_SDK_UTIL = "sdkutil"

    const val MODULE_NAME_CORE_SDK = "core-sdk"
    const val MODULE_NAME_SDK = "core-sdk-android"
    const val MODULE_NAME_SDK_UTIL = "core-sdk-android-util"

    const val LIBRARY_NAME_UI = "ui"
    const val MODULE_NAME_CORE_UI = "core-ui-android"

    const val LIBRARY_NAME_AD = "ads"
    const val MODULE_NAME_AD = "core-ads"

    // ---------------------------------------------------------------------------------------------

    const val VERSION_MAJOR = 2
    const val VERSION_MINOR = 3
    const val VERSION_PATCH = 6

    // ---------------------------------------------------------------------------------------------

    const val PROJECT_MIN_SDK = 23
    const val PROJECT_COMPILE_SDK = 36
    const val PROJECT_TARGET_SDK = PROJECT_COMPILE_SDK

    // ---------------------------------------------------------------------------------------------

    val URI_PACKAGE_LIB = "https://maven.pkg.github.com/amirisback/frogo-admob"

    const val BASE_PACAKGE_NAME = "$APP_DOMAIN.$APP_PLAY_CONSOLE"

    const val PROJECT_NAME_SPACE = "$APP_DOMAIN.$APP_PLAY_CONSOLE"

    const val PROJECT_APP_ID = "$BASE_PACAKGE_NAME.app"
    const val PROJECT_LIB_ID_SDK = "$BASE_PACAKGE_NAME.$LIBRARY_NAME_SDK"
    const val PROJECT_LIB_ID_CORE_SDK = "$BASE_PACAKGE_NAME.$LIBRARY_NAME_CORE_SDK"
    const val PROJECT_LIB_ID_SDK_UTIL = "$BASE_PACAKGE_NAME.$LIBRARY_NAME_SDK_UTIL"

    const val PROJECT_LIB_ID_UI = "$BASE_PACAKGE_NAME.$LIBRARY_NAME_UI"

    const val PROJECT_LIB_ID_AD = "$BASE_PACAKGE_NAME.$LIBRARY_NAME_AD"

    const val PROJECT_VERSION_CODE = (VERSION_MAJOR * 100) + (VERSION_MINOR * 10) + (VERSION_PATCH * 1)
    const val PROJECT_VERSION_NAME = "$VERSION_MAJOR.$VERSION_MINOR.$VERSION_PATCH"

    val NAME_APK = NAME_APP.lowercase().replace(" ", "-")

    val NAME_DB = NAME_APP.lowercase().replace(" ", "_")
    val DB = "\"$NAME_DB.db\""

    // ---------------------------------------------------------------------------------------------

    const val PLAYSTORE_STORE_FILE = "frogoboxmedia.jks"
    const val PLAYSTORE_STORE_PASSWORD = "amirisback"
    const val PLAYSTORE_KEY_ALIAS = "frogoisback"
    const val PLAYSTORE_KEY_PASSWORD = "amirisback"

}