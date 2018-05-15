package idrisadetunmbi.githubusers.models;

import com.google.gson.annotations.SerializedName;

public class GithubUser {

    @SerializedName("login")
    private String mUsername;

    @SerializedName("organizations_url")
    private String mOrganization;

    public GithubUser(String username, String organization) {
        mUsername = username;
        mOrganization = organization;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getOrganization() {
        return mOrganization;
    }
}
