/**
 * Created by faisalamir on 19/09/21
 * Frogo SDK
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
    const val FROGO_PATH_COMPOSE = ":${ProjectSetting.MODULE_NAME_COMPOSE}"
    const val FROGO_PATH_COMPOSE_UI = ":${ProjectSetting.MODULE_NAME_COMPOSE_UI}"
    const val FROGO_PATH_UI = ":${ProjectSetting.MODULE_NAME_CORE_UI}"
    const val FROGO_PATH_RECYCLER_VIEW = ":${ProjectSetting.MODULE_NAME_UI_RECYCLER}"
    const val FROGO_PATH_EXT_ADS = ":${ProjectSetting.MODULE_NAME_AD}"

    @Deprecated("Use FROGO_PATH_EXT_ADS instead", ReplaceWith("FROGO_PATH_EXT_ADS"))
    const val MODULE_LIB_FROGO_AD = FROGO_PATH_EXT_ADS

}