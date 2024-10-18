package com.example.shareworkspace.data.data_source.local.prefs

import android.content.SharedPreferences
import com.example.shareworkspace.data.model.User
import com.example.shareworkspace.utils.fromJson
import com.example.shareworkspace.utils.json

class PreferenceHelper(private val sharedPreferences: SharedPreferences) {

    var lat: Double
        get() = sharedPreferences.getFloat("lat", 0.0f).toDouble()
        set(value) = sharedPreferences.edit().putFloat("lat", value.toFloat()).apply()

    var lon: Double
        get() = sharedPreferences.getFloat("lon", 0.0f).toDouble()
        set(value) = sharedPreferences.edit().putFloat("lon", value.toFloat()).apply()

    var token: String
        get() = sharedPreferences.getString("token", "") ?: ""
        set(value) = sharedPreferences.edit().putString("token", value).apply()

    var isLogin: Boolean
        get() = sharedPreferences.getBoolean("isLogin", false)
        set(value) = sharedPreferences.edit().putBoolean("isLogin", value).apply()

    var user = sharedPreferences.getString("user", "")?.fromJson<User>() ?: "".fromJson()
        set(value) = sharedPreferences.edit().putString("user", value.json()).apply()

}