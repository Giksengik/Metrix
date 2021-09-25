package com.company.metrix.auth

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

import androidx.viewpager2.adapter.FragmentStateAdapter
import java.lang.IllegalStateException

class AuthPagerAdapter(fragment: FragmentActivity, val authHandler: AuthHandler) : FragmentStateAdapter(fragment) {
    private val numOfPages = 2

    override fun getItemCount(): Int = numOfPages

    override fun createFragment(position: Int): Fragment =
        when (position){
            0 -> FragmentEstimate.newInstance(authHandler = authHandler)
            1 -> FragmentAuthEmployee.newInstance(authHandler = authHandler)
            else -> throw IllegalStateException("Unexpected Tab!!")
        }


}