package com.company.metrix.services.recomendations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.metrix.BackButtonHandler
import com.company.metrix.databinding.FragmentRecomendationsBinding
import com.company.metrix.services.strenghts.CharacteristicFactoryImpl
import com.company.metrix.services.strenghts.CharacteristicListAdapter

class FragmentRecomendations : Fragment() , BackButtonHandler{

    private var binding : FragmentRecomendationsBinding? = null
    private var weaknessAdapter : CharacteristicListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecomendationsBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupStrengthsList()
        setupOnBackButtonPressed()
    }

    private fun setupStrengthsList() {
        weaknessAdapter = CharacteristicListAdapter()
        binding?.weaknessList?.apply{
            adapter = weaknessAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        addDummyStrengths()
    }

    private fun addDummyStrengths(){
        var i = 0
        weaknessAdapter?.submitList(
            CharacteristicFactoryImpl().produceAllStrengths().filter { i++ % 2 == 1 }
        )
    }

    override fun setupOnBackButtonPressed() {
        binding?.backButton?.setOnClickListener{
            activity?.onBackPressed()
        }
    }
}