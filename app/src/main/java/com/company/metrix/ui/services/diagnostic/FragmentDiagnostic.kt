package com.company.metrix.ui.services.diagnostic

import android.annotation.SuppressLint
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
import com.company.metrix.data.model.Question
import com.company.metrix.data.model.TeamMemberInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDiagnostic : Fragment(), BackButtonHandler {

    private lateinit var binding: FragmentDiagnosticBinding
    private var questionAdapter: DiagnosticQuestionListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiagnosticBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupOnBackButtonPressed()
        setupQuestionsAdapter()
        setupButton()
    }

    private fun setupQuestionsAdapter() {
        questionAdapter = DiagnosticQuestionListAdapter()
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
        val team = listOf(
            TeamMemberInfo(
                "Степанов Илья",
                "руководитель аналитического отдела"
            ),
            TeamMemberInfo(
                "Большиков Степан",
                "главный аналитик"
            ),
            TeamMemberInfo(
                "Колесников Алексей",
                "аналитик"
            ),
            TeamMemberInfo(
                "Безбах Анатолий",
                "аналитик"
            )
        )
        val rand = java.util.Random()
        val teamMember = team[rand.nextInt(team.size)]
        questionAdapter?.submitList(
            listOf(
                "Точно исполняет поручения",
                "Выполняет задания в полном объеме",
                "Соблюдает сроки",
                "Развивает навыки и умения",
                "Повышает имидж компании"
            ).map {
                Question(it)
            }
        )
        binding.diagnosticDescription.text = "${
            binding.diagnosticDescription.text
        } ${teamMember.name}"
    }

    override fun setupOnBackButtonPressed() {
        binding.backButton.setOnClickListener {
            activity?.onBackPressed()
        }
    }

}