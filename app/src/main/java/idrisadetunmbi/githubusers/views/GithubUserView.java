package idrisadetunmbi.githubusers.views;

import java.util.List;

import idrisadetunmbi.githubusers.models.GithubUser;

public interface GithubUserView {
    void githubUsersReady(List<GithubUser> users);
}
