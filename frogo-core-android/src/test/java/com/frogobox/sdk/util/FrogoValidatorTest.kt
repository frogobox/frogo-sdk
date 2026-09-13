package com.frogobox.sdk.util

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.system.measureTimeMillis

@RunWith(RobolectricTestRunner::class)
class FrogoValidatorTest {

    // =============================================================================================
    // UNIT TESTS
    // =============================================================================================

    @Test
    fun testIsValidText() {
        assertTrue(FrogoValidator.isValidText("Hello", 3))
        assertTrue(FrogoValidator.isValidText("Hi", 2))
        assertFalse(FrogoValidator.isValidText("Hi", 3))
    }

    @Test
    fun testIsValidEmail() {
        assertTrue(FrogoValidator.isValidEmail("test@example.com"))
        assertTrue(FrogoValidator.isValidEmail("user.name+tag@domain.co.id"))
        assertFalse(FrogoValidator.isValidEmail("invalid-email"))
        assertFalse(FrogoValidator.isValidEmail("invalid@"))
        assertFalse(FrogoValidator.isValidEmail("@domain.com"))
    }

    @Test
    fun testIsValidPassword() {
        assertTrue(FrogoValidator.isValidPassword("12345678"))
        assertTrue(FrogoValidator.isValidPassword("strongpassword123"))
        assertFalse(FrogoValidator.isValidPassword("short"))
    }

    @Test
    fun testIsValidPasswordConfirm() {
        assertTrue(FrogoValidator.isValidPasswordConfirm("secret123", "secret123"))
        assertFalse(FrogoValidator.isValidPasswordConfirm("secret123", "different"))
    }

    @Test
    fun testContainsSymbolOrDigit() {
        assertTrue(FrogoValidator.containsSymbolOrDigit("text1"))
        assertTrue(FrogoValidator.containsSymbolOrDigit("text@"))
        assertTrue(FrogoValidator.containsSymbolOrDigit("123"))
        assertFalse(FrogoValidator.containsSymbolOrDigit("onlyletters"))
    }

    // =============================================================================================
    // PERFORMANCE TESTS
    // =============================================================================================

    @Test
    fun testValidationPerformance() {
        val emailList = List(10000) { index -> "user$index@domain.com" }
        
        val timeTaken = measureTimeMillis {
            for (email in emailList) {
                FrogoValidator.isValidEmail(email)
            }
        }
        
        println("Performance test: validated 10,000 email addresses in $timeTaken ms")
        // Ensuring it takes a reasonable amount of time (typically < 500ms on local test environment)
        assertTrue("Validation took too long: $timeTaken ms", timeTaken < 2000)
    }

    // =============================================================================================
    // USABILITY / API ERGONOMICS TESTS
    // =============================================================================================

    @Test
    fun testUsabilityContainsSymbolOrDigit() {
        val testString = "LettersAndDigits123"
        val actual = FrogoValidator.containsSymbolOrDigit(testString)
        assertTrue(actual)
    }

    @Test
    fun testUsabilityWithEmptyAndExtremes() {
        // Validation should gracefully handle empty strings without crashing
        assertFalse(FrogoValidator.isValidEmail(""))
        assertFalse(FrogoValidator.isValidText(""))
        assertFalse(FrogoValidator.isValidPassword(""))
        
        // Validation of an extremely long string should not crash/cause StackOverflow
        val veryLongString = "a".repeat(100000)
        assertFalse(FrogoValidator.isValidEmail(veryLongString))
        assertTrue(FrogoValidator.isValidText(veryLongString))
    }
}
