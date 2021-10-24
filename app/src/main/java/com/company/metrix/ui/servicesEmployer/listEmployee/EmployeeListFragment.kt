package com.company.metrix.ui.servicesEmployer.listEmployee

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.company.metrix.R
import com.company.metrix.data.model.Team
import com.company.metrix.data.model.User
import com.company.metrix.databinding.FragmentEmployeeListBinding
import com.company.metrix.ui.support.getOnItemSelectListener
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EmployeeListFragment : Fragment() {

    private lateinit var adapter: EmployeeListAdapter
    private val viewModel: EmployeeListViewModel by viewModels()

    private var _binding: FragmentEmployeeListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.apply {
            viewModelScope.launch {
                initial()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmployeeListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.teams.observe(viewLifecycleOwner, {
            setupRecycler()
        })

        binding.addEmployee.setOnClickListener {
            showAddDialog()
        }
    }

    private fun setupRecycler() {
        val clickListener = object : EmployeeListAdapter.OnEmployeeClickListener {
            override fun onEmployeeClick(user: User, position: Int) {
                showRemoveDialog(user)
            }
        }

        adapter = EmployeeListAdapter(clickListener, viewModel.teams.value!!)
        adapter.submitList(viewModel.allUsers.value)
        binding.employeeRecycle.adapter = adapter
    }

    private fun showAddDialog() {
        val dialog = Dialog(requireContext())

        dialog.run {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(R.layout.dialog_add_employee)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        val exit: AppCompatImageButton = dialog.findViewById(R.id.exit)

        val name: FrameLayout = dialog.findViewById(R.id.name_surname)
        val nameEt: AppCompatEditText = name.findViewById(R.id.enter_field)
        nameEt.hint = "ФИО*"

        val role: FrameLayout = dialog.findViewById(R.id.role_enter)
        val roleEt: AppCompatEditText = role.findViewById(R.id.enter_field)
        roleEt.hint = "Должность*"

        val idd: FrameLayout = dialog.findViewById(R.id.id_enter)
        val iddEt: AppCompatEditText = idd.findViewById(R.id.enter_field)
        iddEt.hint = "ID*"

        val email: FrameLayout = dialog.findViewById(R.id.email_enter)
        val emailEt: AppCompatEditText = email.findViewById(R.id.enter_field)
        emailEt.hint = "Email*"

        val phone: FrameLayout = dialog.findViewById(R.id.phone_enter)
        val phoneEt: AppCompatEditText = phone.findViewById(R.id.enter_field)
        phoneEt.hint = "Phone*"


        val dropDownList = arrayListOf<String>()
        for (item in viewModel.teams.value!!)
            dropDownList.add(item.team_name)

        val adapterMain = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            dropDownList
        )

        val team: FrameLayout = dialog.findViewById(R.id.team_select)
        val spinnerChoice: AppCompatSpinner = team.findViewById(R.id.enter_field)

        spinnerChoice.adapter = adapterMain

        var teamName = ""

        spinnerChoice.onItemSelectedListener = getOnItemSelectListener<String> {
            Toast.makeText(context, "Team : ${it}", Toast.LENGTH_SHORT).show()
        }

//        val confirm: MaterialButton = dialog.findViewById(R.id.button_confirm_remove)
//        confirm.setOnClickListener {
//            viewModel.run {
//                viewModelScope.launch {
//                    removeUser(user)
//                }
//            }
//            dialog.dismiss()
//        }
        exit.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showRemoveDialog(user: User) {
        val dialog = Dialog(requireContext())

        dialog.run {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(R.layout.user_remove_dialog)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        val name: AppCompatTextView = dialog.findViewById(R.id.name_remove)
        val role: AppCompatTextView = dialog.findViewById(R.id.role_remove)
        val teamName: AppCompatTextView = dialog.findViewById(R.id.teamName)
        val exitMove: AppCompatImageButton = dialog.findViewById(R.id.exit_remove)

        name.text = user.name
        role.text = user.role
        viewModel.viewModelScope.launch {
            teamName.text = viewModel.getTeamNameByTeamId(user.teamId, user.companyName).team_name
        }

        val confirm: MaterialButton = dialog.findViewById(R.id.button_confirm_remove)
        confirm.setOnClickListener {
            viewModel.run {
                viewModelScope.launch {
                    removeUser(user)
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