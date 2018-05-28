package idrisadetunmbi.githubusers.presenters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import idrisadetunmbi.githubusers.UsersContract;
import idrisadetunmbi.githubusers.models.GithubUser;

@RunWith(MockitoJUnitRunner.class)
public class GithubUsersPresenterTests {
    @Mock
    private UsersContract.View mView;

    @Mock
    private UsersContract.Data mData;

    @Captor
    private ArgumentCaptor<UsersContract.Data.Callback<List<GithubUser>>> mCallbackArgumentCaptor;

    private GithubUsersPresenter mPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mPresenter = new GithubUsersPresenter(mView, mData);
    }

    @Test
    public void loadUsersFromApiAndLoadIntoViewWithUserOnline() {
        Mockito.when(mView.userIsOnline()).thenReturn(true);

        mPresenter.loadUsers();
        Mockito.verify(mView).userIsOnline();
        Mockito.verify(mView).setProgressIndicator(true);
        Mockito.verify(mData).getUsers(mCallbackArgumentCaptor.capture());
        mCallbackArgumentCaptor.getValue().onLoaded(new ArrayList<GithubUser>());
        Mockito.verify(mView).setProgressIndicator(false);
        Mockito.verify(mView).showUsers(new ArrayList<GithubUser>());
    }

    @Test
    public void loadUsersCallsViewDisplaySnackbarWithNetworkUnavailable() {
        Mockito.when(mView.userIsOnline()).thenReturn(false);

        mPresenter.loadUsers();
        Mockito.verify(mView).userIsOnline();
        Mockito.verify(mView).setProgressIndicator(false);
        Mockito.verify(mView).showNetworkUnavailabilitySnackbar();

    }

    @Test
    public void clickOnUserItemShowsDetailUi() {
        GithubUser user = new GithubUser();
        mPresenter.openDetailsView(user);
        Mockito.verify(mView).showUserDetailsUi(user);
    }
}
