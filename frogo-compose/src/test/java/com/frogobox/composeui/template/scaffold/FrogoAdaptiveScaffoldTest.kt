package com.frogobox.composeui.template.scaffold

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Unit test for adaptive window width size classifications.
 */
class FrogoAdaptiveScaffoldTest {

    @Test
    fun `verify window width classification enum values`() {
        assertEquals(3, FrogoWindowWidthSizeClass.entries.size)
        assertEquals("Compact", FrogoWindowWidthSizeClass.Compact.name)
        assertEquals("Medium", FrogoWindowWidthSizeClass.Medium.name)
        assertEquals("Expanded", FrogoWindowWidthSizeClass.Expanded.name)
    }
}
