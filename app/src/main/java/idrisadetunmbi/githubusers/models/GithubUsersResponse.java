package idrisadetunmbi.githubusers.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GithubUsersResponse {

    @SerializedName("items")
    private List<GithubUser> mGithubUsers;

    public List<GithubUser> getGithubUsers() {
        return mGithubUsers;
    }
}
