package idrisadetunmbi.githubusers

import idrisadetunmbi.githubusers.models.GithubUser

interface UsersContract {
    interface View {
        fun setProgressIndicator(isLoading: Boolean)

        fun showUsers(users: List<GithubUser>)

        fun showUserDetailsUi(user: GithubUser)

        fun deviceIsConnected(): Boolean

        fun showNetworkUnavailabilitySnackbar()
    }

    interface ActionsListener {
        fun loadUsers()

        fun openDetailsView(user: GithubUser)
    }

    interface Data {
        fun getUsers(callback: Callback<List<GithubUser>>)

        interface Callback<T> {
            fun onLoaded(data: T)
        }
    }
}
