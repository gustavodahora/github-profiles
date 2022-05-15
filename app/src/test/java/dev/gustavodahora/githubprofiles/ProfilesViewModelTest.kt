package dev.gustavodahora.githubprofiles

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import dev.gustavodahora.githubprofiles.ui.home.ProfilesViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProfilesViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ProfilesViewModel

    @Before
    fun setup() {
        viewModel = ProfilesViewModel()
    }

    @Test
    fun `insert username with empty field returns error`() {
        viewModel.retrofitCall(" ")
        val value = viewModel.errorStatus.getOrAwaitValueTest()

        assertThat(value).isTrue()
    }

    @Test
    fun `insert username with wrong field returns error`() {
        viewModel.retrofitCall("dwijdawijd***law")
        val value = viewModel.errorStatus.getOrAwaitValueTest()

        assertThat(value).isTrue()
    }

    @Test
    fun `insert a correctly user name field returns valid user`() {
        viewModel.retrofitCall("gustavodahora")
        val errorStatus = viewModel.errorStatus.getOrAwaitValueTest()
        val profileSelected = viewModel.userProfile.getOrAwaitValueTest()

        assertThat(profileSelected).isNotNull()
        assertThat(errorStatus).isFalse()
    }
}