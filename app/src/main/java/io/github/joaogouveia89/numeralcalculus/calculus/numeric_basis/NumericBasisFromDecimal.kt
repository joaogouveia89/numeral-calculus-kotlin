package io.github.joaogouveia89.numeralcalculus.calculus.numeric_basis

import android.os.Process
import android.util.Log
import java.util.concurrent.Callable

class NumericBasisFromDecimal(private val decimal : String, private val outputBasis : Int) : Callable<String> {
    override fun call(): String {
        Log.d("NumericBasisFromDecimal", "starting conversion for $decimal to base $outputBasis")
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND)
        var result = ""
        var num = Integer.parseInt(decimal)
        while (num != 0) {
            result =
                getNumberChar(num % outputBasis) + result
            num /= outputBasis
        }
        Log.d("NumericBasisFromDecimal", "result conversion for $decimal to base $outputBasis = $result")
        return result
    }

    private fun getNumberChar(n: Int): Char {
        var number = n
        val c: Char
        if (n in 0..9) {
            c = (n + 48).toChar()
        } else {
            Log.i("NumericBasisFromDecimal", "$n + 65")
            number -= 10
            c = (number + 65).toChar()
        }
        return c
    }
}