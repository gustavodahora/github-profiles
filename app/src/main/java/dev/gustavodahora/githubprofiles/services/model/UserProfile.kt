package dev.gustavodahora.githubprofiles.services.model

import com.google.gson.annotations.SerializedName

class UserProfile {
    @SerializedName("avatar_url")
    var avatarUrl: String? = ""

    @SerializedName("login")
    var login: String? = ""

    @SerializedName("name")
    var name: String? = ""

    @SerializedName("followers")
    var followers: Int? = 0

    @SerializedName("public_repos")
    var publicRepos: Int? = 0
}