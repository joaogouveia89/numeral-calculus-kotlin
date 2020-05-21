package io.github.joaogouveia89.numeralcalculus.calculus.expression

import android.util.Log

//1. step: most simple case = without parenthensis and fractions, just numbers and operation signs and just
class ExpressionEvaluator (private val expression : String){
    private var _value : Int = 0
    private var currentNumber = 0
    private var currentOperation : Operation? = null
    val value : Int
        get() = _value

    fun evaluate() : Boolean{
        expression.forEachIndexed{ index, c ->
            if(c.isDigit()){
                currentNumber = currentNumber * 10 + (c.toInt() - 48)
            }else if(Operation.isOperation(c)){
                currentOperation = Operation.getOperation(c)
            }

            if(currentNumber != 0 && currentOperation != null || index+1  == expression.length ){
                if(_value == 0){
                    _value = currentNumber
                }else {
                    _value = currentOperation?.operation?.invoke(_value, currentNumber) ?: _value
                    currentOperation = null
                }
                currentNumber = 0
            }
        }
        return true
    }

    sealed class Operation(val symbol : Char, val operation :((Int, Int) -> Int)){
        class Sum : Operation('+', { a,b ->
            a + b
        })
        class Minus : Operation('-',  { a,b ->
            a - b
        })
        class Times : Operation('*',  { a,b ->
            a * b
        })
        class Div : Operation('/',  { a,b ->
            a / b
        })

        companion object{
            fun isOperation(c : Char) : Boolean = c == '+' || c == '-' || c == '*' || c == '/'
            fun getOperation(c : Char) : Operation? = when(c){
                '+' -> Sum()
                '-' -> Minus()
                '*' -> Times()
                '/' -> Div()
                else -> null
            }
        }
    }
}
