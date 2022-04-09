package com.frogobox.appsdk.util


/*
 * Created by faisalamir on 08/04/22
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */

object AppConstant {

    object RoomDatabase {
        const val BASE_TABLE_NAME = "table"
        const val TABLE_NAME_DATA = "news_apps_frogo_$BASE_TABLE_NAME"
        const val TABLE_NAME_FAVORITE = "favorite_$TABLE_NAME_DATA"

        const val ATTR_TABLE_ID = "table_id"
        const val ATTR_ID = "id"
        const val ATTR_TITLE = "title"
        const val ATTR_SPEED = "speed"
        const val ATTR_BEST = "best"
        const val ATTR_STARS = "stars"
        const val ATTR_FAVE = "fave"
    }

}