package com.frogobox.sdk.delegate.piracy

import com.frogobox.sdk.delegate.piracy.util.PiracyMessage

/*
 * Created by faisalamir on 01/07/22
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

interface FrogoPiracyCallback {

    fun doOnPirated(message: PiracyMessage)

}