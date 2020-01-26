package io.github.joaogouveia89.numeralcalculus.ui.base_conversion

import android.text.Editable
import android.text.TextWatcher
import java.util.*

class InputTextWatcher( val startCalculation : (() -> Unit)) : TextWatcher {
    private var timer = Timer()
    companion object{
        const val TEXT_CHANGING_CALCULATION_MS = 500L
    }
    override fun afterTextChanged(s: Editable?) {
        timer.cancel()
        timer = Timer()
        timer.schedule(object :TimerTask(){
            override fun run() {
                startCalculation.invoke()
            }
        }, TEXT_CHANGING_CALCULATION_MS)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}