package com.company.metrix

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.company.metrix.databinding.FragmentMainBinding
import com.company.metrix.ui.servicesEmployee.ServiceViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    private val args: MainFragmentArgs by navArgs()
    private var position: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args.position.let {
            position = it
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        //menu.se( R.menu.ruler_bottom_navigation_menu )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupNav()
    }


    private fun setupNav() {

        when(position){
            "Сотрудник"->{
                setupEmployeeNavigation()
            }
            else ->{
                setupEmployerNavigation()
            }
        }

        // fragment.men
      }

    private fun setupEmployerNavigation() {
        binding.navHostEmployee.visibility = View.GONE
        binding.navHostEmployer.visibility = View.VISIBLE
        val fragment =
            childFragmentManager.findFragmentById(R.id.nav_host_employer) as NavHostFragment
        val controller = fragment.findNavController()
        binding.bottomNav.visibility = View.GONE
        binding.rulerBottomNav.visibility = View.VISIBLE
        binding.rulerBottomNav.setupWithNavController(controller)
    }

    private fun setupEmployeeNavigation() {
        binding.navHostEmployee.visibility = View.VISIBLE
        binding.navHostEmployer.visibility = View.GONE
        val fragment =
            childFragmentManager.findFragmentById(R.id.nav_host_employee) as NavHostFragment
        val controller = fragment.findNavController()
        binding.bottomNav.visibility = View.VISIBLE
        binding.rulerBottomNav.visibility = View.GONE
        binding.bottomNav.setupWithNavController(controller)
    }
}