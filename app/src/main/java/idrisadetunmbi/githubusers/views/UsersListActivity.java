package idrisadetunmbi.githubusers.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

import idrisadetunmbi.githubusers.R;
import idrisadetunmbi.githubusers.models.GithubUser;
import idrisadetunmbi.githubusers.presenters.GithubUsersPresenter;

public class UsersListActivity extends AppCompatActivity implements GithubUserView {

    private static final List<GithubUser> GITHUB_USERS = Arrays.asList(
            new GithubUser("Jon Doe", "Jon Does' Org"),
            new GithubUser("Jane Whatevs", "Whatevs")
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        RecyclerView recyclerView = findViewById(R.id.users_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GithubUserAdapter adapter = new GithubUserAdapter(GITHUB_USERS);
        recyclerView.setAdapter(adapter);
        getUsers();
    }

    @Override
    public void githubUsersReady(List<GithubUser> users) {
        Log.i("GITHUB_USERS", "Users size: " + users.size());
    }


    public static List<GithubUser> getGithubUsers() {
        return GITHUB_USERS;
    }

    private void getUsers() {
        GithubUsersPresenter presenter = new GithubUsersPresenter(this);
        presenter.getUsersFromApi();
    }
}
