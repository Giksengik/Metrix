package com.company.metrix.services.diagnostic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.metrix.databinding.FragmentAwardsBinding
import com.company.metrix.databinding.FragmentDiagnosticBinding
import com.company.metrix.model.Question

class FragmentDiagnostic : Fragment() {

    private var binding : FragmentDiagnosticBinding? = null
    private var questionAdapter : DiagnosticQuestionListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiagnosticBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupBackButton()
        setupQuestionsAdapter()
    }

    private fun setupBackButton() {
        binding?.backButton?.setOnClickListener{
            activity?.onBackPressed()
        }
    }

    private fun setupQuestionsAdapter(){
        questionAdapter = DiagnosticQuestionListAdapter()
        binding?.diagnosticList?.apply{
            layoutManager = LinearLayoutManager(activity)
            adapter = questionAdapter
        }
        addDummyQuestions()
    }

    private fun addDummyQuestions(){
        questionAdapter?.submitList(
            listOf("ABOBOBOBO", "KGSJGIOSDPOGJSOG", "OISJDGOPGJSOPDG",
            "OISIGJDGOJPSODG", "MSGIDJGSD").map{
                Question(it)
            }
        )
    }

}