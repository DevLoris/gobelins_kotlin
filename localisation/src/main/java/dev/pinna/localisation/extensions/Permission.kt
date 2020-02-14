package dev.pinna.localisation.extensions

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

public fun ContextCompat.hasPermission(context : Context, permission: String) : Boolean {
    return  (ContextCompat.checkSelfPermission(context,  permission ) != PackageManager.PERMISSION_GRANTED)
}
public fun ContextCompat.shouldShow(activity : Activity, permission: String) : Boolean {
    return  (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission))
}