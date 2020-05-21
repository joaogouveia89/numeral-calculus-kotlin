package io.github.joaogouveia89.numeralcalculus.ui.expressions


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import io.github.joaogouveia89.numeralcalculus.R
import kotlinx.android.synthetic.main.fragment_expressions.*

/**
 * A simple [Fragment] subclass.
 */
class ExpressionsFragment : Fragment() {

    private lateinit var expressionsViewModel : ExpressionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        expressionsViewModel = ViewModelProviders.of(this).get(ExpressionsViewModel::class.java)
        expressionsViewModel.evaluatedExpression.observe(viewLifecycleOwner, Observer {
            resultLabel.visibility = View.VISIBLE
            resultLabel.text = it.toString()
        })
        return inflater.inflate(R.layout.fragment_expressions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bt_calculate.setOnClickListener {
            expressionsViewModel.evaluateExpression(et_input.text.toString())
        }
    }
}
