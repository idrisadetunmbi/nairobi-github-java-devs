package idrisadetunmbi.githubusers.services

import idrisadetunmbi.githubusers.models.GithubUsersResponse
import retrofit2.Call
import retrofit2.http.GET

interface GithubApi {
    @get:GET("search/users?q=location:nairobi+language:java")
    val users: Call<GithubUsersResponse>
}
