package play.com.play.Modals;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Grounds implements Parcelable
{
    private  String id,name,address,image,is_active,owner;

    protected Grounds(Parcel in) {
        id = in.readString();
        name = in.readString();
        address = in.readString();
        image = in.readString();
        is_active = in.readString();
        owner = in.readString();
    }

    public static final Creator<Grounds> CREATOR = new Creator<Grounds>() {
        @Override
        public Grounds createFromParcel(Parcel in) {
            return new Grounds(in);
        }

        @Override
        public Grounds[] newArray(int size) {
            return new Grounds[size];
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

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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
        dest.writeString(is_active);
        dest.writeString(owner);
    }
}
