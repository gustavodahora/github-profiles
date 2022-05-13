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

    fun retrofitCall(username: String) {
        val remote = RetrofitClient.createService(GetService::class.java)
        val call: Call<UserProfile> = remote.list(username)

        val response = call.enqueue(object : Callback<UserProfile> {
            override fun onResponse(
                call: Call<UserProfile>,
                response: Response<UserProfile>
            ) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        _userProfile.value = response.body()
                    }
                }
            }

            override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                val s = t.message
            }
        })
    }
}