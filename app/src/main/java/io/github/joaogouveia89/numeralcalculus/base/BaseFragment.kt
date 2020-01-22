package io.github.joaogouveia89.numeralcalculus.base

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager

open class BaseFragment : Fragment() {
    protected var isCalculusCacheSet = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCalculusCacheSet = PreferenceManager.getDefaultSharedPreferences(activity?.baseContext).getBoolean("cache", false)
    }
    protected fun hideKeyboard(){
        val inputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}