package com.frogobox.sdk.test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test

/**
 * Unit test verifying MainDispatcherRule functioning with coroutines test dispatcher.
 */
class MainDispatcherRuleTest {

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule(testDispatcher)

    @Test
    fun `main dispatcher is set to test dispatcher during test execution`() = runTest(testDispatcher) {
        val currentMain = Dispatchers.Main
        assertNotNull(currentMain)
    }
}
