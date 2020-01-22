package io.github.joaogouveia89.numeralcalculus.ui.base_conversion

import android.util.Log
import android.util.SparseArray
import androidx.core.util.containsKey
import androidx.core.util.set
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.joaogouveia89.numeralcalculus.base.BaseFragmentViewModel
import io.github.joaogouveia89.numeralcalculus.calculus.OnConversionFinished
import io.github.joaogouveia89.numeralcalculus.calculus.numeric_basis.NumericBasisFromDecimal
import java.util.concurrent.Executors

class BaseConversionViewModel : BaseFragmentViewModel(), OnConversionFinished {

    private val NUMBER_OF_THREADS = 15
    /*
    * this constant describes the range after and before the seek bar position to be converted
    * and save a cache to improve performance on results showing to the user
    * this constant may affect the performance, be careful
     */
    private val CONVERSION_RANGE = 2

    private val cachedBasis = mutableListOf<Int>()

    val conversions = SparseArray<String>()

    private val executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

    private val _errorMessageResource = MutableLiveData<Int>()

    val errorMessageResource : LiveData<Int>
        get() = _errorMessageResource

    fun convert(seekBarPosition: Int, input : String){
        val positionRange = (seekBarPosition - CONVERSION_RANGE)..(seekBarPosition + CONVERSION_RANGE)

        if(!conversions.containsKey(10)){
            val basis10Thread = Thread(NumericBasisFromDecimal(input, seekBarPosition, this))
            cachedBasis.add(10)
            basis10Thread.start()
            basis10Thread.join()
        }
//        convertRange(positionRange)
    }

    private fun convertRange(range : IntRange){
        for(i in range){
            if(!conversions.containsKey(i)){

                executor.execute(NumericBasisFromDecimal(conversions[10], i, this))
            }
        }
    }

    override fun conversionResult(result: String, basis : Int) {
        conversions[basis] = result
    }
}