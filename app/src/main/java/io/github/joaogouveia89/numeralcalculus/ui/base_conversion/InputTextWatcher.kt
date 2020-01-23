package io.github.joaogouveia89.numeralcalculus.ui.base_conversion

import android.text.Editable
import android.text.TextWatcher

class InputTextWatcher( val startCalculation : (() -> Unit)) : TextWatcher {
    private var lastMs : Long = 0L
    companion object{
        const val TEXT_CHANGING_CALCULATION_MS = 1000
    }
    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val currentMs = System.currentTimeMillis()
        if(currentMs - lastMs >= TEXT_CHANGING_CALCULATION_MS){
            startCalculation.invoke()
        }
        lastMs = currentMs
    }
}