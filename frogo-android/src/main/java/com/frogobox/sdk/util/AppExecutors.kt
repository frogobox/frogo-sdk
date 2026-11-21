package com.frogobox.sdk.util

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by faisalamir on 26/07/21
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * GitHub   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */

/**
 * Utility for background/main thread execution using Java Executors.
 *
 * Consider migrating to Kotlin Coroutines with `Dispatchers.IO`, `Dispatchers.Default`,
 * and `Dispatchers.Main` for modern, non-blocking asynchronous execution.
 */
class AppExecutors constructor(
    val diskIO: Executor = DiskIOThreadExecutor(),
    val networkIO: Executor = Executors.newFixedThreadPool(3),
    val mainThread: Executor = MainThreadExecutor()
) {

    companion object {
        val TAG: String = AppExecutors::class.java.simpleName
    }

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}