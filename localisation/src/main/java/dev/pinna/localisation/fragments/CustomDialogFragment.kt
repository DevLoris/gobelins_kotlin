package dev.pinna.localisation.fragments

import android.Manifest
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment

class CustomDialogFragment : DialogFragment() {

    lateinit var callback:DialogInterface.OnClickListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.i("MESSS", "Okk")
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder
                .setTitle("Permission obligatoire")
                .setMessage(" Nous avons besoin de la permission afin de déterminer votre position GPS")
                .setPositiveButton("Ok",
                    callback)
                .setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}