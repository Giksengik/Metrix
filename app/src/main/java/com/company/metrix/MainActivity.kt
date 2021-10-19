package com.company.metrix

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.Window
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.company.metrix.data.model.User
import com.company.metrix.databinding.ActivityMainBinding
import com.company.metrix.databinding.MoveUserDialogBinding
import com.company.metrix.ui.servicesEmployee.ServiceViewModel
import com.company.metrix.ui.support.getOnItemSelectListener
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


//    override fun onBackPressed() {
//        val count = supportFragmentManager.backStackEntryCount
//
//        if (count == 0) {
//            super.onBackPressed()
//            //additional code
//        } else {
//            supportFragmentManager.popBackStack()
//        }
//
//    }
}