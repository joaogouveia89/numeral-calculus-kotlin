package io.github.joaogouveia89.numeralcalculus.ui.expressions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.joaogouveia89.numeralcalculus.calculus.expression.ExpressionEvaluator
import io.github.joaogouveia89.numeralcalculus.calculus.fraction.Fraction

class ExpressionsViewModel : ViewModel() {

    private val fractionValidationRegex  ="^(\\d+|(frac\\(\\d+\\)|frac\\(\\d+,\\d+\\)|\\d+))"

    val evaluatedExpression : LiveData<Int>
        get() = _evaluatedExpression

    private val _evaluatedExpression = MutableLiveData<Int>()

    fun evaluateExpression(input: String){
        val evaluator = ExpressionEvaluator(input)
        if(evaluator.evaluate()){
            _evaluatedExpression.value = evaluator.value
        }
    }

}