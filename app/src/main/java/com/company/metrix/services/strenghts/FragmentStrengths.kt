package com.company.metrix.services.strenghts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.metrix.databinding.FragmentStrengthsBinding

class FragmentStrengths : Fragment() {

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
    }

    private fun setupStrengthsList() {
        strengthsAdapter = CharacteristicListAdapter()
        binding?.strengthsList?.apply{
            adapter = strengthsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun addDummyStrengths(){

    }
}