package dev.gustavodahora.githubprofiles

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.gustavodahora.githubprofiles.model.ConstUtil
import dev.gustavodahora.githubprofiles.model.ThemeStatus
import dev.gustavodahora.githubprofiles.services.DBShared

class MainViewModel(application: Application) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val mContext: Context = application.applicationContext

    private val _themeSetup: MutableLiveData<ThemeStatus> = MutableLiveData()
    val themeSetup: LiveData<ThemeStatus> = _themeSetup

    private val dbShared: DBShared = DBShared()

    fun getTheme() {
        val pref = mContext.getSharedPreferences(
            "dev.gustavodahora.githubprofiles",
            Context.MODE_PRIVATE
        )
        _themeSetup.value = dbShared.getTheme(pref)
    }
}