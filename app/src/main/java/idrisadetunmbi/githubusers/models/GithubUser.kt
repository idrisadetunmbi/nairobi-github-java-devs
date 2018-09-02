package idrisadetunmbi.githubusers.models

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

open class GithubUser : Parcelable {
    @SerializedName("login")
    var username: String? = null
    @SerializedName("organizations_url")
    var organization: String? = null
    @SerializedName("avatar_url")
    var avatarUrl: String? = null
    @SerializedName("html_url")
    var profileUrl: String? = null

    constructor()

    protected constructor(`in`: Parcel) {
        this.username = `in`.readString()
        this.organization = `in`.readString()
        this.avatarUrl = `in`.readString()
        this.profileUrl = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.username)
        dest.writeString(this.organization)
        dest.writeString(this.avatarUrl)
        dest.writeString(this.profileUrl)
    }

    companion object {

        val CREATOR: Parcelable.Creator<GithubUser> = object : Parcelable.Creator<GithubUser> {
            override fun createFromParcel(source: Parcel): GithubUser {
                return GithubUser(source)
            }

            override fun newArray(size: Int): Array<GithubUser?> {
                return arrayOfNulls(size)
            }
        }
    }
}
