package io.github.joaogouveia89.numeralcalculus.calculus.numeric_basis

import android.os.Process

object NumericBasisFromDecimal {
    fun fromDecimal(input : String, outputBasis : Int) {
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND)
        var result = ""
        var num = Integer.parseInt(input)
        while (num != 0) {
            result = getNumberChar(num % outputBasis) + result
            num /= outputBasis
        }
    }

    private fun getNumberChar(n: Int): Char {
        var number = n
        val c: Char
        if (n in 0..9) {
            c = (n + 48).toChar()
        } else {
            number -= 10
            c = (n + 65).toChar()
        }
        return c
    }
}