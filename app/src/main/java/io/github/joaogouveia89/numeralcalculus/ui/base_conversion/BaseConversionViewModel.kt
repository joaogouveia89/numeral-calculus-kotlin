package io.github.joaogouveia89.numeralcalculus.ui.base_conversion

import android.util.SparseArray
import androidx.core.util.containsKey
import androidx.core.util.set
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.joaogouveia89.numeralcalculus.R
import io.github.joaogouveia89.numeralcalculus.base.BaseFragmentViewModel
import io.github.joaogouveia89.numeralcalculus.calculus.numeric_basis.NumericBasisFromDecimal
import io.github.joaogouveia89.numeralcalculus.calculus.numeric_basis.NumericBasisToDecimal
import java.util.concurrent.Future
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class BaseConversionViewModel : BaseFragmentViewModel() {


    /*
    * this constant describes the range after and before the seek bar position to be converted
    * and save a cache to improve performance on results showing to the user
    * this constant may affect the performance, be careful
     */
    private val conversionRange = 2

    private val numberOfCores = Runtime.getRuntime().availableProcessors()

    private val pool : ThreadPoolExecutor

    private val conversionsQueue = LinkedBlockingDeque<Runnable>()
    private val conversions = SparseArray<Future<String>>()

    private lateinit var base10Process : NumericBasisToDecimal

    private var base10Result : String = ""

    private val base10Observer = object : NumericBasisToDecimal.Base10ConversionListener{
        override fun isDone(result: String) {
            base10Result = result
            convertCacheBasis()
        }

        override fun outBound() {
            _errorMessageResource.postValue(R.string.error_out_bound)
        }
    }

    var userInput : String = ""
    var userInputBasis : Int = 0
        set(value) {
            field = getBasisBySeekBarProgress(value)
        }

    fun clearData(){
        userInput = ""
        userInputBasis = 0
    }
    init {
        pool = ThreadPoolExecutor(
            numberOfCores * 2,
            numberOfCores * 2,
            60L,
            TimeUnit.SECONDS,
            conversionsQueue
        )
    }

    private val _errorMessageResource = MutableLiveData<Int>()

    val errorMessageResource : LiveData<Int>
        get() = _errorMessageResource

    fun convertCacheBasis(basis : Int = userInputBasis){
        val range = getConversionRange(basis)
        for(base in range){
            if(!conversions.containsKey(base)){
                conversions[base] = pool.submit(NumericBasisFromDecimal(base10Result, base))
            }
        }
    }

    private fun getConversionRange(currentBasis: Int) = when {
        currentBasis - conversionRange < 2 -> {
            2..(currentBasis + conversionRange)
        }
        currentBasis + conversionRange > 36 -> {
            (currentBasis - conversionRange)..36
        }
        else -> {
            (currentBasis - conversionRange)..(currentBasis + conversionRange)
        }
    }

    fun getBasisBySeekBarProgress(progress: Int) = progress + 2

    fun getConversion(base :Int) : String{
        return if(base == userInputBasis){
            userInput
        }else if(base == 10){
            base10Result
        }else{
            conversions[base]?.get() ?: ""
        }
    }

    fun initUserInput(input: String, progress: Int) {
        if(
            BaseConvertionValidation.validate(
                input,
                getBasisBySeekBarProgress(progress)
            )
        ){
            userInput = input
            userInputBasis = progress
            conversions.clear()
            base10Process = NumericBasisToDecimal(userInput, userInputBasis, base10Observer)
            Thread(base10Process).start()
        }else{
            _errorMessageResource.postValue(R.string.error_invalid_number_base)
        }
    }
}