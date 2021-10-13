package com.example.younes_rcc010

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ExitDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder
            .setTitle("Exit?")
            .setMessage("Do you want to exit ?")
            .setPositiveButton("ok", DialogInterface.OnClickListener { dialog, i ->
                activity?.finish()
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, i ->
                dialog.dismiss()
            } )
        return builder.create()
    }
}