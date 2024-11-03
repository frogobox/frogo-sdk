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

object DependencyGradle {

    const val FROGO_PATH_CORE_SDK = ":${ProjectSetting.MODULE_NAME_CORE_SDK}"
    const val FROGO_PATH_SDK = ":${ProjectSetting.MODULE_NAME_SDK}"
    const val FROGO_PATH_SDK_UTIL = ":${ProjectSetting.MODULE_NAME_SDK_UTIL}"

    // dependencies version
    const val COMPOSE_MULTIPLATFORM_VERSION = "1.6.11" // https://github.com/JetBrains/compose-jb
    const val COMPOSE_VERSION = "1.5.14" // https://developer.android.com/jetpack/androidx/releases/compose

    const val FROGO_PATH_CORE_UI = ":${ProjectSetting.MODULE_NAME_UI}"
    const val FROGO_PATH_UI = ":${ProjectSetting.MODULE_NAME_CORE_UI}"

    const val FROGO_PATH_CORE_UTIL = ":${ProjectSetting.MODULE_NAME_CORE_UTIL}"
    const val FROGO_PATH_CORE_API = ":${ProjectSetting.MODULE_NAME_CORE_API}"
    const val FROGO_PATH_CORE_ANDROID_API = ":${ProjectSetting.MODULE_NAME_API}"

}