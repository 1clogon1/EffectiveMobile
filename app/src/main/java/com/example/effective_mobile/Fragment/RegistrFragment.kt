package com.example.effective_mobile.Fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.effective_mobile.Pref.ClearPref
import com.example.effective_mobile.Pref.ConclusionPref
import com.example.effective_mobile.Pref.SavePref
import com.example.effective_mobile.R
import com.example.effective_mobile.databinding.FragmentRegisterBinding


class RegistrFragment : Fragment() {
    lateinit var binding:FragmentRegisterBinding

    val prefAuthSave = SavePref()

    var NameValid = 0
    var SurnameValid = 0
    var NumberValid = 0
    var lastChar = " "

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentRegisterBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Valid()
        PhoneMask()
        ButtonCLear()


        binding.btnRegister.setOnClickListener {
            prefAuthSave.saveAuth(requireContext(), "1")
            prefAuthSave.saveName(requireContext(), binding.edName.text.toString())
            prefAuthSave.saveSurname(requireContext(), binding.edSurname.text.toString())
            prefAuthSave.savePhone(requireContext(), "+7 "+binding.edPhone.text.toString())

            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.CLMain, MainFragment.newInstance())
                ?.commit()
        }

    }

    private fun ButtonCLear()= with(binding) {
        btnClearName.setOnClickListener {
            edName.setText("")
        }
        btnClearSurname.setOnClickListener {
            edSurname.setText("")
        }
        btnClearPhone.setOnClickListener {
            edPhone.setText("")
        }
    }


    private fun PhoneMask() {
        binding.edPhone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                val digits: Int = binding.edPhone.getText().toString().length
                if (digits > 1) lastChar = binding.edPhone.getText().toString().substring(digits - 1)
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val digits: Int = binding.edPhone.getText().toString().length
                Log.d("LENGTH", "" + digits)
                if (!lastChar.equals("-") && !lastChar.equals(" ")) {
                    if (digits == 3) {
                        binding.edPhone.append(" ")
                    }
                    else if(digits == 7 || digits == 10){
                        binding.edPhone.append("-")
                    }
                    else{
                            binding.edPhone.append("")
                    }
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    fun alphabetCheck(input: String): Boolean {
        val regex = Regex("[А-Яа-яЁё]+?")
        return regex.matches(input)
    }

    private fun Valid() {
        binding.edName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            @SuppressLint("ResourceAsColor")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val text = p0.toString()
                val matchesAlphabet = alphabetCheck(text)
                if((text.length <=20) && (matchesAlphabet)) {
                    binding.CVSurnameError.setCardBackgroundColor(Color.TRANSPARENT)
                    NameValid =1
                } else if (matchesAlphabet) {
                    binding.CVNameError.setCardBackgroundColor(Color.TRANSPARENT)
                    NameValid = 0
                } else if(text.isEmpty()) {
                    binding.CVNameError.setCardBackgroundColor(Color.TRANSPARENT)
                    NameValid = 0
                }
                else{
                    binding.CVNameError.setCardBackgroundColor(Color.RED)
                    NameValid = 0
                }

                //Вывод крестика
                if(text.isEmpty()){
                    binding.btnClearName.visibility = View.GONE
                }
                else{
                    binding.btnClearName.visibility = View.VISIBLE
                }
                TrueValidButton()
            }
        })

        binding.edSurname.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            @SuppressLint("ResourceAsColor")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val text = p0.toString()
                val matchesAlphabet = alphabetCheck(text)
                if((text.length <=25) && (matchesAlphabet)) {
                    binding.CVSurnameError.setCardBackgroundColor(Color.TRANSPARENT)
                    SurnameValid =1


                } else if (matchesAlphabet) {
                    binding.CVSurnameError.setCardBackgroundColor(Color.TRANSPARENT)
                    SurnameValid =0
                } else if(text.isEmpty()) {
                    binding.CVSurnameError.setCardBackgroundColor(Color.TRANSPARENT)
                    SurnameValid =0
                    binding.btnClearName.visibility = View.GONE
                }
                else{
                    binding.CVSurnameError.setCardBackgroundColor(Color.RED)
                    SurnameValid =0
                }

                //Вывод крестика
                if(text.isEmpty()){
                    binding.btnClearSurname.visibility = View.GONE
                }
                else{
                    binding.btnClearSurname.visibility = View.VISIBLE
                }

                TrueValidButton()
            }
        })

        binding.edPhone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            @SuppressLint("ResourceAsColor")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val text = p0.toString()
                if(text.length==13){
                    binding.CVPhoneError.setCardBackgroundColor(Color.TRANSPARENT)
                    NumberValid =1
                }
                else if (text.length ==1 && text == "7") {
                    binding.edPhone.setText("")
                    NumberValid =0
                }
                else if (text.length < 13) {
                    binding.CVPhoneError.setCardBackgroundColor(Color.TRANSPARENT)
                    NumberValid =0
                } else if(text.isEmpty()) {
                    binding.CVPhoneError.setCardBackgroundColor(Color.TRANSPARENT)
                    binding.edPhone.setText("")
                    NumberValid =0
                }
                else{
                    binding.CVPhoneError.setCardBackgroundColor(Color.RED)
                    NumberValid =0
                }

                //Вывод крестика
                if(text.isEmpty()){
                    binding.btnClearPhone.visibility = View.GONE
                }
                else{
                    binding.btnClearPhone.visibility = View.VISIBLE
                }

                TrueValidButton()

            }
        })
    }

    fun TrueValidButton(){
        if(NameValid == 1 && SurnameValid == 1 && NumberValid == 1){
            binding.btnRegister.isEnabled = true
            binding.btnRegister.isClickable = true

            binding.btnRegister.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.Pink))
        }
        else{
            binding.btnRegister.isEnabled = false
            binding.btnRegister.isClickable = false
            binding.btnRegister.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.Light_Pink))
        }
    }


    companion object {
        fun newInstance() = RegistrFragment()
    }
}