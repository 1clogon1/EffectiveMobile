package com.example.effective_mobile.Pref

import android.content.Context
import android.content.SharedPreferences

class ConclusionPref() {

    fun conclusionAuth(context: Context):String {
        val prefAuth: SharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
        val auth = prefAuth.getString("authUser", "")
        return auth.toString()
    }

    fun conclusionName(context: Context):String {
        val prefRegister: SharedPreferences = context.getSharedPreferences("register", Context.MODE_PRIVATE)
        val register = prefRegister.getString("name", "")
        return register.toString()
    }
    fun conclusionSurname(context: Context):String {
        val prefRegister: SharedPreferences = context.getSharedPreferences("register", Context.MODE_PRIVATE)
        val register = prefRegister.getString("surname", "")
        return register.toString()
    }
    fun conclusionPhone(context: Context):String {
        val prefRegister: SharedPreferences = context.getSharedPreferences("register", Context.MODE_PRIVATE)
        val register = prefRegister.getString("phone", "")
        return register.toString()
    }


}