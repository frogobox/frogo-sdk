package com.frogobox.appsdk.log

import android.os.Bundle
import com.frogobox.appsdk.core.BaseActivity
import com.frogobox.appsdk.databinding.ActivityLogBinding
import com.frogobox.sdk.FLog
import com.frogobox.sdk.FrogoLog

class LogActivity : BaseActivity<ActivityLogBinding>() {

    override fun setupViewBinding(): ActivityLogBinding {
        return ActivityLogBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
    }

    override fun setupOnCreate(savedInstanceState: Bundle?) {
        binding.apply {

            btnFlog.setOnClickListener {
                // Regular No Toast
                FLog.d("Debug")
                FLog.i("Info")
                FLog.v("Verbose")
                FLog.w("Warn")
                FLog.e("Error")
                FLog.d()
            }

            btnFlogToast.setOnClickListener {
                // Regular with Toast
                FLog.d("Debug", this@LogActivity)
                FLog.i("Info", this@LogActivity)
                FLog.v("Verbose", this@LogActivity)
                FLog.w("Warn", this@LogActivity)
                FLog.e("Error", this@LogActivity)
                FLog.d()
            }

            btnFrogolog.setOnClickListener {
                // Line Number and No Toast
                FrogoLog.d("Debug")
                FrogoLog.i("Info")
                FrogoLog.v("Verbose")
                FrogoLog.w("Warn")
                FrogoLog.e("Error")
                FrogoLog.d()
            }

            btnFrogologToast.setOnClickListener {
                // Line Numbar with Toast
                FrogoLog.d("Debug", this@LogActivity)
                FrogoLog.i("Info", this@LogActivity)
                FrogoLog.v("Verbose", this@LogActivity)
                FrogoLog.w("Warn", this@LogActivity)
                FrogoLog.e("Error", this@LogActivity)
                FrogoLog.d(this@LogActivity)
            }

        }
    }
}