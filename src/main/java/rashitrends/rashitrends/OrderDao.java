package rashitrends.rashitrends;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface OrderDao {

    @Insert
    public void insert(Orders order);

    @Update
    public void update(Orders order);

    @Delete
    public void delete(Orders order);

    @Query("SELECT * FROM Orders")
    public LiveData<List<Orders>> getAllOrders();


}
