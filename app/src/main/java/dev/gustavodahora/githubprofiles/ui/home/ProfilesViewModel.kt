package dev.gustavodahora.githubprofiles.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.gustavodahora.githubprofiles.services.model.UserProfile
import dev.gustavodahora.githubprofiles.services.repo.GetService
import dev.gustavodahora.githubprofiles.services.repo.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilesViewModel : ViewModel() {

    private val _userProfile = MutableLiveData<UserProfile>()
    val userProfile: LiveData<UserProfile> = _userProfile

    private val _errorStatus = MutableLiveData<Boolean>()
    val  errorStatus: LiveData<Boolean> = _errorStatus

    fun retrofitCall(username: String) {
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
}