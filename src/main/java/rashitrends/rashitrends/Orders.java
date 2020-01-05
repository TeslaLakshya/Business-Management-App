package rashitrends.rashitrends;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Orders")
public class Orders {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String product_name;

    private String customer_name;

    private double prooduct_price;

    private boolean state;

    private double ifPendingPrice;

    public void setId(int id) {
        this.id = id;
    }

    public Orders(String product_name, String customer_name, double prooduct_price, boolean state,
                  double ifPendingPrice) {
        this.product_name = product_name;
        this.customer_name = customer_name;
        this.prooduct_price = prooduct_price;
        this.state = state;
        this.ifPendingPrice = ifPendingPrice;
    }

    public int getId() {
        return id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public double getProoduct_price() {
        return prooduct_price;
    }

    public boolean isState() {
        return state;
    }

    public double getIfPendingPrice() {
        return ifPendingPrice;
    }
}
