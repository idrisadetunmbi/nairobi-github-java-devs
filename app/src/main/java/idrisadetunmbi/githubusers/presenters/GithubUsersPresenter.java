package idrisadetunmbi.githubusers.presenters;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import idrisadetunmbi.githubusers.models.GithubUser;
import idrisadetunmbi.githubusers.models.GithubUsersResponse;
import idrisadetunmbi.githubusers.services.GithubService;
import idrisadetunmbi.githubusers.views.GithubUserView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubUsersPresenter {
    private GithubService mGithubService;
    private GithubUserView mGithubUserView;

    public GithubUsersPresenter(GithubUserView githubUserView) {
        mGithubUserView = githubUserView;
        if (mGithubService == null) {
            mGithubService = new GithubService();
        }
    }

    public void getUsersFromApi() {
        mGithubService
                .getAPI()
                .getUsers()
                .enqueue(new Callback<GithubUsersResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<GithubUsersResponse> call,
                                           @NonNull Response<GithubUsersResponse> response) {
                        GithubUsersResponse data = response.body();
                        if (data != null && data.getGithubUsers() != null) {
                            List<GithubUser> githubUsers = data.getGithubUsers();
                            mGithubUserView.githubUsersReady(githubUsers);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GithubUsersResponse> call,
                                          @NonNull Throwable t) {
                        try {
                            throw new InterruptedException("Something went wrong");
                        } catch (InterruptedException e) {
                            Log.d("ERROR_RETRIEVING_USERS", e.getLocalizedMessage());
                        }
                    }
                });
    }
}
