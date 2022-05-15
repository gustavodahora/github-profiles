package dev.gustavodahora.githubprofiles

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import dev.gustavodahora.githubprofiles.model.ThemeStatus
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MainViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel(Mockito.mock(Application::class.java))
    }

    @Test
    fun `run a setupTheme and verify the status variable`() {
        val theme = viewModel.themeSetup.getOrAwaitValueTest()
        Truth.assertThat(theme).isNotNull()
    }
}