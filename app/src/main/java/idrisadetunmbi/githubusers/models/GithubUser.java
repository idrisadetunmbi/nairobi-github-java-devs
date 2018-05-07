package idrisadetunmbi.githubusers.models;

public class GithubUser {

    private String mUsername;
    private String mOrganization;

    public GithubUser(String username, String organization) {
        mUsername = username;
        mOrganization = organization;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getOrganization() {
        return mOrganization;
    }

    public void setOrganization(String organization) {
        mOrganization = organization;
    }
}
