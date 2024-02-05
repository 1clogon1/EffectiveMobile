package com.example.effective_mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.effective_mobile.Fragment.MainFragment
import com.example.effective_mobile.Fragment.RegistrFragment
import com.example.effective_mobile.Pref.ClearPref
import com.example.effective_mobile.Pref.ConclusionPref
import com.example.effective_mobile.Pref.SavePref
import com.example.effective_mobile.Room.EntityHeart
import com.example.effective_mobile.Room.ProductDB
import com.example.effective_mobile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding

    val prefAuthConclusion = ConclusionPref()
    val prefAuthClear = ClearPref()
    val prefAuthSave = SavePref()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val Auth = prefAuthConclusion.conclusionAuth(this)


        if(Auth == "1"){
            supportFragmentManager?.beginTransaction()
                ?.replace(R.id.CLMain, MainFragment.newInstance())
                ?.commit()
        }
        else{
            supportFragmentManager?.beginTransaction()
                ?.replace(R.id.CLMain, RegistrFragment.newInstance())
                ?.commit()
        }

    }
}