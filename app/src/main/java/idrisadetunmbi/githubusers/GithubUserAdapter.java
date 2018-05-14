package idrisadetunmbi.githubusers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import idrisadetunmbi.githubusers.models.GithubUser;

public class GithubUserAdapter extends RecyclerView.Adapter<GithubUserAdapter.UserViewHolder> {

    private List<GithubUser> mGithubUsers;

    public GithubUserAdapter(List<GithubUser> githubUsers) {
        mGithubUsers = githubUsers;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mUserImageView;
        private TextView mUserNameTextView;

        public UserViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            mUserImageView = view.findViewById(R.id.users_profile_image);
            mUserNameTextView = view.findViewById(R.id.users_full_name);
        }

        @Override
        public void onClick(View v) {
            Intent intent = DetailActivity.newIntent(v.getContext(), getAdapterPosition());
            v.getContext().startActivity(intent);
        }

        void bind(GithubUser user) {
            mUserNameTextView.setText(user.getUsername());
        }
    }

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
        // since we don't have a list of data to display yet
        return mGithubUsers.size();
    }
}
