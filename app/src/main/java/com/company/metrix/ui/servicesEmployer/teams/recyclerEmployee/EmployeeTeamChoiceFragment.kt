package com.company.metrix.ui.servicesEmployer.teams.recyclerEmployee

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.company.metrix.R
import com.company.metrix.data.model.Team
import com.company.metrix.databinding.FragmentEmployeeTeamChoiceBinding
import com.company.metrix.ui.servicesEmployer.TeamViewModel
import com.company.metrix.ui.servicesEmployer.teamRecycler.TeamAdapter
import com.company.metrix.ui.servicesEmployer.teamRecycler.TeamModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.random.Random

@AndroidEntryPoint
class EmployeeTeamChoiceFragment : Fragment() {
    private lateinit var adapter: TeamAdapter
    private val viewModel: TeamViewModel by viewModels()

    private var _binding: FragmentEmployeeTeamChoiceBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.viewModelScope.launch {
            viewModel.getTeamsOfUser()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmployeeTeamChoiceBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

        viewModel.apply {
            teamList.observe(viewLifecycleOwner, {
                adapter.submitList(teamList.value)
                binding.teamsList.adapter = adapter
            })
        }
    }

    private fun init() {
        binding.content.visibility = View.VISIBLE
        binding.loadingBar.visibility = View.GONE

        val clickListener = object : TeamAdapter.OnTeamClickListener {
            override fun onTeamClick(teamModel: TeamModel, position: Int) {
                val action =
                    EmployeeTeamChoiceFragmentDirections.actionEmployeeTeamChoiceFragmentToEmployeeTeamFragment(
                        teamModel
                    )
                findNavController().navigate(action)
            }
        }
        adapter = TeamAdapter(clickListener)

        binding.buttonConfirm.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.run {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setCancelable(false)
                setContentView(R.layout.create_team_dialog)
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }


            val enter: TextInputEditText = dialog.findViewById(R.id.new_team)
            val exit: AppCompatImageButton = dialog.findViewById(R.id.exit)
            val people: AppCompatButton = dialog.findViewById(R.id.people)
            val confirm: MaterialButton = dialog.findViewById(R.id.button_confirm_team)

            confirm.setOnClickListener {
                viewModel.apply {
                    viewModelScope.launch {
                        createNewTeam(
                            Team(
                                id = this.hashCode().toLong(),
                                companyName = viewModel.currentUser.value!!.companyName,
                                team_id = Random(10000).nextLong(),
                                team_name = enter.text.toString()
                            )
                        )
                        dialog.dismiss()
                    }
                }
            }

            exit.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }
    }

}