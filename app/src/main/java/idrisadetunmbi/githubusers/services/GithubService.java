package idrisadetunmbi.githubusers.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubService {

    private Retrofit mRetrofit = null;

    public GithubApi getAPI() {
        final String BASE_URL = "https://api.github.com/";
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit.create(GithubApi.class);
    }
}
