package idrisadetunmbi.githubusers.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import idrisadetunmbi.githubusers.R;
import idrisadetunmbi.githubusers.models.GithubUser;
import idrisadetunmbi.githubusers.views.DetailActivity;

public class GithubUserAdapter extends RecyclerView.Adapter<GithubUserAdapter.UserViewHolder> {

    private List<GithubUser> mGithubUsers = new ArrayList<>();

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View userDetailsRowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_details, parent, false);
        return new UserViewHolder(userDetailsRowView);
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

        UserViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            mUserImageView = view.findViewById(R.id.users_profile_image);
            mUserNameTextView = view.findViewById(R.id.users_full_name);
        }

        @Override
        public void onClick(View v) {
            Intent intent = DetailActivity.newIntent(v.getContext(), mUser);
            v.getContext().startActivity(intent);
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
