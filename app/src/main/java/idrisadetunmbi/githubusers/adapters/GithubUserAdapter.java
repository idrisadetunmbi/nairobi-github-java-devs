package idrisadetunmbi.githubusers.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import idrisadetunmbi.githubusers.R;
import idrisadetunmbi.githubusers.models.GithubUser;
import idrisadetunmbi.githubusers.views.UsersListActivity;

public class GithubUserAdapter extends RecyclerView.Adapter<GithubUserAdapter.UserViewHolder> {

    private List<GithubUser> mGithubUsers;
    private UsersListActivity.UserItemListener mItemListener;

    public GithubUserAdapter(UsersListActivity.UserItemListener listener,
                             List<GithubUser> githubUsers) {
        mItemListener = listener;
        mGithubUsers = githubUsers;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View userDetailsRowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_details, parent, false);
        return new UserViewHolder(userDetailsRowView, mItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        GithubUser user = mGithubUsers.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return mGithubUsers.size();
    }

    public List<GithubUser> getGithubUsers() {
        return mGithubUsers;
    }

    public void setGithubUsers(List<GithubUser> githubUsers) {
        mGithubUsers = githubUsers;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private GithubUser mUser;
        private ImageView mUserImageView;
        private TextView mUserNameTextView;
        private UsersListActivity.UserItemListener mUserItemListener;

        UserViewHolder(View view, UsersListActivity.UserItemListener listener) {
            super(view);
            view.setOnClickListener(this);
            mUserImageView = view.findViewById(R.id.users_profile_image);
            mUserNameTextView = view.findViewById(R.id.users_full_name);
            mUserItemListener = listener;
        }

        @Override
        public void onClick(View v) {
            mUserItemListener.onUserItemClick(mUser);
        }

        void bind(GithubUser user) {
            mUser = user;
            mUserNameTextView.setText(user.getUsername());
            Glide.with(itemView.getContext())
                    .load(user.getAvatarUrl())
                    .into(mUserImageView);
        }
    }
}
