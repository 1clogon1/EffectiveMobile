package com.example.effective_mobile.Pref

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class SavePref (){


    @SuppressLint("CommitPrefEdits")
    fun saveAuth(context: Context, token: String) {
        val prefAuth: SharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
        prefAuth.edit().putString("authUser", token).apply()
    }
    @SuppressLint("CommitPrefEdits")
    fun saveName(context: Context, token: String) {
        val prefRegister: SharedPreferences = context.getSharedPreferences("register", Context.MODE_PRIVATE)
        prefRegister.edit().putString("name", token).apply()
    }

    @SuppressLint("CommitPrefEdits")
    fun saveSurname(context: Context, token: String) {
        val prefRegister: SharedPreferences = context.getSharedPreferences("register", Context.MODE_PRIVATE)
        prefRegister.edit().putString("surname", token).apply()
    }

    @SuppressLint("CommitPrefEdits")
    fun savePhone(context: Context, token: String) {
        val prefRegister: SharedPreferences = context.getSharedPreferences("register", Context.MODE_PRIVATE)
        prefRegister.edit().putString("phone", token).apply()
    }



}