package com.frogobox.appsdk.notification.custom

import android.content.Intent
import android.os.Bundle
import com.frogobox.appsdk.core.BaseActivity
import com.frogobox.appsdk.databinding.ActivityCustomNotifBinding

class CustomNotifActivity : BaseActivity<ActivityCustomNotifBinding>() {

    override fun setupViewBinding(): ActivityCustomNotifBinding {
        return ActivityCustomNotifBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {}

    override fun onCreateExt(savedInstanceState: Bundle?) {
        super.onCreateExt(savedInstanceState)
        setupDetailActivity("Custom Notif")
        binding.buttonShowNotification.setOnClickListener {
            startService(Intent(this, NotificationService::class.java))
        }
    }

}