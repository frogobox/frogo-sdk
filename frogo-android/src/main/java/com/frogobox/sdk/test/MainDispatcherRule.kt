package com.frogobox.sdk.test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * Standard JUnit [TestWatcher] rule that overrides [Dispatchers.Main] with a [TestDispatcher].
 *
 * Essential for unit testing ViewModels, Coroutines, and UDF/MVI StateFlow flows in isolation.
 *
 * Example usage:
 * ```kotlin
 * class MyViewModelTest {
 *     @get:Rule
 *     val mainDispatcherRule = MainDispatcherRule()
 *
 *     @Test
 *     fun testState() = runTest {
 *         // Test code here with virtual coroutine time
 *     }
 * }
 * ```
 *
 * Created by Muhammad Faisal Amir
 * github.com/amirisback
 */
@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    val testDispatcher: TestDispatcher = StandardTestDispatcher()
) : TestWatcher() {

    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}
