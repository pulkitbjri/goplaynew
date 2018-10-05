package play.com.play.Modals;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Tournaments implements Parcelable {
    private  String id,name,address,image,sport_type;

    protected Tournaments(Parcel in) {
        id = in.readString();
        name = in.readString();
        address = in.readString();
        image = in.readString();
        sport_type = in.readString();
    }

    public static final Creator<Tournaments> CREATOR = new Creator<Tournaments>() {
        @Override
        public Tournaments createFromParcel(Parcel in) {
            return new Tournaments(in);
        }

        @Override
        public Tournaments[] newArray(int size) {
            return new Tournaments[size];
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSport_type() {
        return sport_type;
    }

    public void setSport_type(String sport_type) {
        this.sport_type = sport_type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(image);
        dest.writeString(sport_type);
    }
}
