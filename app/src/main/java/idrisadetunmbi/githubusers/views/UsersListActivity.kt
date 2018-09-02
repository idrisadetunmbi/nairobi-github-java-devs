package idrisadetunmbi.githubusers.views

import android.os.Bundle
import android.os.Parcelable
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import idrisadetunmbi.githubusers.R
import idrisadetunmbi.githubusers.UsersContract
import idrisadetunmbi.githubusers.UsersContract.Data
import idrisadetunmbi.githubusers.adapters.GithubUserAdapter
import idrisadetunmbi.githubusers.models.GithubUser
import idrisadetunmbi.githubusers.models.GithubUsersResponse
import idrisadetunmbi.githubusers.presenters.GithubUsersPresenter
import idrisadetunmbi.githubusers.services.GithubService
import idrisadetunmbi.githubusers.utils.NetworkInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class UsersListActivity : AppCompatActivity(), UsersContract.View {
    private var mAdapter: GithubUserAdapter? = null
    private var mRecyclerView: RecyclerView? = null
    private var mProgressBar: ProgressBar? = null
    private var mSwipeRefreshLayout: SwipeRefreshLayout? = null
    private var mUserActionsListener: UsersContract.ActionsListener? = null
    private var mUserItemListener: UserItemListener = object : UserItemListener {
        override fun onUserItemClick(user: GithubUser) {
            mUserActionsListener!!.openDetailsView(user)
        }
    }
    private var mData: Data? = null

    override fun deviceIsConnected(): Boolean {
        return NetworkInfo.isOnline(this)
    }

    override fun showNetworkUnavailabilitySnackbar() {
        Snackbar.make(findViewById(R.id.activity_users_list_fl_parent_view),
                R.string.snack_bar_msg, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.snack_bar_action_text) { mUserActionsListener?.loadUsers() }.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_list)

        // TODO: Abstract and replace with injection
        mData = object : Data {
            override fun getUsers(callback: Data.Callback<List<GithubUser>>) {
                GithubService()
                        .api
                        .users
                        .enqueue(object : Callback<GithubUsersResponse> {
                            override fun onResponse(call: Call<GithubUsersResponse>,
                                                    response: Response<GithubUsersResponse>) {
                                val data = response.body()
                                data?.githubUsers ?: callback.onLoaded(data?.githubUsers!!)

                            }

                            override fun onFailure(call: Call<GithubUsersResponse>, t: Throwable) {

                            }
                        })
            }
        }

        mUserActionsListener = GithubUsersPresenter(this, this.mData!!)

        mRecyclerView = findViewById(R.id.users_list_rv)
        mAdapter = GithubUserAdapter(mUserItemListener, ArrayList(0))
        mRecyclerView!!.layoutManager = GridLayoutManager(this, 3)
        mRecyclerView!!.adapter = mAdapter

        setUpSwipeRefresh()
        mProgressBar = findViewById(R.id.activity_users_list_pb_loading_users)

        if (savedInstanceState == null) {
            mUserActionsListener!!.loadUsers()
        }
    }

    override fun setProgressIndicator(isLoading: Boolean) {
        mProgressBar!!.visibility = if (isLoading) View.VISIBLE else View.GONE
        mRecyclerView!!.visibility = if (isLoading) View.GONE else View.VISIBLE
        if (mSwipeRefreshLayout!!.isRefreshing) {
            mSwipeRefreshLayout!!.isRefreshing = isLoading
        }
    }

    override fun showUsers(users: List<GithubUser>) {
        mAdapter!!.githubUsers = users
        mAdapter!!.notifyDataSetChanged()
    }

    override fun showUserDetailsUi(user: GithubUser) {
        val intent = DetailActivity.newIntent(this, user)
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(USERS_LIST_KEY,
                mAdapter!!.githubUsers as ArrayList<out Parcelable>)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        mAdapter!!.githubUsers = savedInstanceState
                .getParcelableArrayList(USERS_LIST_KEY)
    }

    private fun setUpSwipeRefresh() {
        mSwipeRefreshLayout = findViewById(R.id.activity_users_list_swipe_refresh)
        mSwipeRefreshLayout!!.setOnRefreshListener { mUserActionsListener!!.loadUsers() }
    }

    interface UserItemListener {
        fun onUserItemClick(user: GithubUser)
    }

    companion object {

        const val USERS_LIST_KEY = "ADAPTER_LIST_STATE"
    }
}
