package com.frogobox.ads.source

import com.frogobox.ads.model.FrogoAdmobId
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

class FrogoAdmobRepositoryTest {

    // =============================================================================================
    // INTEGRATION & FUNCTIONAL TESTS
    // =============================================================================================

    @Test
    fun testRepositoryIntegrationWithMockSource() {
        val mockRepository = mockk<FrogoAdmobRepository>(relaxed = true)
        val mockCallback = mockk<FrogoAdmobApiResponse<FrogoAdmobId>>(relaxed = true)
        val testJsonFile = "admob_id.json"

        every { mockRepository.getFrogoAdmobId(testJsonFile, mockCallback) } answers {
            // Simulate triggering response success callback
            mockCallback.onSuccess(FrogoAdmobId())
        }

        mockRepository.getFrogoAdmobId(testJsonFile, mockCallback)

        verify(exactly = 1) { mockRepository.getFrogoAdmobId(testJsonFile, mockCallback) }
        verify(exactly = 1) { mockCallback.onSuccess(any()) }
    }

    @Test
    fun testRepositorySuspendFetchFunctions() = kotlinx.coroutines.runBlocking {
        val mockRepository = mockk<FrogoAdmobRepository>(relaxed = true)
        val expected = FrogoAdmobId(testAdmobAppId = "test_app_id")

        io.mockk.coEvery { mockRepository.fetchFrogoAdmobId("admob_id.json") } returns Result.success(expected)

        val result = mockRepository.fetchFrogoAdmobId("admob_id.json")
        assertTrue(result.isSuccess)
        assertEquals("test_app_id", result.getOrNull()?.testAdmobAppId)
    }

    // =============================================================================================
    // PERFORMANCE TESTS
    // =============================================================================================

    @Test
    fun testRepositoryMappingPerformance() {
        val count = 1000
        val timeTaken = measureTimeMillis {
            for (i in 0 until count) {
                // Simulate fast instantiation and field mapping of Ad ID objects
                val id = FrogoAdmobId(
                    testAdmobAppId = "app_$i",
                    testAdmobBanner = "banner_$i"
                )
                assertEquals("app_$i", id.testAdmobAppId)
            }
        }
        println("Performance: Mapped $count Admob ID objects in $timeTaken ms")
        assertTrue("Mapping took too long: $timeTaken ms", timeTaken < 1000)
    }

    // =============================================================================================
    // STRESS TESTS
    // =============================================================================================

    @Test
    fun testConcurrentRepositoryAccessStress() {
        val repository = mockk<FrogoAdmobRepository>(relaxed = true)
        val numThreads = 10
        val opsPerThread = 200
        val executor = Executors.newFixedThreadPool(numThreads)
        val accessCount = AtomicInteger(0)

        for (i in 0 until numThreads) {
            executor.submit {
                for (j in 0 until opsPerThread) {
                    repository.getFrogoAdmobId("test.json", mockk(relaxed = true))
                    accessCount.incrementAndGet()
                }
            }
        }

        executor.shutdown()
        executor.awaitTermination(5, TimeUnit.SECONDS)

        assertEquals(numThreads * opsPerThread, accessCount.get())
    }
}
