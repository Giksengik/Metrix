package com.company.metrix.ui.servicesEmployee.diagnostic

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.metrix.BackButtonHandler
import com.company.metrix.R
import com.company.metrix.data.model.Diagnostic
import com.company.metrix.data.model.TeamMemberInfo
import com.company.metrix.databinding.FragmentDiagnosticBinding
import com.company.metrix.ui.support.setupNavigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentDiagnostic : Fragment(), BackButtonHandler {
    private val viewModel: DiagnosticViewModel by viewModels()

    private var _binding: FragmentDiagnosticBinding? = null
    private val binding get() = _binding!!

    private var questionAdapter: DiagnosticQuestionListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        requireActivity().setupNavigation(
            this,
            FragmentDiagnosticDirections.actionFragmentDiagnosticToServiceFragment()
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiagnosticBinding.inflate(inflater)

        viewModel.viewModelScope.launch {
            viewModel.getTeamMembers()

            val rand = java.util.Random()
            val teamMember = viewModel.users.value!![rand.nextInt(viewModel.users.value!!.size)]

            binding.diagnosticDescription.text = "${
                binding.diagnosticDescription.text
            } ${teamMember.name}"
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupOnBackButtonPressed()
        setupQuestionsAdapter()
        setupButton()
    }

    var questionOne = false

    private fun setupQuestionsAdapter() {
        questionAdapter =
            DiagnosticQuestionListAdapter(object : DiagnosticQuestionListAdapter.OnDiagnosticQuestionClickListener {
                override fun onDiagnosticClick(model: Diagnostic, position: Int, isYes: Boolean) {
                    Toast.makeText(
                        context, model.value, Toast.LENGTH_SHORT
                    ).show()
                }

            })
        binding.diagnosticList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = questionAdapter
        }
        addDummyQuestions()
    }

    private fun setupButton() {
        binding.confirmDiagnosticButton.setOnClickListener {

            Toast.makeText(
                context, getString(R.string.diagnostic_send_text), Toast.LENGTH_SHORT
            ).show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun addDummyQuestions() {

        var count = 0L
        questionAdapter?.submitList(
            listOf(
                "Точно исполняет поручения",
                "Выполняет задания в полном объеме",
                "Соблюдает сроки",
                "Развивает навыки и умения",
                "Повышает имидж компании"
            ).map {
                count++

                val diag = Diagnostic(
                    1,
                    count,
                    it
                )
                viewModel.viewModelScope.launch {
                    viewModel.addDiagnostic(diag)
                }

                diag
            }
        )

    }

    override fun setupOnBackButtonPressed() {
        binding.backButton.setOnClickListener {
            val action = FragmentDiagnosticDirections.actionFragmentDiagnosticToServiceFragment()
            findNavController().navigate(action)
        }
    }

}