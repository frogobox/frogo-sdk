package com.frogobox.sdk.util

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.frogobox.sdk.R

/**
 * Created by faisalamircs on 26/04/2024
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * GitHub   : github.com/amirisback
 * -----------------------------------------
 */

object SimpleDialogUtil {

    interface OnDialogClickListener {
        fun positiveButton()
        fun negativeButton()
    }

    fun create(
        context: Context,
        title: String,
        message: String,
        listener: OnDialogClickListener
    ): AlertDialog {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton(context.getText(R.string.dialog_button_yes)) { _, _ ->
                listener.positiveButton()
            }
            .setNegativeButton(context.getText(R.string.dialog_button_no)) { dialog, _ ->
                dialog.cancel()
                listener.negativeButton()
            }
        val alert = dialogBuilder.create()
        alert.setTitle(title)
        alert.show()
        return alert
    }

    fun create(
        context: Context,
        title: String,
        message: String,
        onPositive: () -> Unit = {},
        onNegative: () -> Unit = {}
    ): AlertDialog {
        return create(context, title, message, object : OnDialogClickListener {
            override fun positiveButton() = onPositive()
            override fun negativeButton() = onNegative()
        })
    }

}