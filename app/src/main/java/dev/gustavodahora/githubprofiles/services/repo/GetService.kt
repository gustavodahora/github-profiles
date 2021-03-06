package dev.gustavodahora.githubprofiles.services.repo

import dev.gustavodahora.githubprofiles.services.model.Repos
import dev.gustavodahora.githubprofiles.services.model.UserProfile
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetService {
    @GET("users/{username}")
    fun list(@Path("username") username: String): Call<UserProfile>

    @GET("users/{username}/repos")
    fun listRepos(@Path("username") username: String): Call<List<Repos>>
}