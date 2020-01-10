package io.github.joaogouveia89.numeralcalculus.ui.base_conversion

import android.util.SparseArray
import androidx.core.util.set
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.joaogouveia89.numeralcalculus.R
import io.github.joaogouveia89.numeralcalculus.base.BaseFragmentViewModel

class BaseConversionViewModel : BaseFragmentViewModel() {

    private val _errorMessageResource = MutableLiveData<Int>()

    val errorMessageResource : LiveData<Int>
        get() = _errorMessageResource

    private val conversions = SparseArray<String>()

    var actionConversionIsDone : ((results : SparseArray<String>) -> Unit)? = null


    private val bases = arrayOf<Base>(
        Base(2, "2", "^[0-1]{1,}$"),
        Base(8, "8", "^[0-7]{1,}$"),
        Base(10, "10", "^[0-9]{1,}$"),
        Base(16, "16", "^[0-9A-F]{1,}$")
    )

    private fun isvalid(input: String, basePosition: Int):Boolean{
        val regex = bases[basePosition].validationRegex.toRegex()
        return regex.matches(input)
    }

    fun convert(input:String, basePosition:Int){
        if(isvalid(input,basePosition)){
            conversions[bases[basePosition].value] = input
            if(bases[basePosition].value != 10){
                conversions[10] = toDecimal(input, basePosition)
            }
            bases.forEachIndexed { index, base ->
                if(index != basePosition && base.value != 10){
                    conversions[base.value] = fromDecimal(base.value, conversions[10])
                }
            }
            actionConversionIsDone?.invoke(conversions)
        }else{
            _errorMessageResource.postValue(R.string.error_invalid_number_base)
        }

    }

    private fun toDecimal(input: String, base : Int): String {
        var decimal = 0
        for (i in input.length - 1 downTo 0) {
            decimal += (Math.pow(
                bases[base].value.toDouble(),
                i.toDouble()
            ) * Character.getNumericValue(input[input.length - i - 1])).toInt()
        }
        return decimal.toString()
    }

    private fun fromDecimal(desiredBase: Int, decimal: String): String {
        var num = Integer.parseInt(decimal)
        var result = ""
        while (num != 0) {
            result = getNumberChar(num % desiredBase) + result
            num /= desiredBase
        }
        return result
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

    fun getBaseLabels() = bases.map { it.label }

    data class Base(val value:Int, val label:String, val validationRegex:String)
}