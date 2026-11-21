package com.frogobox.sdk.log


/**
 * Created by faisalamir on 29/03/22
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * GitHub   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */

object LogConstant {

    // Constant Variable Simple Message
    const val SIMPLE_MESSAGE = "SIMPLE MESSAGE FROM FROGO-LOG FOR MARK LINE OF CODE"

    @Deprecated(
        message = "Typo in constant name, use SIMPLE_MESSAGE instead",
        replaceWith = ReplaceWith("SIMPLE_MESSAGE")
    )
    const val SIMPLE_MESSSAGE = SIMPLE_MESSAGE

}