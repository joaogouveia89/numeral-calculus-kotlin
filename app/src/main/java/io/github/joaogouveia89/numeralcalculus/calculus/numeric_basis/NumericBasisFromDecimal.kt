package io.github.joaogouveia89.numeralcalculus.calculus.numeric_basis

import android.os.Process
import android.util.Log
import java.util.concurrent.Callable

class NumericBasisFromDecimal(val decimal : String, val outputBasis : Int) : Callable<String> {
    override fun call(): String {
        Log.i("NumericBasisFromDecimal", "starting conversion for $decimal to base $outputBasis")
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND)
        var result = ""
        var num = Integer.parseInt(decimal)
        while (num != 0) {
            result =
                getNumberChar(num % outputBasis) + result
            num /= outputBasis
        }
        return result
    }

    protected fun getNumberChar(n: Int): Char {
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