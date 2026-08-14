package com.frogobox.sdk.view

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

data class TestState(val count: Int = 0)
sealed interface TestEffect {
    data class ShowToast(val message: String) : TestEffect
}

class SampleStateViewModel : FrogoStateViewModel<TestState, TestEffect>(TestState()) {
    fun increment() {
        updateState { copy(count = count + 1) }
    }

    fun triggerEffect(message: String) {
        emitEffect(TestEffect.ShowToast(message))
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class FrogoStateViewModelTest {

    @Test
    fun testInitialState() {
        val viewModel = SampleStateViewModel()
        assertEquals(0, viewModel.uiState.value.count)
    }

    @Test
    fun testAtomicStateUpdate() {
        val viewModel = SampleStateViewModel()
        viewModel.increment()
        assertEquals(1, viewModel.uiState.value.count)
    }

    @Test
    fun testConcurrentStateUpdates() = runTest {
        val viewModel = SampleStateViewModel()
        val totalUpdates = 100

        val deferreds = (1..totalUpdates).map {
            async(Dispatchers.Default) {
                viewModel.increment()
            }
        }
        deferreds.awaitAll()

        assertEquals(totalUpdates, viewModel.uiState.value.count)
    }
}
