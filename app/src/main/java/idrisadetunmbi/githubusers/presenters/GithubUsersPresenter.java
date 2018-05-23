package idrisadetunmbi.githubusers.presenters;

import java.util.List;

import idrisadetunmbi.githubusers.UsersContract;
import idrisadetunmbi.githubusers.models.GithubUser;

public class GithubUsersPresenter implements UsersContract.ActionsListener {
    private UsersContract.View mGithubUserView;
    private UsersContract.Data mData;

    public GithubUsersPresenter(UsersContract.View githubUserView, UsersContract.Data data) {
        mGithubUserView = githubUserView;
        mData = data;
    }

    @Override
    public void loadUsers() {
        if (mGithubUserView.userIsOnline()) {
            mGithubUserView.setProgressIndicator(true);
            mData.getUsers(new UsersContract.Data.Callback<List<GithubUser>>() {
                @Override
                public void onLoaded(List<GithubUser> data) {
                    mGithubUserView.setProgressIndicator(false);
                    mGithubUserView.showUsers(data);
                }
            });
        } else {
            mGithubUserView.setProgressIndicator(false);
            mGithubUserView.showNetworkUnavailabilitySnackbar();
        }
    }

    @Override
    public void openDetailsView(GithubUser user) {
        mGithubUserView.showUserDetailsUi(user);
    }
}
