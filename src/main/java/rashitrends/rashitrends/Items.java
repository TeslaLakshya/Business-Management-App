package rashitrends.rashitrends;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.sql.Blob;

@Entity(tableName = "Items")
public class Items {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String item_name;

    private double avg_price;

    private byte[] image;

    private String dimensions;

    private int quantity;

    public Items(String item_name, double avg_price, String dimensions, int quantity, byte[] image) {
        this.item_name = item_name;
        this.avg_price = avg_price;
        this.dimensions = dimensions;
        this.quantity = quantity;
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getItem_name() {
        return item_name;
    }

    public double getAvg_price() {
        return avg_price;
    }

    public String getDimensions() {
        return dimensions;
    }

    public int getQuantity() {
        return quantity;
    }

    public byte[] getImage() {
        return image;
    }
}
