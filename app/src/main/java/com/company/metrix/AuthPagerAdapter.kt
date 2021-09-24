package com.company.metrix

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

import androidx.viewpager2.adapter.FragmentStateAdapter
import java.lang.IllegalStateException

class AuthPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    private val numOfPages = 2

    override fun getItemCount(): Int = numOfPages

    override fun createFragment(position: Int): Fragment =
        when(position){
            0 -> FragmentAuthEmployee()
            1 -> FragmentEstimate()
            else -> throw IllegalStateException("Unexpected Tab!!")
        }


}