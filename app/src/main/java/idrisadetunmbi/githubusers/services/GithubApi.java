package idrisadetunmbi.githubusers.services;

import idrisadetunmbi.githubusers.models.GithubUsersResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GithubApi {
    @GET("search/users?q=location:nairobi+language:java")
    Call<GithubUsersResponse> getUsers();
}
