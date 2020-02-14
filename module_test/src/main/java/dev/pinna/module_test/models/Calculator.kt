package dev.pinna.module_test.models

class Calculator {
    companion object {
        fun doCalculate(operation: Operation, first: Double, second: Double): Double {
            return when (operation) {
                Operation.SUM -> first + second
                Operation.MULTIPLY -> first * second
                Operation.DIVISION -> first / second
                Operation.SUBSTRACT -> first - second
            }
        }
    }
}