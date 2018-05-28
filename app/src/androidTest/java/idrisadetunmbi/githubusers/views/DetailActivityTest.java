package idrisadetunmbi.githubusers.views;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import idrisadetunmbi.githubusers.R;
import idrisadetunmbi.githubusers.models.GithubUser;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    private static final String USER_USERNAME = "janedoe";

    private static final String USER_ORGANIZATION = "organization";
    private static final String USER_AVATAR_URL = null;
    private static final String USER_PROFILE_URL = "profile_url";
    private static final GithubUser GITHUB_USER = new GithubUser();


    @Rule
    public ActivityTestRule<DetailActivity> mDetailActivityTestRule = new
            ActivityTestRule<>(DetailActivity.class, true, false);

    @Before
    public void intentWithStubbedUser() {
        GITHUB_USER.setUsername(USER_USERNAME);
        GITHUB_USER.setAvatarUrl(USER_AVATAR_URL);
        GITHUB_USER.setOrganization(USER_ORGANIZATION);
        GITHUB_USER.setProfileUrl(USER_PROFILE_URL);

        Intent startIntent = new Intent();
        startIntent.putExtra(DetailActivity.EXTRA_GITHUB_USER, GITHUB_USER);

        mDetailActivityTestRule.launchActivity(startIntent);
    }

    @Test
    public void userDetailsDisplayedInUi() {
        onView(withId(R.id.activity_user_details_tv_username))
                .check(matches(withText(USER_USERNAME)));
        onView(withId(R.id.activity_user_details_tv_organization))
                .check(matches(withText(USER_ORGANIZATION)));

    }
}
