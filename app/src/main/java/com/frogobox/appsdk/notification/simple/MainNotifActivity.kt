package com.frogobox.appsdk.notification.simple

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.frogobox.R
import com.frogobox.appsdk.core.BaseActivity
import com.frogobox.appsdk.notification.custom.CustomNotifActivity
import com.frogobox.appsdk.notification.stack.StackNotifActivity
import com.frogobox.databinding.ActivityMainNotifBinding
import com.frogobox.sdk.notification.FrogoNotifCustomContentViewListener
import com.frogobox.sdk.notification.FrogoNotification


class MainNotifActivity : BaseActivity<ActivityMainNotifBinding>() {

    companion object {
        private const val NOTIFICATION_ID = 1
        private const val CHANNEL_ID = "CHANNEL_$NOTIFICATION_ID"
        private const val CHANNEL_NAME = "CHANNEL_NAME_$CHANNEL_ID"
    }

    override fun setupViewBinding(): ActivityMainNotifBinding {
        return ActivityMainNotifBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {}

    override fun onCreateExt(savedInstanceState: Bundle?) {
        super.onCreateExt(savedInstanceState)
        setupDetailActivity("FrogoNotification")
        binding.apply {

            btnSendNotif.setOnClickListener {
                sendNotification()
            }

            btnCustomNotif.setOnClickListener {
                intentToCustom()
            }

            btnStackNotif.setOnClickListener {
                intentToStack()
            }

            btnShowExpanded.setOnClickListener {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    sendNotificationCustom()
                }
            }

        }
    }

    private fun sendNotification() {

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/amirisback"))
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        FrogoNotification.Inject(this) // Intialize for Context
            .setChannelId(CHANNEL_ID) // Intialize for Channel ID
            .setChannelName(CHANNEL_NAME) // Initialize for Channel Name
            .setContentIntent(pendingIntent) // Initialize for Content Intent
            .setSmallIcon(R.drawable.ic_frogo_notif) // Initialize for Small Icon
            .setLargeIcon(R.drawable.ic_frogo_notif) // Initialize for Large Icon
            .setContentTitle(resources.getString(R.string.content_title)) // Initialize for Content Title
            .setContentText(resources.getString(R.string.content_text)) // Initialize for Content Text
            .setSubText(resources.getString(R.string.subtext)) // Initialize for Sub Text
            .setupAutoCancel() // Initialize for Auto Cancel
            .setCallCategory()
            .setPriorityHigh()
            .build() // Build the Frogo Notification
            .launch(NOTIFICATION_ID) // Notify the Frogo Notification

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun sendNotificationCustom() {
        val clickIntent = Intent(this, MainNotifReceiver::class.java)
        val clickPendingIntent =
            PendingIntent.getBroadcast(this, 0, clickIntent, PendingIntent.FLAG_IMMUTABLE)

        val collapsed = object : FrogoNotifCustomContentViewListener {
            override fun setupCustomView(): Int {
                return R.layout.notification_collapsed
            }

            override fun setupComponent(context: Context, customView: RemoteViews) {
                customView.apply {
                    setTextViewText(R.id.text_view_collapsed_1, "Hello World!")
                }
            }
        }

        val expanded = object : FrogoNotifCustomContentViewListener {
            override fun setupCustomView(): Int {
                return R.layout.notification_expanded
            }

            override fun setupComponent(context: Context, customView: RemoteViews) {
                customView.apply {
                    setImageViewResource(R.id.image_view_expanded, R.drawable.ic_android)
                    setOnClickPendingIntent(R.id.image_view_expanded, clickPendingIntent)
                }
            }
        }

        FrogoNotification.Inject(this) // Intialize for Context
            .setChannelId(CHANNEL_ID) // Intialize for Channel ID
            .setChannelName(CHANNEL_NAME) // Initialize for Channel Name
            .setSmallIcon(R.drawable.ic_android) // Initialize for Small Icon
            .setCustomContentView(collapsed)
            .setCustomBigContentView(expanded)
            .build() // Build the Frogo Notification
            .launch(NOTIFICATION_ID) // Notify the Frogo Notification

    }

    private fun intentToCustom() {
        startActivity(Intent(this, CustomNotifActivity::class.java))
    }

    private fun intentToStack() {
        startActivity(Intent(this, StackNotifActivity::class.java))
    }

    private fun showNotification() {

    }

    private fun createNotificationChannel(channelId: String, channelName: String): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val chan =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            chan.lightColor = Color.BLUE
            chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            service.createNotificationChannel(chan)
        }
        return channelId
    }

    private fun createNotificationCall() {
        // Create a notification channel for Android O and above
        val channelId = createNotificationChannel("my_service", "My Background Service")

        // Build the notification for foreground service
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
        val notification = notificationBuilder.setOngoing(true)
            .setSmallIcon(R.drawable.ic_android)
            .setContentTitle("Incoming Call")
            .setContentText("Tap to answer")
            // Priority settings for heads-up notification
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_CALL)
            .build()
    }

}