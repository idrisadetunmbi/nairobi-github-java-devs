package idrisadetunmbi.githubusers.views

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import idrisadetunmbi.githubusers.R
import idrisadetunmbi.githubusers.models.GithubUser

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText

@RunWith(AndroidJUnit4::class)
class DetailActivityTest {


    @Rule
    var mDetailActivityTestRule = ActivityTestRule(DetailActivity::class.java, true, false)

    @Before
    fun intentWithStubbedUser() {
        GITHUB_USER.username = USER_USERNAME
        GITHUB_USER.avatarUrl = USER_AVATAR_URL
        GITHUB_USER.organization = USER_ORGANIZATION
        GITHUB_USER.profileUrl = USER_PROFILE_URL

        val startIntent = Intent()
        startIntent.putExtra(DetailActivity.EXTRA_GITHUB_USER, GITHUB_USER)

        mDetailActivityTestRule.launchActivity(startIntent)
    }

    @Test
    fun userDetailsDisplayedInUi() {
        onView(withId(R.id.activity_user_details_tv_username))
                .check(matches(withText(USER_USERNAME)))
        onView(withId(R.id.activity_user_details_tv_organization))
                .check(matches(withText(USER_ORGANIZATION)))

    }

    companion object {

        private const val USER_USERNAME = "janedoe"

        private const val USER_ORGANIZATION = "organization"
        private val USER_AVATAR_URL: String? = null
        private const val USER_PROFILE_URL = "profile_url"
        private val GITHUB_USER = GithubUser()
    }
}
