package com.ducdiep.newsapp.repository

import android.content.Context
import android.content.SharedPreferences


object AppPreferences {
    private const val NAME = "AppPreferences"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    private val PASS_CODE = Pair("PassCode","")
    private val IS_FIRST_TIME = Pair("IsFirstTime", true)

    fun init(context: Context?) {
        preferences = context?.getSharedPreferences(NAME, MODE)!!
    }
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var password: String
        get() = preferences.getString(PASS_CODE.first, PASS_CODE.second).toString()
        set(value) = preferences.edit {
            it.putString(PASS_CODE.first, value)
        }
    var isFirstTime: Boolean
        get() = preferences.getBoolean(IS_FIRST_TIME.first, IS_FIRST_TIME.second)
        set(value) = preferences.edit {
            it.putBoolean(IS_FIRST_TIME.first, value)
        }
}