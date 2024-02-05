package com.example.effective_mobile.Fragment.Main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.effective_mobile.Fragment.Main.HeartList.HeartFragment
import com.example.effective_mobile.Fragment.RegistrFragment
import com.example.effective_mobile.Pref.ClearPref
import com.example.effective_mobile.Pref.ConclusionPref
import com.example.effective_mobile.Pref.SavePref
import com.example.effective_mobile.R
import com.example.effective_mobile.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    val prefAuthConclusion = ConclusionPref()
    val prefAuthClear = ClearPref()
    val prefAuthSave = SavePref()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name_surname = prefAuthConclusion.conclusionName(requireContext())+" "+prefAuthConclusion.conclusionSurname(requireContext())
        binding.txtNameAndSurname.text = name_surname

        val phone = "+7 "+prefAuthConclusion.conclusionPhone(requireContext())
        binding.txtPhone.text = phone

        binding.btnExit.setOnClickListener {
            prefAuthClear.clearAuth(requireContext())
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.CLMain, RegistrFragment.newInstance())
                ?.commit()//Заменяем наш экран на фрагмент (используем наш экран как основу)//R.id.placeHolder - куда всталяем //MainFragment.newInstance() - это то что мы вставляем

        }

        binding.btnHeart.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.CLMainFragment, HeartFragment.newInstance())
                ?.commit()//Заменяем наш экран на фрагмент (используем наш экран как основу)//R.id.placeHolder - куда всталяем //MainFragment.newInstance() - это то что мы вставляем

        }
    }

    companion object {

        fun newInstance() = ProfileFragment()
    }
}