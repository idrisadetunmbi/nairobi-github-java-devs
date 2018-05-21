package idrisadetunmbi.githubusers.views;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import idrisadetunmbi.githubusers.R;
import idrisadetunmbi.githubusers.adapters.GithubUserAdapter;
import idrisadetunmbi.githubusers.models.GithubUser;
import idrisadetunmbi.githubusers.presenters.GithubUsersPresenter;
import idrisadetunmbi.githubusers.utils.NetworkInfo;

public class UsersListActivity extends AppCompatActivity implements GithubUserView {

    public static final String USERS_LIST_KEY = "ADAPTER_LIST_STATE";
    private GithubUserAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        mRecyclerView = findViewById(R.id.users_list_rv);
        mProgressBar = findViewById(R.id.activity_users_list_pb_loading_users);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new GithubUserAdapter();
        mRecyclerView.setAdapter(mAdapter);
        setUpSwipeRefresh();

        if (savedInstanceState == null) {
            getUsers();
        }
    }

    @Override
    public void githubUsersReady(List<GithubUser> users) {
        mAdapter.setGithubUsers(users);
        toggleVisibilities(true);
        mAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void getUsers() {
        GithubUsersPresenter presenter = new GithubUsersPresenter(this);
        if (!NetworkInfo.isOnline(this)) {
            displaySnackbar();
            if (mSwipeRefreshLayout.isRefreshing()) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
            return;
        }
        toggleVisibilities(false);
        presenter.getUsersFromApi();
    }

    private void displaySnackbar() {
        Snackbar.make(findViewById(R.id.activity_users_list_fl_parent_view),
                R.string.snack_bar_msg, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.snack_bar_action_text, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getUsers();
                    }
                }).show();
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

    public void toggleVisibilities(boolean recyclerViewIsVisible) {
        mProgressBar.setVisibility(recyclerViewIsVisible ? View.GONE : View.VISIBLE);
        mRecyclerView.setVisibility(recyclerViewIsVisible ? View.VISIBLE : View.GONE);
    }

    private void setUpSwipeRefresh() {
        mSwipeRefreshLayout = findViewById(R.id.activity_users_list_swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUsers();
            }
        });
    }
}
