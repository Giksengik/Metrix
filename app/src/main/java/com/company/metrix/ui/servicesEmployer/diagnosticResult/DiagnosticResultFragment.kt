package com.company.metrix.ui.servicesEmployer.diagnosticResult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.company.metrix.databinding.FragmentDiagnosticResultBinding
import com.company.metrix.ui.servicesEmployer.teamRecycler.TeamModel


class DiagnosticResultFragment : Fragment() {
    private val args: DiagnosticResultFragmentArgs by navArgs()
    private var _binding: FragmentDiagnosticResultBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ResultsAdapter

    private var team: TeamModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        team = args.team
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiagnosticResultBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loadingBar.visibility = View.GONE
        binding.content.visibility = View.VISIBLE

        adapter = ResultsAdapter()

        binding.results.adapter = adapter

        adapter.submitList(
            listOf(
                "Точно исполняет поручения 100%",
                "Точно исполняет поручения 10%",
                "Точно исполняет поручения 20%",
                "Точно исполняет поручения 80%",
                "Точно исполняет поручения 100%",
            )
        )

    }

}