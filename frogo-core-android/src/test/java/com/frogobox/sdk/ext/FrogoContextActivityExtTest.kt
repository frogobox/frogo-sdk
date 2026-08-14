package com.frogobox.sdk.ext

import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.Serializable

data class TestSerializable(val id: Int, val name: String) : Serializable

data class TestParcelable(val id: Int, val description: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(description)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<TestParcelable> {
        override fun createFromParcel(parcel: Parcel): TestParcelable = TestParcelable(parcel)
        override fun newArray(size: Int): Array<TestParcelable?> = arrayOfNulls(size)
    }
}

@RunWith(RobolectricTestRunner::class)
class FrogoContextActivityExtTest {

    @Test
    fun testGetExtraExt_primitives() {
        val intent = Intent().apply {
            putExtra("key_str", "hello")
            putExtra("key_int", 42)
            putExtra("key_bool", true)
            putExtra("key_double", 3.14)
            putExtra("key_float", 2.5f)
            putExtra("key_long", 1000L)
        }

        assertEquals("hello", intent.getExtraExt<String>("key_str"))
        assertEquals(42, intent.getExtraExt<Int>("key_int"))
        assertEquals(true, intent.getExtraExt<Boolean>("key_bool"))
        assertEquals(3.14, intent.getExtraExt<Double>("key_double") ?: 0.0, 0.001)
        assertEquals(2.5f, intent.getExtraExt<Float>("key_float") ?: 0.0f, 0.001f)
        assertEquals(1000L, intent.getExtraExt<Long>("key_long"))
        assertNull(intent.getExtraExt<String>("missing_key"))
    }

    @Test
    fun testGetExtraExt_serializable() {
        val original = TestSerializable(1, "Frogo")
        val intent = Intent().apply {
            putExtra("key_serializable", original)
        }

        val result = intent.getExtraExt<TestSerializable>("key_serializable")
        assertEquals(original, result)
    }

    @Test
    fun testGetExtraExt_parcelable() {
        val original = TestParcelable(99, "Testing Parcelable")
        val intent = Intent().apply {
            putExtra("key_parcelable", original)
        }

        val result = intent.getExtraExt<TestParcelable>("key_parcelable")
        assertEquals(original, result)
    }
}
