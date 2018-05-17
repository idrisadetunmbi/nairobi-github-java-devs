package idrisadetunmbi.githubusers.views;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import idrisadetunmbi.githubusers.R;
import idrisadetunmbi.githubusers.adapters.GithubUserAdapter;
import idrisadetunmbi.githubusers.models.GithubUser;
import idrisadetunmbi.githubusers.presenters.GithubUsersPresenter;

public class UsersListActivity extends AppCompatActivity {

    private GithubUserAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    public final static String USERS_LIST_KEY = "ADAPTER_LIST_STATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        RecyclerView recyclerView = findViewById(R.id.users_list);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new GithubUserAdapter();
        recyclerView.setAdapter(mAdapter);
        if (savedInstanceState == null) {
            getUsers();
        }
    }

    private void getUsers() {
        GithubUsersPresenter presenter = new GithubUsersPresenter(mAdapter);
        presenter.getUsersFromApi();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(USERS_LIST_KEY,
                (ArrayList<? extends Parcelable>) mAdapter.getGithubUsers());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mAdapter.setGithubUsers(savedInstanceState
                .<GithubUser>getParcelableArrayList(USERS_LIST_KEY));
    }
}
