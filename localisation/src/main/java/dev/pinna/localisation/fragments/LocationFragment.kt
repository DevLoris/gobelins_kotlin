package dev.pinna.localisation.fragments

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.LocationManager
import android.location.LocationProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import android.location.Location
import android.opengl.Visibility
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnCompleteListener
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.android.gms.location.LocationRequest
import kotlinx.android.synthetic.main.location_fragment.*
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationCallback
import dev.pinna.localisation.R

class LocationFragment : Fragment() {
    private lateinit var mFusedLocationClient: FusedLocationProviderClient

    companion object {
        const val PERMISSION_CODE = 1
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v =  inflater.inflate(R.layout.location_fragment, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = activity ?: return
        val context = context ?: return


        if (ContextCompat.checkSelfPermission( context,  Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,   Manifest.permission.ACCESS_FINE_LOCATION)) {
                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_CODE)
            } else {
                val builder = AlertDialog.Builder(activity)
                builder
                    .setTitle("Permission obligatoire")
                    .setMessage(" Nous avons besoin de la permission afin de déterminer votre position GPS")
                    .setPositiveButton("Ok") {_, _ ->
                        requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_CODE)
                    }
                    .setNegativeButton("Cancel" ) { _, _ ->  }
                builder.create().show()

            }
        } else {
            getLastLocation()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    getLastLocation()
                } else {
                    error()
                }
            }
            else -> { }
        }
    }

    private fun getLastLocation() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity!!)

        mFusedLocationClient.lastLocation.addOnCanceledListener {
            Log.i("MESSS","Cancelled")
        }
        mFusedLocationClient.lastLocation.addOnCompleteListener {
            Log.i("MESSS","OK")
            it.let {
                val location = it.result
                if (location == null) {
                    requestNewLocationData()
                } else
                    location_label.text = (location.latitude.toString() + ", " + location.longitude.toString())
            }
        }
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val location = locationResult.lastLocation
            location_label.text = (location.latitude.toString() + ", " + location.longitude.toString())
        }
    }

    private fun error() {
        error.visibility = View.VISIBLE
    }

    private fun requestNewLocationData() {

        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this.activity!!)
        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )

    }
}