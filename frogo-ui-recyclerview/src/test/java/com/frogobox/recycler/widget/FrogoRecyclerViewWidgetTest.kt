package com.frogobox.recycler.widget

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)

class FrogoRecyclerViewWidgetTest {

    private val context: Context get() = ApplicationProvider.getApplicationContext()

    // =============================================================================================
    // UI / INSTRUMENT TESTS
    // =============================================================================================

    @Test
    fun testInitialization() {
        val recyclerView = FrogoRecyclerView(context)
        assertNotNull(recyclerView)
    }

    @Test
    fun testDeprecatedIsViewLinearVertical() {
        val recyclerView = FrogoRecyclerView(context)
        recyclerView.isViewLinearVertical(dividerItem = true)
        
        val layoutManager = recyclerView.layoutManager
        assertTrue(layoutManager is LinearLayoutManager)
        assertEquals(LinearLayoutManager.VERTICAL, (layoutManager as LinearLayoutManager).orientation)
        // Verify item decoration is added
        assertTrue(recyclerView.itemDecorationCount > 0)
    }

    @Test
    fun testDeprecatedIsViewLinearHorizontal() {
        val recyclerView = FrogoRecyclerView(context)
        recyclerView.isViewLinearHorizontal(dividerItem = false)
        
        val layoutManager = recyclerView.layoutManager
        assertTrue(layoutManager is LinearLayoutManager)
        assertEquals(LinearLayoutManager.HORIZONTAL, (layoutManager as LinearLayoutManager).orientation)
        assertEquals(0, recyclerView.itemDecorationCount)
    }

    @Test
    fun testDeprecatedIsViewGrid() {
        val recyclerView = FrogoRecyclerView(context)
        recyclerView.isViewGrid(spanCount = 3)
        
        val layoutManager = recyclerView.layoutManager
        assertTrue(layoutManager is GridLayoutManager)
        assertEquals(3, (layoutManager as GridLayoutManager).spanCount)
    }

    @Test
    fun testDeprecatedIsViewStaggeredGrid() {
        val recyclerView = FrogoRecyclerView(context)
        recyclerView.isViewStaggeredGrid(spanCount = 2)
        
        val layoutManager = recyclerView.layoutManager
        assertTrue(layoutManager is StaggeredGridLayoutManager)
        assertEquals(2, (layoutManager as StaggeredGridLayoutManager).spanCount)
        assertEquals(StaggeredGridLayoutManager.VERTICAL, (layoutManager as StaggeredGridLayoutManager).orientation)
    }

    @Test
    fun testInjectorCreation() {
        val recyclerView = FrogoRecyclerView(context)
        val singleRv = recyclerView.injector<String>()
        assertNotNull(singleRv)
    }

    @Test
    fun testInjectorBindingCreation() {
        val recyclerView = FrogoRecyclerView(context)
        val singleRvBinding = recyclerView.injectorBinding<String, androidx.viewbinding.ViewBinding>()
        assertNotNull(singleRvBinding)
    }

    // =============================================================================================
    // SECURITY TESTS
    // =============================================================================================

    @Test
    fun testGetAdapterExtThrowsExceptionWhenNullOrWrongType() {
        val recyclerView = FrogoRecyclerView(context)
        
        // At start, adapter is null. Calling getAdapterExt should throw NullPointerException or ClassCastException
        try {
            recyclerView.getAdapterExt<String>()
            fail("Should throw NullPointerException or ClassCastException since adapter is null")
        } catch (e: NullPointerException) {
            // OK, expected behavior since no adapter is set
        } catch (e: ClassCastException) {
            // OK, expected behavior
        }
    }
}
