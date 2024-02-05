package com.example.effective_mobile.Fragment.Main.HeartList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.effective_mobile.Adapter.VpAdapter
import com.example.effective_mobile.Fragment.Main.ProfileFragment
import com.example.effective_mobile.R
import com.example.effective_mobile.databinding.FragmentHeartBinding
import com.google.android.material.tabs.TabLayoutMediator


class HeartFragment : Fragment() {
    private lateinit var binding: FragmentHeartBinding

    private val tList = listOf(
        "Товары",
        "Бренды",
    )

    //Список с фрагментами для переключения
    private val flist = listOf(
        HeartProductFragment.newInstance(),
        HeartBrandmarkeFragment.newInstance(),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeartBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()


        binding.btnBack.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.CLMainFragment, ProfileFragment.newInstance())
                ?.commit()

        }


    }


    private fun init() = with(binding) {
        val adapter = VpAdapter(activity as FragmentActivity, flist)
        vpProduct.adapter = adapter


        TabLayoutMediator(tabLayoutProduct, vpProduct) { tab, pos ->
            tab.text =
                tList[pos]
        }.attach()


        binding.tabLayoutProduct.setTabTextColors(
            getResources().getColor(R.color.Dark_grey),
            getResources().getColor(R.color.black)
        );


    }

    companion object {

        fun newInstance() = HeartFragment()
    }
}