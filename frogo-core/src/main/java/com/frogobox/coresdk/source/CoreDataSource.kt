package com.frogobox.coresdk.source

import kotlinx.coroutines.Job
import java.util.concurrent.ConcurrentHashMap

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

    private val jobs = ConcurrentHashMap.newKeySet<Job>()

    fun onClearDisposables() {
        val jobsToCancel = ArrayList(jobs)
        jobs.clear()
        jobsToCancel.forEach { it.cancel() }
    }

    fun addSubscribe(job: Job) {
        jobs.removeIf { !it.isActive }
        jobs.add(job)
        job.invokeOnCompletion {
            jobs.remove(job)
        }
    }
}