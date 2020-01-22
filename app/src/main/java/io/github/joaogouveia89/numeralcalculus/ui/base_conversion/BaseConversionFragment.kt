package io.github.joaogouveia89.numeralcalculus.ui.base_conversion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import io.github.joaogouveia89.numeralcalculus.R
import io.github.joaogouveia89.numeralcalculus.base.BaseFragment
import io.github.joaogouveia89.numeralcalculus.calculus.numeric_basis.NumericBasisFromDecimal
import io.github.joaogouveia89.numeralcalculus.calculus.numeric_basis.NumericBasisToDecimal
import kotlinx.android.synthetic.main.fragment_base_conversion.*

class BaseConversionFragment : BaseFragment() {

    private lateinit var baseConversionViewModel: BaseConversionViewModel
    private val onBasisChangeListener = OnBasisSeekBackChangeListener()

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

        base.setOnSeekBarChangeListener(onBasisChangeListener)

        onBasisChangeListener.basis.observe(viewLifecycleOwner, Observer {
            baseDiscrete.text = resources.getString(R.string.basis_seek_label, it)
            baseConversionViewModel.convert(it, input.text.toString())
            result.text = baseConversionViewModel.conversions[it]
        })

        baseDiscrete.text = resources.getString(R.string.basis_seek_label, 2)
        baseConversionViewModel.errorMessageResource.observe(this, Observer {
            val snackbar = Snackbar.make(view, getString(it), Snackbar.LENGTH_LONG)
            snackbar.show()
        })
    }
}