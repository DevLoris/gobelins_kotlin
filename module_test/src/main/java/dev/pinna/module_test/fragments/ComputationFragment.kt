package dev.pinna.module_test.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dev.pinna.module_test.R
import dev.pinna.module_test.extensions.toDouble
import dev.pinna.module_test.extensions.format2numbers
import dev.pinna.module_test.models.Calculator
import dev.pinna.module_test.models.Operation
import dev.pinna.module_test.viewmodels.ComputeViewModel
import kotlinx.android.synthetic.main.computation_fragment.*

class ComputationFragment : Fragment() {
    var operation: Operation =  arguments?.getParcelable(ARGS_OPERATION) ?: Operation.SUM

    private lateinit var computeModel : ComputeViewModel

    companion object
    {
        const val ARGS_OPERATION = "ARGS_OPERATION"
        fun newInstance(operation: Operation): ComputationFragment {
            return ComputationFragment().apply{
                arguments = bundleOf(ARGS_OPERATION to operation)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        computeModel = ViewModelProvider(this).get(ComputeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.computation_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        listOf(nombre1, nombre2).forEach {
            it.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}

                override fun beforeTextChanged( s: CharSequence?,  start: Int,  count: Int,  after: Int  ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val nbr1: Double = nombre1.toDouble() ?: 0.0
                    val nbr2: Double = nombre2.toDouble() ?: 0.0

                    computeModel.doCalculate(operation, nbr1, nbr2)
                }
            })
        }

        computeModel.resultLiveData.observe(viewLifecycleOwner, Observer {
            result.text = getString(R.string.result, it ?: 0)
        })

      /*  button.setOnClickListener {
            val nbr1 = nombre1.toDouble()?:return@setOnClickListener
            val nbr2 = nombre2.toDouble()?:return@setOnClickListener

            result.text = getString(R.string.result, .format2numbers())
        }*/
    }
}