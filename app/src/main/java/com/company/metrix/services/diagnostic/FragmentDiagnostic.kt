package com.company.metrix.services.diagnostic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.metrix.BackButtonHandler
import com.company.metrix.R
import com.company.metrix.databinding.FragmentDiagnosticBinding
import com.company.metrix.model.Question

class FragmentDiagnostic : Fragment() , BackButtonHandler {

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
        setupOnBackButtonPressed()
        setupQuestionsAdapter()
        setupButton()
    }

    private fun setupQuestionsAdapter(){
        questionAdapter = DiagnosticQuestionListAdapter()
        binding?.diagnosticList?.apply{
            layoutManager = LinearLayoutManager(activity)
            adapter = questionAdapter
        }
        addDummyQuestions()
    }

    private fun setupButton() {
        binding?.confirmDiagnosticButton?.setOnClickListener{
            Toast.makeText(context, getString(R.string.diagnostic_send_text)
                , Toast.LENGTH_SHORT).show()
        }
    }

    private fun addDummyQuestions(){
        questionAdapter?.submitList(
            listOf("ABOBOBOBO", "KGSJGIOSDPOGJSOG", "OISJDGOPGJSOPDG",
            "OISIGJDGOJPSODG", "MSGIDJGSD").map{
                Question(it)
            }
        )
    }

    override fun setupOnBackButtonPressed() {
        binding?.backButton?.setOnClickListener{
            activity?.onBackPressed()
        }
    }

}