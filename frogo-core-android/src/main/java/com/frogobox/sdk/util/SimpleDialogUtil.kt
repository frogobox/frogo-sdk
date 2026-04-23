package com.frogobox.sdk.util

import android.app.AlertDialog
import android.content.Context
import com.frogobox.sdk.R

/**
 * Created by faisalamircs on 26/04/2024
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
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
    ) {
        val dialogBuilder = AlertDialog.Builder(context)
        // set message of alert dialog
        dialogBuilder.setMessage(message)
            // if the dialog is cancelable
            .setCancelable(false)
            .setPositiveButton(context.getText(R.string.dialog_button_yes)) { dialog, id ->
                // positive button text and action
                listener.positiveButton()
            }
            .setNegativeButton(context.getText(R.string.dialog_button_no)) { dialog, id ->
                // negative button text and action
                dialog.cancel()
                listener.negativeButton()
            }
        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle(title)
        // show alert dialog
        alert.show()
    }

}