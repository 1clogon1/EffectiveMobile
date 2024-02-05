package com.example.effective_mobile.Fragment.Main.HeartList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.effective_mobile.R
import com.example.effective_mobile.databinding.FragmentHeartBrandmarkeBinding


class HeartBrandmarkeFragment : Fragment() {
    private lateinit var binding: FragmentHeartBrandmarkeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeartBrandmarkeBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {

        fun newInstance() = HeartBrandmarkeFragment()
    }
}