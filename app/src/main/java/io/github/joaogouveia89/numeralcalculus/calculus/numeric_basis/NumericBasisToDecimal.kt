package io.github.joaogouveia89.numeralcalculus.calculus.numeric_basis

import android.os.Process

class NumericBasisToDecimal(
    private val input : String,
    private val outputBasis : Int
) : Runnable{

    private var _result = ""

    override fun run() {
        // Moves the current Thread into the background
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND)
        toDecimal()
    }

    private fun toDecimal() {
        var decimal = 0
        for (i in input.length - 1 downTo 0) {
            decimal += (Math.pow(
                outputBasis.toDouble(),
                i.toDouble()
            ) * Character.getNumericValue(input[input.length - i - 1])).toInt()
        }
        _result = decimal.toString()
    }
}