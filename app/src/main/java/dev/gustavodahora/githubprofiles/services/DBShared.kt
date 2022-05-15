package dev.gustavodahora.githubprofiles.services

import android.content.SharedPreferences
import dev.gustavodahora.githubprofiles.model.ConstUtil
import dev.gustavodahora.githubprofiles.model.ThemeStatus

class DBShared {
    var editor: SharedPreferences.Editor? = null

    private fun getEditor(pref: SharedPreferences) {
        editor = pref.edit()
    }

    fun getTheme(pref: SharedPreferences): ThemeStatus {
        when (pref.getInt(ConstUtil.THEME, ConstUtil.LIGHT)) {
            ConstUtil.LIGHT -> return ThemeStatus.LIGHT
            ConstUtil.DARK -> return ThemeStatus.DARK
        }
        return ThemeStatus.LIGHT
    }

    fun setTheme(pref: SharedPreferences, theme: Int) {
        getEditor(pref)
        editor!!.putInt(ConstUtil.THEME, theme).apply()
    }
}