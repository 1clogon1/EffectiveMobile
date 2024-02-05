package com.example.effective_mobile.Pref

import android.content.Context
import android.content.SharedPreferences

class ClearPref() {

    fun clearAuth(context: Context) {
        val prefDAuth: SharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
        val edit = prefDAuth.edit()
        edit?.clear()
        edit?.apply()
    }


}