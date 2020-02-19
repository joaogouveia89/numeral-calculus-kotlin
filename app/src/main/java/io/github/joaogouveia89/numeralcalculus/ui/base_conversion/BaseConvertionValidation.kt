package io.github.joaogouveia89.numeralcalculus.ui.base_conversion

class BaseConvertionValidation {
    companion object{
        fun validate(input: String, seekBarPosition: Int) : Boolean{
            val regex = generateInputRegex(seekBarPosition).toRegex()
            return regex.matches(input)
        }

        private fun generateInputRegex(basis: Int): String {
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
    }

}