package dev.pinna.module_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.pinna.module_test.fragments.ChoiceFragment
import dev.pinna.module_test.fragments.ComputationFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = ChoiceFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.buttons_fragment, fragment)
            addToBackStack(null)
        }.commit()

    }
}
