package idrisadetunmbi.githubusers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import idrisadetunmbi.githubusers.models.GithubUser;

public class UsersListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private static final List<GithubUser> GITHUB_USERS = Arrays.asList(
            new GithubUser("Jon Doe", "Jon Does' Org"),
            new GithubUser("Jane Whatevs", "Whatevs")
    );

    public static List<GithubUser> getGithubUsers() {
        return GITHUB_USERS;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        mRecyclerView = findViewById(R.id.users_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        GithubUserAdapter adapter = new GithubUserAdapter(GITHUB_USERS);
        mRecyclerView.setAdapter(adapter);
    }
}
