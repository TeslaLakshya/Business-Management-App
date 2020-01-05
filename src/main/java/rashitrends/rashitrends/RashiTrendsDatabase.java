package rashitrends.rashitrends;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Items.class, Orders.class, Employees.class, Customers.class}, version = 1)
public abstract class RashiTrendsDatabase extends RoomDatabase {

    private static RashiTrendsDatabase instance;

    abstract public ItemDao getItemDao();
    abstract public EmployeeDao getEmployeeDao();
    abstract public OrderDao getOrderDao();
    abstract public CustomerDao getCustomerDao();
    public static RashiTrendsDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context, RashiTrendsDatabase.class, "RashiTrendsDatabase")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
