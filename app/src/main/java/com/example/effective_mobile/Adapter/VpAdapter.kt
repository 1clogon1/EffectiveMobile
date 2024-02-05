package com.example.effective_mobile.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class VpAdapter(fr_act:FragmentActivity, private val list:List<Fragment>):FragmentStateAdapter(fr_act) {

    override fun getItemCount(): Int {
        return list.size
    }


    override fun createFragment(position: Int): Fragment {
        return list[position]
    }
}