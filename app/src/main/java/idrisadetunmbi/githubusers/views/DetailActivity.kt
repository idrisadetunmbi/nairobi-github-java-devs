package idrisadetunmbi.githubusers.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import idrisadetunmbi.githubusers.R
import idrisadetunmbi.githubusers.models.GithubUser

class DetailActivity : AppCompatActivity() {
    private var mUser: GithubUser? = null

    private var mUsernameTextView: TextView? = null
    private var mOrganizationTextView: TextView? = null
    private var mUserAvatarImageView: ImageView? = null
    private var mShareProfileBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        mUser = intent.getParcelableExtra(EXTRA_GITHUB_USER)

        mUsernameTextView = findViewById(R.id.activity_user_details_tv_username)
        mOrganizationTextView = findViewById(R.id.activity_user_details_tv_organization)
        mUserAvatarImageView = findViewById(R.id.activity_user_details_iv_user_avatar)

        mUsernameTextView!!.text = mUser!!.username
        mOrganizationTextView!!.text = mUser!!.organization
        Glide.with(this).load(mUser!!.avatarUrl).into(mUserAvatarImageView!!)
        setUpShareBtn()
    }

    private fun setUpShareBtn() {
        mShareProfileBtn = findViewById(R.id.activity_user_details_btn_share_profile)
        mShareProfileBtn!!.setOnClickListener {
            val content = StringBuilder("Check out this awesome developer @")
                    .append(mUser!!.username)
                    .append(", ")
                    .append(mUser!!.profileUrl)
                    .toString()
            val intent = Intent()
                    .setAction(Intent.ACTION_SEND)
                    .putExtra(Intent.EXTRA_TEXT, content)
                    .setType("text/plain")
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
    }

    companion object {

        const val EXTRA_GITHUB_USER = "idrisadetunmbi.githubusers.user_index"

        fun newIntent(context: Context, user: GithubUser): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_GITHUB_USER, user)
            return intent
        }
    }
}
