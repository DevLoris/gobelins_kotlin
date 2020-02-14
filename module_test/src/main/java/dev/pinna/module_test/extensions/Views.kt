package dev.pinna.module_test.extensions

import android.widget.EditText
import dev.pinna.module_test.R

fun EditText.toInt() : Int? {
    return text.toString().toIntOrNull() ?: run {
        error = context.resources.getString(R.string.number_error)
        null
    }
}

fun EditText.toDouble() : Double? {
    return text.toString().toDoubleOrNull() ?: run {
        error = context.resources.getString(R.string.number_error)
        null
    }
}