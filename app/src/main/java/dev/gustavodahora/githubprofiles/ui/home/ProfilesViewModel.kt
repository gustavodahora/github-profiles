package dev.gustavodahora.githubprofiles.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.gustavodahora.githubprofiles.services.model.Repos
import dev.gustavodahora.githubprofiles.services.model.UserProfile
import dev.gustavodahora.githubprofiles.services.repo.GetService
import dev.gustavodahora.githubprofiles.services.repo.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilesViewModel : ViewModel() {

    private val _userProfile = MutableLiveData<UserProfile>()
    val userProfile: LiveData<UserProfile> = _userProfile

    private val _repos = MutableLiveData<List<Repos>>()
    val repos: LiveData<List<Repos>> = _repos

    private val _errorStatus = MutableLiveData<Boolean>()
    val  errorStatus: LiveData<Boolean> = _errorStatus

    fun callUserProfile(username: String) {
        val remote = RetrofitClient.createService(GetService::class.java)
        val call: Call<UserProfile> = remote.list(username)

        call.enqueue(object : Callback<UserProfile> {
            override fun onResponse(
                call: Call<UserProfile>,
                response: Response<UserProfile>
            ) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        _userProfile.value = response.body()
                        _errorStatus.value = false
                    }
                } else {
                    _errorStatus.value = true
                }
            }

            override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                _errorStatus.value = true
            }
        })
    }

    fun callRecyclerRepo(username: String) {
        val remote = RetrofitClient.createService(GetService::class.java)
        val call: Call<List<Repos>> = remote.listRepos(username)

        call.enqueue(object : Callback<List<Repos>> {
            override fun onResponse(
                call: Call<List<Repos>>,
                response: Response<List<Repos>>
            ) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        _repos.value = response.body()
                        _errorStatus.value = false
                    }
                } else {
                    _errorStatus.value = true
                }
            }

            override fun onFailure(call: Call<List<Repos>>, t: Throwable) {
                _errorStatus.value = true
            }
        })
    }
}