package com.company.metrix.ui.support

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestListener
import com.company.metrix.R
import com.company.metrix.databinding.CreateTeamDialogBinding
import com.company.metrix.ui.servicesEmployee.strenghts.FragmentStrengthsDirections
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText

fun loadImage(
    context: Context,
    url: Uri?,
    view: ImageView,
) {
    Glide.with(context)
        .load(
            url ?: "https://avatars.githubusercontent.com/u/55493845?v=4"
        )
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(view)
}

fun <T> getOnItemSelectListener(action: (item: T) -> Unit): AdapterView.OnItemSelectedListener {
    val listener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parent: AdapterView<*>,
            view: View,
            position: Int,
            id: Long
        ) {
            val item = parent.getItemAtPosition(position) as T
            action(item)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
    return listener
}

fun showTeamsDialog(activity: Activity, func: () -> Unit) {
    val dialog = Dialog(activity)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(false)
    //val binding = CreateTeamDialogBinding.inflate(layoutInflater) //Почему не работает?
    dialog.setContentView(R.layout.create_team_dialog)

    val enter: TextInputEditText = dialog.findViewById(R.id.new_team)
    val exit: FloatingActionButton = dialog.findViewById(R.id.exit)
    val people: AppCompatButton = dialog.findViewById(R.id.people)

    exit.setOnClickListener(View.OnClickListener {
        func()
        dialog.dismiss()
    })
    dialog.show()
}

fun FragmentActivity.setupNavigation(fragment: Fragment, action : NavDirections) {
    this
        .onBackPressedDispatcher
        .addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (isEnabled) {
                    isEnabled = false
                    fragment.findNavController().navigate(action)
                }
            }
        }
        )
}