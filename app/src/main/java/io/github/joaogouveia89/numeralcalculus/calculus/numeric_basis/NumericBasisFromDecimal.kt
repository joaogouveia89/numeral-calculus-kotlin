package io.github.joaogouveia89.numeralcalculus.calculus.numeric_basis

import android.os.Process

class NumericBasisFromDecimal(
    private val input : String,
    val outputBasis : Int)
    : Runnable {

    val result : String
        get() = _result

    private var _result : String = ""

    override fun run() {
        fromDecimal()
    }

    private fun fromDecimal() {
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND)
        var num = Integer.parseInt(this.input)
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