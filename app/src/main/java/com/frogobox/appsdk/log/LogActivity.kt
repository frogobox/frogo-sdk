package com.frogobox.appsdk.log

import android.os.Bundle
import com.frogobox.appsdk.core.BaseActivity
import com.frogobox.databinding.ActivityLogBinding
import com.frogobox.sdk.log.FLog
import com.frogobox.sdk.log.FrogoLog

class LogActivity : BaseActivity<ActivityLogBinding>() {

    override fun setupViewBinding(): ActivityLogBinding {
        return ActivityLogBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
    }

    override fun onCreateExt(savedInstanceState: Bundle?) {
        super.onCreateExt(savedInstanceState)
        setupDetailActivity("Frogo Log")
        binding.apply {

            btnFlog.setOnClickListener {
                // Regular No Toast
                FLog.d("Debug")
                FLog.i("Info")
                FLog.v("Verbose")
                FLog.w("Warn")
                FLog.e("Error")

                FLog.d()
                FLog.i()
                FLog.v()
                FLog.w()
                FLog.e()
            }

            btnFlogToast.setOnClickListener {
                // Regular with Toast
                FLog.d("Debug", this@LogActivity)
                FLog.i("Info", this@LogActivity)
                FLog.v("Verbose", this@LogActivity)
                FLog.w("Warn", this@LogActivity)
                FLog.e("Error", this@LogActivity)

                FLog.d(this@LogActivity)
                FLog.i(this@LogActivity)
                FLog.v(this@LogActivity)
                FLog.w(this@LogActivity)
                FLog.e(this@LogActivity)
            }

            btnFrogolog.setOnClickListener {
                // Line Number and No Toast
                FrogoLog.d("Debug")
                FrogoLog.i("Info")
                FrogoLog.v("Verbose")
                FrogoLog.w("Warn")
                FrogoLog.e("Error")

                FrogoLog.d()
                FrogoLog.i()
                FrogoLog.v()
                FrogoLog.w()
                FrogoLog.e()
            }

            btnFrogologToast.setOnClickListener {
                // Line Numbar with Toast
                FrogoLog.d("Debug", this@LogActivity)
                FrogoLog.i("Info", this@LogActivity)
                FrogoLog.v("Verbose", this@LogActivity)
                FrogoLog.w("Warn", this@LogActivity)
                FrogoLog.e("Error", this@LogActivity)

                FrogoLog.d(this@LogActivity)
                FrogoLog.i(this@LogActivity)
                FrogoLog.v(this@LogActivity)
                FrogoLog.w(this@LogActivity)
                FrogoLog.e(this@LogActivity)
            }

        }
    }
}