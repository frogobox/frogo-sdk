package com.frogobox.coresdk.source

import com.google.gson.annotations.SerializedName

/*
 * Created by faisalamir on 26/07/21
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */
data class FrogoApiModel<T>(

    @SerializedName("code")
    var code: Int = 0,

    @SerializedName("message")
    var message: String = "",

    @SerializedName("status")
    var status: String = "",

    @SerializedName("data")
    var data: T? = null

)