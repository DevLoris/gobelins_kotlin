package dev.pinna.dmii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.pinna.dmii.extensions.toDouble
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val nbr1 = nombre1.toDouble()?:return@setOnClickListener
            val nbr2 = nombre2.toDouble()?:return@setOnClickListener

            result.text = getString(R.string.result, (nbr1 + nbr2).virgul2numbers())
        }
    }
}

private fun Double.virgul2numbers() = String.format("%.2f", this)
