package com.example.effective_mobile.Fragment.Main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.effective_mobile.R
import com.example.effective_mobile.databinding.FragmentPromotionBinding


class PromotionFragment : Fragment() {
    private lateinit var binding: FragmentPromotionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPromotionBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    companion object {

        fun newInstance() = PromotionFragment()
    }
}