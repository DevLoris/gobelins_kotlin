package dev.pinna.module_test.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import dev.pinna.module_test.R
import dev.pinna.module_test.models.Operation
import kotlinx.android.synthetic.main.choice_fragment.*

class ChoiceFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v =  inflater.inflate(R.layout.choice_fragment, container, false)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_addition.setOnClickListener (addButtonOperation(Operation.SUM))
        button_soustraction.setOnClickListener (addButtonOperation(Operation.SUBSTRACT))
        button_multiplication.setOnClickListener (addButtonOperation(Operation.MULTIPLY))
        button_division.setOnClickListener (addButtonOperation(Operation.DIVISION))
    }

    fun addButtonOperation(operation:Operation) : View.OnClickListener {
        return View.OnClickListener {
            if ( activity != null ) {
                val fragmentManager = activity!!.supportFragmentManager
                var fragment = fragmentManager.findFragmentById(R.id.computation)
                if (fragment is ComputationFragment) {
                    fragment.operation = operation
                }
                else {
                    fragmentManager.beginTransaction().apply {
                        replace(R.id.computation, ComputationFragment.newInstance(operation))
                        addToBackStack(null)
                    }.commit()
                }
            }
        }
    }
}