package dev.gustavodahora.githubprofiles.services

import android.content.SharedPreferences
import dev.gustavodahora.githubprofiles.model.ConstUtil

class DBShared {
    var editor: SharedPreferences.Editor? = null

    private fun getEditor(pref: SharedPreferences) {
        editor = pref.edit()
    }

    fun getTheme(pref: SharedPreferences): Int {
        return pref.getInt(ConstUtil.THEME, ConstUtil.LIGHT)
    }

    fun setTheme(pref: SharedPreferences, theme: Int) {
        getEditor(pref)
        editor!!.putInt(ConstUtil.THEME, theme).apply()
    }
}