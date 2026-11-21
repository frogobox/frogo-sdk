package com.frogobox.sdk.piracychecker

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.frogobox.sdk.piracychecker.utils.buildUnlicensedDialog

class PiracyCheckerDialog : DialogFragment() {

    fun show(context: Context) {
        (context as? AppCompatActivity)?.let {
            show(it.supportFragmentManager, TAG)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        isCancelable = false
        val dialogTitle = arguments?.getString(ARG_TITLE).orEmpty()
        val dialogContent = arguments?.getString(ARG_CONTENT).orEmpty()
        val act = activity ?: requireContext()
        return act.buildUnlicensedDialog(dialogTitle, dialogContent) ?: super.onCreateDialog(savedInstanceState)
    }

    companion object {
        private const val TAG = "[LICENSE_DIALOG]"
        private const val ARG_TITLE = "arg_dialog_title"
        private const val ARG_CONTENT = "arg_dialog_content"

        fun newInstance(dialogTitle: String, dialogContent: String): PiracyCheckerDialog {
            return PiracyCheckerDialog().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, dialogTitle)
                    putString(ARG_CONTENT, dialogContent)
                }
            }
        }
    }
}