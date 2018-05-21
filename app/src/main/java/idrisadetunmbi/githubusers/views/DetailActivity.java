package idrisadetunmbi.githubusers.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import idrisadetunmbi.githubusers.R;
import idrisadetunmbi.githubusers.models.GithubUser;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_USER_INDEX = "idrisadetunmbi.githubusers.user_index";
    private GithubUser mUser;

    private TextView mUsernameTextView;
    private TextView mOrganizationTextView;
    private ImageView mUserAvatarImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        mUser = getIntent().getParcelableExtra(EXTRA_USER_INDEX);

        mUsernameTextView = findViewById(R.id.activity_user_details_tv_username);
        mOrganizationTextView = findViewById(R.id.activity_user_details_tv_organization);
        mUserAvatarImageView = findViewById(R.id.activity_user_details_iv_user_avatar);

        mUsernameTextView.setText(mUser.getUsername());
        mOrganizationTextView.setText(mUser.getOrganization());
        Glide.with(this).load(mUser.getAvatarUrl()).into(mUserAvatarImageView);
    }

    public static Intent newIntent(Context context, GithubUser user) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_USER_INDEX, user);
        return intent;
    }
}
