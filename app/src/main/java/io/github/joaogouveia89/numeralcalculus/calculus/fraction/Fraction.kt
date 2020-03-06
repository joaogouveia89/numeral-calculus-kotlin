package io.github.joaogouveia89.numeralcalculus.calculus.fraction

class Fraction(numerator: Int, denominator: Int = 1) {
    val numerator : Int
    val denominator : Int

    init {
        if(denominator == 0){
            throw FractionException("Denominator cannot be zero")
        }else{
            this.numerator = numerator
            this.denominator = denominator
        }
    }

    //operators
    operator fun plus(otherFrac : Fraction) : Fraction =
        Fraction(
            denominator * otherFrac.numerator + otherFrac.denominator * numerator,
            otherFrac.denominator * denominator)

    operator fun plus(otherFrac : Int) : Fraction = this + Fraction(otherFrac)


    operator fun minus(otherFrac: Fraction) : Fraction =
        Fraction(
            denominator * otherFrac.numerator - otherFrac.denominator * numerator,
            otherFrac.denominator * denominator)

    operator fun minus(otherFrac : Int) : Fraction = this - Fraction(otherFrac)

    operator fun times(otherFrac: Fraction) : Fraction =
        Fraction(
            numerator * otherFrac.numerator,
            denominator * otherFrac.denominator
        )
    operator fun times(otherFrac: Int) : Fraction = this * Fraction(otherFrac)

    operator fun div(otherFrac: Fraction) : Fraction {
        val fraction = Fraction(otherFrac.denominator, otherFrac.numerator)
        return this * fraction
    }

    operator fun div(otherFrac: Int) : Fraction {
        val fraction = Fraction(1, otherFrac)
        return this * fraction
    }

    fun toDouble(): Double = numerator/denominator.toDouble()

    fun simplify() : Fraction {
        val gdc = euclidGreatterCommonDivisor(numerator, denominator)
        return Fraction(numerator/gdc, denominator/gdc)
    }

    override fun toString() = "$numerator/$denominator"

    private fun euclidGreatterCommonDivisor(a: Int, b: Int): Int {
        var a = a
        var b = b
        val aux: Int
        if (a == 0) {
            aux = a
            a = b
            b = aux
        }
        return if (b == 0) a else euclidGreatterCommonDivisor(b, a % b)
    }

    inner class FractionException(msg : String) : Exception(msg)
}