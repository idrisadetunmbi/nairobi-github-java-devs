package idrisadetunmbi.githubusers.models

import com.google.gson.annotations.SerializedName

class GithubUsersResponse {

    @SerializedName("items")
    val githubUsers: List<GithubUser>? = null
}
