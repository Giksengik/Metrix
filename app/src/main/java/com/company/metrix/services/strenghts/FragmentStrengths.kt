package com.company.metrix.services.strenghts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.metrix.BackButtonHandler
import com.company.metrix.databinding.FragmentStrengthsBinding

class FragmentStrengths : Fragment() , BackButtonHandler {

    private var binding : FragmentStrengthsBinding? = null
    private var strengthsAdapter : CharacteristicListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStrengthsBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupStrengthsList()
        setupOnBackButtonPressed()
    }

    private fun setupStrengthsList() {
        strengthsAdapter = CharacteristicListAdapter()
        binding?.strengthsList?.apply{
            adapter = strengthsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        addDummyStrengths()
    }

    private fun addDummyStrengths(){
        var i = 0
        strengthsAdapter?.submitList(
            CharacteristicFactoryImpl().produceAllStrengths().filter { i++ % 2 == 0 }
        )
    }

    override fun setupOnBackButtonPressed() {
        binding?.backButton?.setOnClickListener{
            activity?.onBackPressed()
        }
    }

}