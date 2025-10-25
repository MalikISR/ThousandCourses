package com.example.data.userprefs

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class UserPrefs(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    var email: String?
        get() = prefs.getString("email", null)
        set(value) = prefs.edit { putString("email", value) }

    var isLoggedIn: Boolean
        get() = prefs.getBoolean("is_logged_in", false)
        set(value) = prefs.edit { putBoolean("is_logged_in", value) }
}
