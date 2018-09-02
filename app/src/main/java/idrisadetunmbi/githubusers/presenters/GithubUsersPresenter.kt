package idrisadetunmbi.githubusers.presenters

import idrisadetunmbi.githubusers.UsersContract
import idrisadetunmbi.githubusers.models.GithubUser

class GithubUsersPresenter(
        private val mGithubUserView: UsersContract.View,
        private val mData: UsersContract.Data) : UsersContract.ActionsListener {

    override fun loadUsers() {
        if (mGithubUserView.deviceIsConnected()) {
            mGithubUserView.setProgressIndicator(true)
            mData.getUsers(object : UsersContract.Data.Callback<List<GithubUser>> {
                override fun onLoaded(data: List<GithubUser>) {
                    mGithubUserView.setProgressIndicator(false)
                    mGithubUserView.showUsers(data)
                }
            })
        } else {
            mGithubUserView.setProgressIndicator(false)
            mGithubUserView.showNetworkUnavailabilitySnackbar()
        }
    }

    override fun openDetailsView(user: GithubUser) {
        mGithubUserView.showUserDetailsUi(user)
    }
}
