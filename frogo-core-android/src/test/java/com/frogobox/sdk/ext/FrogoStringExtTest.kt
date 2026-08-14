package com.frogobox.sdk.ext

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.TimeZone

class FrogoStringExtTest {

    @Test
    fun testIsDigitOnly() {
        assertTrue("123456".isDigitOnly)
        assertFalse("123a456".isDigitOnly)
        assertFalse("".isDigitOnly)
        assertFalse("   ".isDigitOnly)
    }

    @Test
    fun testIsAlphabeticOnly() {
        assertTrue("HelloWorld".isAlphabeticOnly)
        assertFalse("Hello World".isAlphabeticOnly)
        assertFalse("Hello123".isAlphabeticOnly)
        assertFalse("".isAlphabeticOnly)
    }

    @Test
    fun testIsAlphanumericOnly() {
        assertTrue("HelloWorld123".isAlphanumericOnly)
        assertFalse("Hello World 123".isAlphanumericOnly)
        assertFalse("Hello@123".isAlphanumericOnly)
        assertFalse("".isAlphanumericOnly)
    }

    @Test
    fun testIsValidPassword() {
        val (valid, error) = "StrongP@ss1".isValidPassword()
        assertTrue(valid)
        assertNull(error)

        val (tooShort, errorShort) = "P@s1".isValidPassword()
        assertFalse(tooShort)
        assertEquals("The password must be at least 8 characters.", errorShort)

        val (noLower, errorNoLower) = "STRONGP@SS1".isValidPassword()
        assertFalse(noLower)
        assertEquals("The password must contain at least one lowercase letter.", errorNoLower)

        val (noUpper, errorNoUpper) = "strongp@ss1".isValidPassword()
        assertFalse(noUpper)
        assertEquals("The password must contain at least one uppercase letter", errorNoUpper)

        val (noDigit, errorNoDigit) = "StrongP@ssword".isValidPassword()
        assertFalse(noDigit)
        assertEquals("The password must contain at least one number.", errorNoDigit)

        val (noSpecial, errorNoSpecial) = "StrongPass1".isValidPassword()
        assertFalse(noSpecial)
        assertEquals("The password must contain at least one special character.", errorNoSpecial)
    }

    @Test
    fun testRemoveLastChar() {
        assertEquals("Hell", "Hello".removeLastChar())
        assertEquals("", "H".removeLastChar())
        assertEquals("", "".removeLastChar())
    }

    @Test
    fun testToDate() {
        val dateString = "2026-08-14T12:00:00.000Z"
        val date = dateString.toDate(timeZone = TimeZone.getTimeZone("UTC"))
        assertEquals(1786708800000L, date.time)
    }

    @Test
    fun testGetUserMention() {
        val message = "Hello @frogo let's build SDK"
        assertEquals("frogo", message.getUserMention("frogo"))
        assertNull(message.getUserMention("unknown"))
    }
}
