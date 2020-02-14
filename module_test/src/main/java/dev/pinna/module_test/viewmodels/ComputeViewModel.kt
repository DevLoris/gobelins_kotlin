package dev.pinna.module_test.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.pinna.module_test.models.Operation

class ComputeViewModel : ViewModel() {
    private val _resultLiveData = MutableLiveData<Double>()
    val resultLiveData : LiveData<Double>
        get() = _resultLiveData

    fun doCalculate(operation: Operation, first: Double, second: Double) {
        _resultLiveData.value = when (operation) {
            Operation.SUM -> first + second
            Operation.MULTIPLY -> first * second
            Operation.DIVISION -> first / second
            Operation.SUBSTRACT -> first - second
        }
    }
}