package idrisadetunmbi.githubusers.presenters

import idrisadetunmbi.githubusers.UsersContract.Data
import idrisadetunmbi.githubusers.UsersContract.Data.Callback
import idrisadetunmbi.githubusers.UsersContract.View
import idrisadetunmbi.githubusers.models.GithubUser
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.runners.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class GithubUsersPresenterTests {
    @Mock private lateinit var mView: View

    @Mock private lateinit var mData: Data

    @Captor private lateinit var mCallbackArgumentCaptor: ArgumentCaptor<Callback<List<GithubUser>>>

    private lateinit var mPresenter: GithubUsersPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mPresenter = GithubUsersPresenter(mView, mData)
    }

    @Test fun loadUsersFromApiAndLoadIntoViewWithUserOnline() {
        Mockito.`when`(mView.deviceIsConnected()).thenReturn(true)

        mPresenter.loadUsers()
        Mockito.verify(mView).deviceIsConnected()
        Mockito.verify(mView).setProgressIndicator(true)
        Mockito.verify<Data>(mData).getUsers(mCallbackArgumentCaptor.capture())
        mCallbackArgumentCaptor.value.onLoaded(ArrayList())
        Mockito.verify<View>(mView).setProgressIndicator(false)
        Mockito.verify<View>(mView).showUsers(ArrayList())
    }

    @Test fun loadUsersCallsViewDisplaySnackbarWithNetworkUnavailable() {
        Mockito.`when`(mView.deviceIsConnected()).thenReturn(false)

        mPresenter.loadUsers()
        Mockito.verify<View>(mView).deviceIsConnected()
        Mockito.verify<View>(mView).setProgressIndicator(false)
        Mockito.verify<View>(mView).showNetworkUnavailabilitySnackbar()

    }

    @Test fun clickOnUserItemShowsDetailUi() {
        val user = GithubUser()
        mPresenter.openDetailsView(user)
        Mockito.verify<View>(mView).showUserDetailsUi(user)
    }
}
