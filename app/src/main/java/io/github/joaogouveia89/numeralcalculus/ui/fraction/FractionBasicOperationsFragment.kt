package io.github.joaogouveia89.numeralcalculus.ui.fraction


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import io.github.joaogouveia89.numeralcalculus.R

/**
 * A simple [Fragment] subclass.
 */
class FractionBasicOperationsFragment : Fragment() {

    private lateinit var fractionViewModel: FractionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fractionViewModel = ViewModelProviders.of(this).get(FractionViewModel::class.java)
        return inflater.inflate(R.layout.fragment_fraction_basic_operations, container, false)
    }


}
