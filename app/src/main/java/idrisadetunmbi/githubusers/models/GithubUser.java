package idrisadetunmbi.githubusers.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class GithubUser implements Parcelable {

    public static final Parcelable.Creator<GithubUser> CREATOR =
            new Parcelable.Creator<GithubUser>() {
        @Override
        public GithubUser createFromParcel(Parcel source) {
            return new GithubUser(source);
        }

        @Override
        public GithubUser[] newArray(int size) {
            return new GithubUser[size];
        }
    };
    @SerializedName("login")
    private String mUsername;
    @SerializedName("organizations_url")
    private String mOrganization;
    @SerializedName("avatar_url")
    private String mAvatarUrl;

    public GithubUser() {
    }

    protected GithubUser(Parcel in) {
        this.mUsername = in.readString();
        this.mOrganization = in.readString();
        this.mAvatarUrl = in.readString();
    }

    public String getUsername() {
        return mUsername;
    }

    public String getOrganization() {
        return mOrganization;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mUsername);
        dest.writeString(this.mOrganization);
        dest.writeString(this.mAvatarUrl);
    }
}
