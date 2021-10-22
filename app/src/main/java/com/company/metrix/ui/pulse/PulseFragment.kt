package com.company.metrix.ui.pulse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.metrix.R
import com.company.metrix.databinding.FragmentPulseBinding
import com.company.metrix.data.model.PulseQuestion
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PulseFragment : Fragment() {
    private val viewModel: PulseViewModel by viewModels()
    private lateinit var binding: FragmentPulseBinding
    private var questionListAdapter: PulseQuestionsListAdapter? = null

    var question1 = ""
    var question2 = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPulseBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupQuestionsAdapter()
        setupDummyData()
    }

    private fun setupButton(position: Long, positionIn : Int) {
        binding.pulseConfirmButton.setOnClickListener {
            viewModel.apply {
                viewModelScope.launch {
                    updateVotes(Firebase.auth.currentUser?.email!!, position, positionIn)
                    Toast.makeText(context, getString(R.string.pulse_data_sended), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupDummyData() {
        questionListAdapter?.submitList(
            listOf(
                PulseQuestion(
                    "Как за последнее время изменились отношения в команде?",
                    listOf(
                        "Люди растеряны. Кто-то успокаивает других, а кто-то сам ждет поддержки.",
                        "Мы поддерживаем друг друга, боремся за новые задачи вместе",
                        "Отношения портятся, большинство действует в своих интересах.",
                        "Отношения не изменились. Соблюдаем правила, делаем свои дела"
                    )
                ),
                PulseQuestion(
                    "Как ваш руководитель действует в сложившейся ситуации?",
                    listOf(
                        "Делает не то, что нужно, а то , что нужно - не делает.",
                        "В целом ведет себя адекватно, но создает лишнее беспокойство",
                        "Действует грамотно и вдохновляет тем самым всю команду",
                        "Руководитель не делает ничего необычного, просто выполняет свою работу"
                    )
                )
            )
        )
    }

    private fun setupQuestionsAdapter() {
        val onClick = object : PulseQuestionsListAdapter.OnPulseQuestionsListener {
            override fun onPulseClick(value: String, position: Int, positionIn: Int) {
                if (position == 0) {
                    question1 = value
                } else if (position == 1) {
                    question2 = value
                }
                setupButton(position.toLong(), positionIn)
                Toast.makeText(context, "$value $position $positionIn", Toast.LENGTH_SHORT).show()
            }
        }

        questionListAdapter = PulseQuestionsListAdapter(onClick)
        binding.pulseList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = questionListAdapter
        }
    }

}