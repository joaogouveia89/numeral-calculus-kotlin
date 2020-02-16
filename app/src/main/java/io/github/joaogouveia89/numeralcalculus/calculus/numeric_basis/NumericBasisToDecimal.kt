package io.github.joaogouveia89.numeralcalculus.calculus.numeric_basis

import android.util.Log
import io.github.joaogouveia89.numeralcalculus.ktx.length
import kotlin.math.pow

class NumericBasisToDecimal(private val input : String,
                            private  val inputBasis : Int,
                            val listener : Base10ConversionListener) : Runnable {
    val result : String
        get() = _result
    private var _result : String = ""

    override fun run() {
        Log.i("NumericBasisToDecimal", "starting conversion for $input to base 10")
        var decimal = 0
        for (i in input.length - 1 downTo 0) {
            decimal += (inputBasis.toDouble().pow(i.toDouble()) * Character.getNumericValue(input[input.length - i - 1])).toInt()
            if(decimal.length() > 9){
                listener.outBound()
                break
            }
        }
        _result = decimal.toString()
        listener.isDone(result)
    }

    interface Base10ConversionListener{
        fun isDone(result : String)
        fun outBound()
    }
}