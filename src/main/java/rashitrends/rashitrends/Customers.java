package rashitrends.rashitrends;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;

@Entity
public class Customers {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private long phone_Number;

    private byte[] profile_image;

    private String email;

    private String address;

    private String since;

    public void setId(int id) {
        this.id = id;
    }

    public Customers(String name, long phone_Number, String email, String address, String since,
                     byte[] profile_image) {
        this.name = name;
        this.phone_Number = phone_Number;
        this.email = email;
        this.address = address;
        this.since = since;
        this.profile_image = profile_image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPhone_Number() {
        return phone_Number;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getSince() {
        return since;
    }

    public byte[] getProfile_image() {
        return profile_image;
    }
}
