package idrisadetunmbi.githubusers;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class UsersListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        mRecyclerView = findViewById(R.id.users_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new GithubUserAdapter());
    }
}

class GithubUserAdapter extends RecyclerView.Adapter<GithubUserAdapter.UserViewHolder> {

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView mUserImageView;
        private TextView mUserNameTextView;

        public UserViewHolder(View view) {
            super(view);
            mUserImageView = view.findViewById(R.id.users_profile_image);
            mUserNameTextView = view.findViewById(R.id.users_full_name);
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
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {}

    @Override
    public int getItemCount() {
        // since we don't have a list of data to display yet
        return 1;
    }
}
