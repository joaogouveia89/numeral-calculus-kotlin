package io.github.joaogouveia89.numeralcalculus.ui.base_conversion

import android.util.SparseArray
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.joaogouveia89.numeralcalculus.R
import io.github.joaogouveia89.numeralcalculus.base.BaseFragmentViewModel
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class BaseConversionViewModel : BaseFragmentViewModel() {
    /*
    * this constant describes the range after and before the seek bar position to be converted
    * and save a cache to improve performance on results showing to the user
    * this constant may affect the performance, be careful
     */
    private val CONVERSION_RANGE = 2

    private val NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors()

    private val pool : ThreadPoolExecutor

    private val conversionsQueue = LinkedBlockingDeque<Runnable>()

    var userInput : String = ""
    var userInputBasis : Int = 0

    init {
        pool = ThreadPoolExecutor(
            NUMBER_OF_CORES * 2,
            NUMBER_OF_CORES * 2,
            60L,
            TimeUnit.SECONDS,
            conversionsQueue
        )
    }

    private val cachedBasis = mutableListOf<Int>()

    val conversions = SparseArray<Runnable>()

    private val _errorMessageResource = MutableLiveData<Int>()

    val errorMessageResource : LiveData<Int>
        get() = _errorMessageResource

    fun convert(progress: Int){
        val currentBasis = getBasisBySeekBarProgress(progress)
        val positionRange = (currentBasis - CONVERSION_RANGE)..(currentBasis + CONVERSION_RANGE)
    }

    private fun getBasisBySeekBarProgress(progress: Int) = progress + 2

    fun validateInput(input: String, seekBarPosition: Int) : Boolean{
        val regex = generateInputRegex(seekBarPosition).toRegex()
        return regex.matches(input)
    }

    private fun generateInputRegex(progress: Int): String {
        val basis = getBasisBySeekBarProgress(progress)
        var res = if(basis <= 10) "^[0-${basis-1}" else  "^[0-9"
        if(basis == 11){
            res = "${res}A"
        }else if(basis > 11){
            val ref = basis - 10
            res = "${res}A-${(ref + 64).toChar()}"
        }
        res = "$res]{1,}\$"
        return res
    }

    fun initUserInput(input: String, progress: Int) {
        if(validateInput(input, progress)){
            userInput = input
            userInputBasis = getBasisBySeekBarProgress(progress)
            convert(progress)
        }else{
            _errorMessageResource.postValue(R.string.error_invalid_number_base)
        }

    }
}