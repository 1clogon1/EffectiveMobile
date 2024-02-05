package com.example.effective_mobile.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.effective_mobile.Fragment.Main.BasketFragment
import com.example.effective_mobile.Fragment.Main.CategoryFragment
import com.example.effective_mobile.Fragment.Main.HomeFragment
import com.example.effective_mobile.Fragment.Main.ProfileFragment
import com.example.effective_mobile.Fragment.Main.PromotionFragment
import com.example.effective_mobile.R
import com.example.effective_mobile.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            binding.buttonNavigation.selectedItemId = R.id.home

        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.CLMainFragment, HomeFragment.newInstance())
            ?.commit()



            binding.buttonNavigation.setOnItemSelectedListener {

                when (it.itemId) {
                    R.id.home -> {
                            activity?.supportFragmentManager?.beginTransaction()
                                ?.replace(R.id.CLMainFragment, HomeFragment.newInstance())
                                ?.commit()
                    }

                    R.id.category -> {
                            activity?.supportFragmentManager?.beginTransaction()
                                ?.replace(R.id.CLMainFragment, CategoryFragment.newInstance())
                                ?.commit()
                    }

                    R.id.basket -> {
                            activity?.supportFragmentManager?.beginTransaction()
                                ?.replace(R.id.CLMainFragment, BasketFragment.newInstance())
                                ?.commit()
                    }

                    R.id.promotion -> {
                            activity?.supportFragmentManager?.beginTransaction()
                                ?.replace(R.id.CLMainFragment, PromotionFragment.newInstance())
                                ?.commit()
                    }

                    R.id.profile -> {
                        activity?.supportFragmentManager?.beginTransaction()
                            ?.replace(R.id.CLMainFragment, ProfileFragment.newInstance())
                            ?.commit()
                    }

                }
                true

        }




    }

    companion object {

        fun newInstance() = MainFragment()
    }
}