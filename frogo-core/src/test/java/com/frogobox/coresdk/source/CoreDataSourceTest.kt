package com.frogobox.coresdk.source

import kotlinx.coroutines.Job
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class CoreDataSourceTest {

    class TestDataSource : CoreDataSource()

    @Test
    fun testAddAndClearJobs() {
        val dataSource = TestDataSource()
        val job = Job()

        dataSource.addSubscribe(job)
        dataSource.onClearDisposables()

        assertTrue(job.isCancelled)
    }

    @Test
    fun testStressConcurrentSubscriptions() {
        val dataSource = TestDataSource()
        val numThreads = 20
        val subscriptionsPerThread = 50
        val executor = Executors.newFixedThreadPool(numThreads)
        val jobs = mutableListOf<Job>()

        for (i in 0 until numThreads) {
            executor.submit {
                for (j in 0 until subscriptionsPerThread) {
                    val job = Job()
                    synchronized(jobs) {
                        jobs.add(job)
                    }
                    dataSource.addSubscribe(job)
                }
            }
        }

        executor.shutdown()
        executor.awaitTermination(5, TimeUnit.SECONDS)

        dataSource.onClearDisposables()
        assertEquals(numThreads * subscriptionsPerThread, jobs.size)
        assertTrue(jobs.all { it.isCancelled })
    }
}
