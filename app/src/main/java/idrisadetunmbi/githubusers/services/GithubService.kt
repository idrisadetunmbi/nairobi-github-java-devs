package idrisadetunmbi.githubusers.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubService {

    private var mRetrofit: Retrofit? = null

    val api: GithubApi
        get() {
            val BASE_URL = "https://api.github.com/"
            if (mRetrofit == null) {
                mRetrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return mRetrofit!!.create(GithubApi::class.java)
        }
}
