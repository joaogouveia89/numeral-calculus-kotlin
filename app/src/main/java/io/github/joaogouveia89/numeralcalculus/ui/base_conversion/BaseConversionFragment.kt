package io.github.joaogouveia89.numeralcalculus.ui.base_conversion

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import io.github.joaogouveia89.numeralcalculus.R
import io.github.joaogouveia89.numeralcalculus.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_base_conversion.*

class BaseConversionFragment : BaseFragment() {

    private lateinit var baseConversionViewModel: BaseConversionViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        baseConversionViewModel =
            ViewModelProviders.of(this).get(BaseConversionViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_base_conversion, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapterBases = ArrayAdapter<String>(view.context,
            R.layout.support_simple_spinner_dropdown_item, baseConversionViewModel.getBaseLabels())
        base_spinner.adapter = adapterBases

        baseConversionViewModel.actionConversionIsDone = { results ->
            binary_result.text = getString(R.string.number_conversion_result, 2, results[2])
            octal_result.text = getString(R.string.number_conversion_result, 8, results[8])
            decimal_result.text = getString(R.string.number_conversion_result, 10, results[10])
            hexa_result.text = getString(R.string.number_conversion_result, 16, results[16])
        }

        baseConversionViewModel.errorMessageResource.observe(this, Observer {
            val snackbar = Snackbar.make(view, getString(it), Snackbar.LENGTH_LONG)
            snackbar.show()
        })
        convert.setOnClickListener {
            hideKeyboard()
            baseConversionViewModel.convert(input.text.toString(), base_spinner.selectedItemPosition)
        }
    }
}