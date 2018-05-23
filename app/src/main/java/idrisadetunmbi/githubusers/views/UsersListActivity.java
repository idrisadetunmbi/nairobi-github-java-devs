package idrisadetunmbi.githubusers.views;

import android.content.Intent;
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
import idrisadetunmbi.githubusers.UsersContract;
import idrisadetunmbi.githubusers.adapters.GithubUserAdapter;
import idrisadetunmbi.githubusers.models.GithubUser;
import idrisadetunmbi.githubusers.models.GithubUsersResponse;
import idrisadetunmbi.githubusers.presenters.GithubUsersPresenter;
import idrisadetunmbi.githubusers.services.GithubService;
import idrisadetunmbi.githubusers.utils.NetworkInfo;
import retrofit2.Call;
import retrofit2.Response;

public class UsersListActivity extends AppCompatActivity implements UsersContract.View {

    public static final String USERS_LIST_KEY = "ADAPTER_LIST_STATE";
    private GithubUserAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private UsersContract.ActionsListener mUserActionsListener;
    UserItemListener mUserItemListener = new UserItemListener() {
        @Override
        public void onUserItemClick(GithubUser user) {
            mUserActionsListener.openDetailsView(user);
        }
    };
    private UsersContract.Data mData;

    @Override
    public boolean userIsOnline() {
        return NetworkInfo.isOnline(this);
    }

    @Override
    public void showNetworkUnavailabilitySnackbar() {
        Snackbar.make(findViewById(R.id.activity_users_list_fl_parent_view),
                R.string.snack_bar_msg, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.snack_bar_action_text, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mUserActionsListener.loadUsers();
                    }
                }).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        // TODO: Abstract and replace with injection
        mData = new UsersContract.Data() {
            @Override
            public void getUsers(final Callback<List<GithubUser>> callback) {
                new GithubService()
                        .getAPI()
                        .getUsers()
                        .enqueue(new retrofit2.Callback<GithubUsersResponse>() {
                            @Override
                            public void onResponse(Call<GithubUsersResponse> call,
                                                   Response<GithubUsersResponse> response) {
                                GithubUsersResponse data = response.body();
                                if (data != null && data.getGithubUsers() != null) {
                                    callback.onLoaded(data.getGithubUsers());
                                }
                            }

                            @Override
                            public void onFailure(Call<GithubUsersResponse> call, Throwable t) {

                            }
                        });
            }
        };

        mUserActionsListener = new GithubUsersPresenter(this, mData);

        mRecyclerView = findViewById(R.id.users_list_rv);
        mAdapter = new GithubUserAdapter(mUserItemListener, new ArrayList<GithubUser>(0));
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mAdapter);

        setUpSwipeRefresh();
        mProgressBar = findViewById(R.id.activity_users_list_pb_loading_users);

        if (savedInstanceState == null) {
            mUserActionsListener.loadUsers();
        }
    }

    @Override
    public void setProgressIndicator(boolean isLoading) {
        mProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        mRecyclerView.setVisibility(isLoading ? View.GONE : View.VISIBLE);
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(isLoading);
        }
    }

    @Override
    public void showUsers(List<GithubUser> users) {
        mAdapter.setGithubUsers(users);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showUserDetailsUi(GithubUser user) {
        Intent intent = DetailActivity.newIntent(this, user);
        startActivity(intent);
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

    private void setUpSwipeRefresh() {
        mSwipeRefreshLayout = findViewById(R.id.activity_users_list_swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mUserActionsListener.loadUsers();
            }
        });
    }

    public interface UserItemListener {
        void onUserItemClick(GithubUser user);
    }
}
