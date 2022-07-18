package com.frogobox.appsdk.notification.simple

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import com.frogobox.appsdk.FrogoApp
import com.frogobox.appsdk.R
import com.frogobox.appsdk.core.BaseActivity
import com.frogobox.appsdk.databinding.ActivityMainNotifBinding
import com.frogobox.appsdk.notification.custom.CustomNotifActivity
import com.frogobox.appsdk.notification.stack.StackNotifActivity
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

    override fun setupViewModel() {
    }

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
            .setChannelId(FrogoApp.CHANNEL_ID) // Intialize for Channel ID
            .setChannelName(FrogoApp.CHANNEL_NAME) // Initialize for Channel Name
            .setSmallIcon(R.drawable.ic_android) // Initialize for Small Icon
            .setCustomContentView(collapsed)
            .setCustomBigContentView(expanded)
            .build() // Build the Frogo Notification
            .launch(FrogoApp.NOTIFICATION_ID) // Notify the Frogo Notification

    }

    private fun intentToCustom() {
        startActivity(Intent(this, CustomNotifActivity::class.java))
    }

    private fun intentToStack() {
        startActivity(Intent(this, StackNotifActivity::class.java))
    }

}