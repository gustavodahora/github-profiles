package dev.gustavodahora.githubprofiles.ui.dashboard

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.gustavodahora.githubprofiles.model.ConstUtil
import dev.gustavodahora.githubprofiles.model.ThemeStatus
import dev.gustavodahora.githubprofiles.services.DBShared

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val mContext: Context = application.applicationContext

    private val _themeSetup: MutableLiveData<ThemeStatus> = MutableLiveData()
    val themeSetup: LiveData<ThemeStatus> = _themeSetup

    private val dbShared: DBShared = DBShared()

    fun setTheme(isActivated: Boolean) {
        val pref: SharedPreferences = mContext.getSharedPreferences(
            "dev.gustavodahora.githubprofiles",
            Context.MODE_PRIVATE
        )
        when (isActivated) {
            true -> {
                dbShared.setTheme(pref, ConstUtil.DARK)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            false -> {
                dbShared.setTheme(pref, ConstUtil.LIGHT)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    fun getTheme() {
        val pref: SharedPreferences = mContext.getSharedPreferences(
            "dev.gustavodahora.githubprofiles",
            Context.MODE_PRIVATE
        )
        _themeSetup.value = dbShared.getTheme(pref)
    }
}