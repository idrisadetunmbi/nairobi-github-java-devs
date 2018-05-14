package idrisadetunmbi.githubusers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import idrisadetunmbi.githubusers.models.GithubUser;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_USER_INDEX = "idrisadetunmbi.githubusers.user_index";
    private GithubUser mUser;

    private TextView mUsernameTextView;
    private TextView mOrganizationTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        int userIndex = (int) getIntent().getSerializableExtra(EXTRA_USER_INDEX);
        mUser = UsersListActivity.getGithubUsers().get(userIndex);

        mUsernameTextView = findViewById(R.id.activity_user_details_tv_username);
        mOrganizationTextView = findViewById(R.id.activity_user_details_tv_organization);

        mUsernameTextView.setText(mUser.getUsername());
        mOrganizationTextView.setText(mUser.getOrganization());


    }

    public static Intent newIntent(Context context, int userIndex) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_USER_INDEX, userIndex);
        return intent;
    }
}
