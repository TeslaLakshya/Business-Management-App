package rashitrends.rashitrends;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface EmployeeDao {

    @Insert
    public void insert(Employees employee);

    @Delete
    public void delete(Employees employee);

    @Update
    public void update(Employees employee);

    @Query("SELECT * FROM EmployeesTable")
    public LiveData<List<Employees>> getAllEmployees();
}
