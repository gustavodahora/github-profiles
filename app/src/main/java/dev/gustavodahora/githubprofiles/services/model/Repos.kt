package dev.gustavodahora.githubprofiles.services.model

import com.google.gson.annotations.SerializedName

class Repos {
    @SerializedName("name")
    var name: String? = ""

    @SerializedName("full_name")
    var fullName: String? = ""
}