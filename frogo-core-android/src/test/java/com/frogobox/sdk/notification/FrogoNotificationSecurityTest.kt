package com.frogobox.sdk.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class FrogoNotificationSecurityTest {

    @Test
    fun testNotificationBuildAndLaunch_safeExecution() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        val notifInject = FrogoNotification.Inject(context)
            .setChannelId("test_channel")
            .setChannelName("Test Channel")
            .setContentTitle("Test Title")
            .setContentText("Test Content")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .build()

        assertNotNull(notifInject)
        // Must launch safely without unhandled crashes
        notifInject.launch(101)
    }
}
