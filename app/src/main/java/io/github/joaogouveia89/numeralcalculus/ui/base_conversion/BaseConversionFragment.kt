package io.github.joaogouveia89.numeralcalculus.ui.base_conversion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import io.github.joaogouveia89.numeralcalculus.R
import io.github.joaogouveia89.numeralcalculus.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_base_conversion.*

class BaseConversionFragment : BaseFragment() {

    private lateinit var baseConversionViewModel: BaseConversionViewModel
    private val onBasisChangeListener = OnBasisSeekBackChangeListener()
    private val inputTextWatcher = InputTextWatcher{
        baseConversionViewModel.initUserInput(input.text.toString(), base.progress)
        activity?.runOnUiThread {
            result.text = baseConversionViewModel.getConversion(baseConversionViewModel.getBasisBySeekBarProgress(base.progress))
        }
    }

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

        initData()
        initViewsListeners()
        initObservers(view)
    }

    private fun initObservers(view: View) {
        onBasisChangeListener.basis.observe(viewLifecycleOwner, Observer {
            hideKeyboard()
            val seekBarBase = baseConversionViewModel.getBasisBySeekBarProgress(it)
            baseDiscrete.text = resources.getString(R.string.basis_seek_label, seekBarBase)
            if(input.text.toString().isEmpty()){
                baseConversionViewModel.userInputBasis = seekBarBase
            }else{
                baseConversionViewModel.userInput = input.text.toString()
                result.text = baseConversionViewModel.getConversion(seekBarBase)
            }
            baseConversionViewModel.convertCacheBasis(seekBarBase)
        })


        baseConversionViewModel.errorMessageResource.observe(this, Observer {
            if(it == R.string.error_invalid_number_base){
                input.error = getString(it)
            }else{
                val snackbar = Snackbar.make(view, getString(it), Snackbar.LENGTH_LONG)
                snackbar.show()
            }
        })
    }

    private fun initViewsListeners() {
        base.setOnSeekBarChangeListener(onBasisChangeListener)
        input.addTextChangedListener(inputTextWatcher)
    }

    private fun initData() {
        baseConversionViewModel.userInputBasis = base.progress
        baseDiscrete.text = resources.getString(R.string.basis_seek_label, 2)
        result.text = input.text
    }
}