package play.com.play.Modals;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Players implements Parcelable {
    private  String id,name,profile_pic,is_active,player_id;

    protected Players(Parcel in) {
        id = in.readString();
        name = in.readString();
        profile_pic = in.readString();
        is_active = in.readString();
        player_id = in.readString();
    }

    public static final Creator<Players> CREATOR = new Creator<Players>() {
        @Override
        public Players createFromParcel(Parcel in) {
            return new Players(in);
        }

        @Override
        public Players[] newArray(int size) {
            return new Players[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(String player_id) {
        this.player_id = player_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(profile_pic);
        dest.writeString(is_active);
        dest.writeString(player_id);
    }
}
