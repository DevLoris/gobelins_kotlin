package dev.pinna.localisation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dev.pinna.localisation.fragments.LocationFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("MESSS", "Coucou")

        val fragment = LocationFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.location, fragment)
            addToBackStack(null)
        }.commit()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
