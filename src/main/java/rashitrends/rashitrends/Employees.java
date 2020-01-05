package rashitrends.rashitrends;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;
import android.widget.ImageView;

@Entity(tableName = "EmployeesTable")
public class Employees {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    //private Bitmap profile_image;

    private long phone_Number;

    private String email;

    private String address;

    private String since;

    public void setId(int id) {
        this.id = id;
    }

    public Employees(String name, long phone_Number, String email, String address, String since) {
        this.name = name;
        this.phone_Number = phone_Number;
        this.email = email;
        this.address = address;
        this.since = since;
        //this.profile_image = profile_image;
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

    //public Bitmap getProfile_image() {
        //return profile_image;
    //}
}
