package com.frogobox.sdk.delegate.util


/*
 * Created by faisalamir on 02/07/22
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

interface DateDelegates {

    fun getCurrentTimeStamp(): String

    fun getCurrentTime(): String

    fun getCurrentDate(format: String): String

    fun getCurrentUTC(): String

    fun convertDateTimeToTimeStamp(date: String?): Long

    fun convertClassificationDate(string: String?): String

    fun convertDateNewFormat(string: String?): String

    fun convertLongDateNewFormat(string: String?): String

    fun convertTargetDate(string: String?): String

    fun revertFromLongDateNewFormat(string: String?): String

    fun diffTime(timeStart: String, timeEnd: String): Long

}