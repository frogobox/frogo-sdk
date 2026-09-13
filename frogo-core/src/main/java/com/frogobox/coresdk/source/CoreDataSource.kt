package com.frogobox.coresdk.source

import kotlinx.coroutines.Job
import java.util.Collections

/**
 * Created by faisalamir on 08/04/22
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

abstract class CoreDataSource {

    companion object {
        val TAG: String = CoreDataSource::class.java.simpleName
    }

    private val jobs = Collections.synchronizedList(mutableListOf<Job>())

    fun onClearDisposables() {
        synchronized(jobs) {
            jobs.forEach { it.cancel() }
            jobs.clear()
        }
    }

    fun addSubscribe(job: Job) {
        jobs.add(job)
    }
}