package dev.gustavodahora.githubprofiles

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import dev.gustavodahora.githubprofiles.model.ConstUtil
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito


class MainViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    var sharedPrefs: SharedPreferences = Mockito.mock(SharedPreferences::class.java)
    var context = Mockito.mock(Context::class.java)

    @Before
    fun setup() {
        val application = Mockito.mock(Application::class.java)
        sharedPrefs = Mockito.mock(SharedPreferences::class.java)
        context = Mockito.mock(Context::class.java)

        Mockito.`when`(application.applicationContext).thenReturn(context)
        Mockito.`when`(context.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPrefs)
        viewModel = MainViewModel(application)
    }

    @Test
    fun `run a setupTheme and verify the status variable`() {
        Mockito.`when`(sharedPrefs.getInt(anyString(), anyInt())).thenReturn(ConstUtil.LIGHT)

        viewModel.getTheme()

        val theme = viewModel.themeSetup.getOrAwaitValueTest()

        Truth.assertThat(theme).isNotNull()
    }
}