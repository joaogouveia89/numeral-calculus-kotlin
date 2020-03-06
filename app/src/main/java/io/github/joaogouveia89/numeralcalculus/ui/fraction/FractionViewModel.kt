package io.github.joaogouveia89.numeralcalculus.ui.fraction

import androidx.lifecycle.ViewModel
import io.github.joaogouveia89.numeralcalculus.calculus.fraction.Fraction

class FractionViewModel : ViewModel() {

    private val fractionValidationRegex  ="^(\\d+|(frac\\(\\d+\\)|frac\\(\\d+,\\d+\\)|\\d+))"

    fun teste(){
        val f = Fraction(1,2)
        val f2 = Fraction(1, 2)
        val f3 = f / f2
    }

}