package rashitrends.rashitrends;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CustomerDao {

    @Insert
    public void insert(Customers customer);

    @Delete
    public void delete(Customers customer);

    @Update
    public void update(Customers customer);

    @Query("SELECT * FROM EmployeesTable")
    public LiveData<List<Customers>> getAllCustomers();
}
