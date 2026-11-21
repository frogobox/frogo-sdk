package com.frogobox.composeui.theme

import androidx.compose.ui.graphics.Color
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class FrogoThemeTest {

    // =============================================================================================
    // UNIT TESTS
    // =============================================================================================

    @Test
    fun testMaterial3DefaultPaletteColors() {
        assertEquals(Color(0xFFD0BCFF), Purple80)
        assertEquals(Color(0xFFCCC2DC), PurpleGrey80)
        assertEquals(Color(0xFFEFB8C8), Pink80)

        assertEquals(Color(0xFF6650a4), Purple40)
        assertEquals(Color(0xFF625b71), PurpleGrey40)
        assertEquals(Color(0xFF7D5260), Pink40)
    }

    @Test
    fun testFrogoGlobalColors() {
        assertEquals(Color(0xFF6200EE), FrogoColorPrimary)
        assertEquals(Color(0xFF3700B3), FrogoColorPrimaryDark)
        assertEquals(Color(0xFF03DAC5), FrogoColorAccent)
        
        assertEquals(Color(0xFFFFFFFF), FrogoColorWhite)
        assertEquals(Color(0xFF000000), FrogoColorBlack)
        assertEquals(Color(0xFFF44336), FrogoColorRed)
        assertEquals(Color(0xFFE91E63), FrogoColorPink)
    }

    // =============================================================================================
    // USABILITY TESTS
    // =============================================================================================

    @Test
    fun testThemeUsabilityColorPaletteDistinction() {
        // Assert that core color palette variables are distinct and defined
        assertNotNull(FrogoColorPrimary)
        assertNotNull(FrogoColorPrimaryDark)
        assertNotNull(FrogoColorAccent)

        // Primary color must be different from background colors (White/Black) for readability
        assertNotEquals(FrogoColorPrimary, FrogoColorWhite)
        assertNotEquals(FrogoColorPrimary, FrogoColorBlack)
    }

    @Test
    fun testFrogoColorSchemes() {
        assertEquals(FrogoColorPrimary, FrogoLightColorScheme.primary)
        assertEquals(FrogoColorAccent, FrogoDarkColorScheme.primary)
        assertNotNull(FrogoLightColorScheme.background)
        assertNotNull(FrogoDarkColorScheme.background)
    }

    @Test
    fun testTypographyDefinition() {
        assertNotNull(Typography.bodyLarge)
        assertNotNull(Typography.titleLarge)
        assertNotNull(Typography.headlineMedium)
        assertNotNull(Typography.labelSmall)
    }
}
