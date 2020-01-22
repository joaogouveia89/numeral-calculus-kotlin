package io.github.joaogouveia89.numeralcalculus.ui.base_conversion

import android.widget.SeekBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class OnBasisSeekBackChangeListener : SeekBar.OnSeekBarChangeListener {
    val basis : LiveData<Int>
        get() = _basis

    private val _basis = MutableLiveData<Int>()

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        _basis.postValue(progress + 2)
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
}