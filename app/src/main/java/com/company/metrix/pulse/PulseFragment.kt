package com.company.metrix.pulse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.metrix.R
import com.company.metrix.databinding.FragmentPulseBinding
import com.company.metrix.model.PulseQuestion


class PulseFragment : Fragment() {

    private var binding : FragmentPulseBinding? = null
    private var questionListAdapter: PulseQuestionsListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPulseBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupQuestionsAdapter()
        setupDummyData()
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

    private fun setupQuestionsAdapter(){
        questionListAdapter = PulseQuestionsListAdapter()
        binding?.pulseList?.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = questionListAdapter
        }
    }

}