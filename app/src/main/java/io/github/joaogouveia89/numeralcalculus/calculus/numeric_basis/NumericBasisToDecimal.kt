package io.github.joaogouveia89.numeralcalculus.calculus.numeric_basis

import io.github.joaogouveia89.numeralcalculus.calculus.OnConversionFinished

class NumericBasisFromDecimal(
    private val decimal : String,
    private val outputBasis : Int,
    private val listener : OnConversionFinished
) : Runnable{
    private var _result : String = ""

    override fun run() {
        fromDecimal()
        listener.conversionResult(_result, outputBasis)
    }

    private fun fromDecimal() {
        var num = Integer.parseInt(this.decimal)
        while (num != 0) {
            this._result = getNumberChar(num % this.outputBasis) + this._result
            num /= this.outputBasis
        }
    }

    private fun getNumberChar(n: Int): Char {
        var n = n
        val c: Char
        if (n >= 0 && n < 10) {
            c = (n + 48).toChar()
        } else {
            n -= 10
            c = (n + 65).toChar()
        }
        return c
    }
}