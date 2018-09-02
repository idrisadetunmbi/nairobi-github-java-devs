package idrisadetunmbi.githubusers.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide

import idrisadetunmbi.githubusers.R
import idrisadetunmbi.githubusers.models.GithubUser
import idrisadetunmbi.githubusers.views.UsersListActivity

class GithubUserAdapter(private val mItemListener: UsersListActivity.UserItemListener,
                        var githubUsers: List<GithubUser>?) :
        RecyclerView.Adapter<GithubUserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val userDetailsRowView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user_details, parent, false)
        return UserViewHolder(userDetailsRowView, mItemListener)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = githubUsers!![position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return githubUsers!!.size
    }

    class UserViewHolder internal constructor(view: View,
                                              private val mUserItemListener:
                                              UsersListActivity.UserItemListener) :
            RecyclerView.ViewHolder(view), View.OnClickListener {
        private var mUser: GithubUser? = null
        private val mUserImageView: ImageView
        private val mUserNameTextView: TextView

        init {
            view.setOnClickListener(this)
            mUserImageView = view.findViewById(R.id.users_profile_image)
            mUserNameTextView = view.findViewById(R.id.users_full_name)
        }

        override fun onClick(v: View) {
            mUserItemListener.onUserItemClick(mUser!!)
        }

        internal fun bind(user: GithubUser) {
            mUser = user
            mUserNameTextView.text = user.username
            Glide.with(itemView.context)
                    .load(user.avatarUrl)
                    .into(mUserImageView)
        }
    }
}
