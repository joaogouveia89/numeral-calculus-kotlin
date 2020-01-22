package io.github.joaogouveia89.numeralcalculus.calculus.numeric_basis

import io.github.joaogouveia89.numeralcalculus.calculus.OnConversionFinished


class NumericBasisToDecimal(
    private val input : String,
    private val basis : Int,
    private val listener : OnConversionFinished)
    : Runnable {

    private var _result = ""

    override fun run() {
        _result = toDecimal()
        listener.conversionResult(_result, 10)
    }

    private fun toDecimal(): String {
        var decimal = 0
        for (i in input.length - 1 downTo 0) {
            decimal += (Math.pow(
                basis.toDouble(),
                i.toDouble()
            ) * Character.getNumericValue(input[input.length - i - 1])).toInt()
        }
        return decimal.toString()
    }
}