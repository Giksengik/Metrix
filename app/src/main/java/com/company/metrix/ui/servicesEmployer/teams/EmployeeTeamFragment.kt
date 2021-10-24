package com.company.metrix.ui.servicesEmployer.teams


import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.company.metrix.R
import com.company.metrix.data.model.User
import com.company.metrix.databinding.FragmentEmployeeTeamBinding
import com.company.metrix.ui.servicesEmployer.teamRecycler.TeamModel
import com.company.metrix.ui.servicesEmployer.teams.recyclerEmployee.EmployeeAdapter
import com.company.metrix.ui.support.getOnItemSelectListener
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class EmployeeTeamFragment : Fragment() {
    private val viewModel: EmployeeViewModel by viewModels()
    private val args: EmployeeTeamFragmentArgs by navArgs()
    private lateinit var adapter: EmployeeAdapter

    private var _binding: FragmentEmployeeTeamBinding? = null
    private val binding get() = _binding!!

    lateinit var team: TeamModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        team = args.team

        viewModel.apply {
            viewModelScope.launch {
                getUsersInTeam(team)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmployeeTeamBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.teamDescription.text = team.teamName
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.userList.observe(viewLifecycleOwner, {
            if (it.isEmpty()) {
                binding.emptyView.visibility = View.VISIBLE
                binding.loadingBar.visibility = View.GONE
            } else {
                binding.emptyView.visibility = View.GONE
                binding.loadingBar.visibility = View.VISIBLE
                adapter.submitList(it)
                binding.teamsList.adapter = adapter
                binding.loadingBar.visibility = View.GONE
            }
        })

        viewModel.userRatings.observe(viewLifecycleOwner, {
            setupRecycler()
        })
    }

    private fun setupRecycler() {
        val clickListener = object : EmployeeAdapter.OnEmployeeClickListener {
            override fun onEmployeeClick(teamModel: User, position: Int) {
            }
        }

        adapter = EmployeeAdapter(clickListener, viewModel.userRatings.value!!) { user, action ->
            when (action.actionId) {
                R.id.green -> {
                    showMoveDialog(user)
                }
                R.id.red -> {
                    removeUser(user)
                }
            }
        }
    }

    private fun removeUser(user: User) {
        viewModel.run {
            viewModelScope.launch {
                removeUser(user)
            }
        }
    }


    private fun showMoveDialog(user: User) {
        val dialog = Dialog(requireContext())

        dialog.run {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(R.layout.move_user_dialog)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        val name: AppCompatTextView = dialog.findViewById(R.id.name_move)
        val role: AppCompatTextView = dialog.findViewById(R.id.role_move)
        val exitMove: AppCompatImageButton = dialog.findViewById(R.id.exit_move)

        name.text = user.name
        role.text = user.role

        val adapterMain = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            viewModel.teamList.value!!
        )

        val spinnerChoice: AppCompatSpinner = dialog.findViewById(R.id.spinner_choice)
        spinnerChoice.adapter = adapterMain

        var teamName = ""

        spinnerChoice.onItemSelectedListener = getOnItemSelectListener<String> { teamName = it }

        val confirm: MaterialButton = dialog.findViewById(R.id.button_confirm_move)
        confirm.setOnClickListener {
            viewModel.run {
                viewModelScope.launch {
                    moveUserToOtherTeam(teamName, user.id)
                }
            }
            dialog.dismiss()
        }
        exitMove.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }


}