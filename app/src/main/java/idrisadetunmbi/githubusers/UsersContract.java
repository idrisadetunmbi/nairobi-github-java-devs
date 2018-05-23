package idrisadetunmbi.githubusers;

import java.util.List;

import idrisadetunmbi.githubusers.models.GithubUser;

public interface UsersContract {
    interface View {
        void setProgressIndicator(boolean isLoading);

        void showUsers(List<GithubUser> users);

        void showUserDetailsUi(GithubUser user);

        boolean userIsOnline();

        void showNetworkUnavailabilitySnackbar();
    }

    interface ActionsListener {
        void loadUsers();

        void openDetailsView(GithubUser user);
    }

    interface Data {
        void getUsers(Callback<List<GithubUser>> callback);

        interface Callback<T> {
            void onLoaded(T data);
        }
    }
}
