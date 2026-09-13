package com.frogobox.compose.view

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.yield
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis


class FrogoComposeViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // Concrete implementation of FrogoComposeViewModel for testing
    private class TestViewModel : FrogoComposeViewModel() {
        var onStartCalled = false
        var onClearDisposableCalled = false

        override fun onStart() {
            super.onStart()
            onStartCalled = true
        }

        override fun onClearDisposable() {
            super.onClearDisposable()
            onClearDisposableCalled = true
        }

        // Expose protected onCleared() for unit testing
        fun triggerOnCleared() {
            onCleared()
        }
    }

    // =============================================================================================
    // UNIT TESTS
    // =============================================================================================

    @Test
    fun testViewModelLifecycleCallbacks() {
        val viewModel = TestViewModel()
        
        // Assert initial state
        assertTrue(!viewModel.onStartCalled)
        assertTrue(!viewModel.onClearDisposableCalled)

        // Invoke start
        viewModel.onStart()
        assertTrue(viewModel.onStartCalled)

        // Trigger cleared (which should trigger onClearDisposable)
        viewModel.triggerOnCleared()
        assertTrue(viewModel.onClearDisposableCalled)
    }

    // =============================================================================================
    // PERFORMANCE TESTS
    // =============================================================================================

    @Test
    fun testViewModelCreationPerformance() {
        val count = 10000
        val timeTaken = measureTimeMillis {
            for (i in 0 until count) {
                val vm = TestViewModel()
                vm.onStart()
            }
        }
        println("Performance: Created and started $count ViewModels in $timeTaken ms")
        assertTrue("Creation of $count ViewModels took too long: $timeTaken ms", timeTaken < 2000)
    }

    // =============================================================================================
    // STRESS TESTS
    // =============================================================================================

    @Test
    fun testConcurrentViewModelLifecycleStress() {
        val viewModel = TestViewModel()
        val numThreads = 10
        val opsPerThread = 500
        val executor = Executors.newFixedThreadPool(numThreads)
        val startOpsCount = AtomicInteger(0)
        val clearOpsCount = AtomicInteger(0)

        for (i in 0 until numThreads) {
            executor.submit {
                for (j in 0 until opsPerThread) {
                    viewModel.onStart()
                    startOpsCount.incrementAndGet()
                    viewModel.triggerOnCleared()
                    clearOpsCount.incrementAndGet()
                }
            }
        }

        executor.shutdown()
        executor.awaitTermination(5, TimeUnit.SECONDS)

        assertEquals(numThreads * opsPerThread, startOpsCount.get())
        assertEquals(numThreads * opsPerThread, clearOpsCount.get())
        assertTrue(viewModel.onStartCalled)
        assertTrue(viewModel.onClearDisposableCalled)
    }

    // =============================================================================================
    // STATE VIEW MODEL TESTS
    // =============================================================================================

    data class TestState(val data: String = "initial")
    sealed interface TestEffect {
        object Navigate : TestEffect
    }

    private class TestStateViewModel : FrogoComposeStateViewModel<TestState, TestEffect>(TestState()) {
        fun updateData(newData: String) {
            updateState { copy(data = newData) }
        }

        fun triggerEffect() {
            emitEffect(TestEffect.Navigate)
        }
    }

    @Test
    fun testStateViewModelFlows() = runBlocking {
        val viewModel = TestStateViewModel()

        // Assert initial state
        assertEquals("initial", viewModel.uiState.value.data)

        // Update state and assert
        viewModel.updateData("updated")
        assertEquals("updated", viewModel.uiState.value.data)

        // Capture effects in a list
        val effects = mutableListOf<TestEffect>()
        val job = launch(Dispatchers.Main) {
            viewModel.uiEffect.collect { effects.add(it) }
        }

        // Yield to allow subscription to register
        yield()

        // Trigger effect and assert emission
        viewModel.triggerEffect()
        assertEquals(1, effects.size)
        assertEquals(TestEffect.Navigate, effects[0])

        // Clean up job
        job.cancel()
    }

    data class CounterState(val count: Int = 0)

    private class CounterViewModel : FrogoComposeStateViewModel<CounterState, TestEffect>(CounterState()) {
        fun increment() {
            updateState { copy(count = count + 1) }
        }
    }

    @Test
    fun testConcurrentAtomicStateUpdates() = runBlocking {
        val viewModel = CounterViewModel()
        val numCoroutines = 50
        val incrementsPerCoroutine = 100
        val jobs = (0 until numCoroutines).map {
            launch(Dispatchers.Default) {
                for (i in 0 until incrementsPerCoroutine) {
                    viewModel.increment()
                }
            }
        }
        jobs.forEach { it.join() }
        assertEquals(numCoroutines * incrementsPerCoroutine, viewModel.uiState.value.count)
    }
}

