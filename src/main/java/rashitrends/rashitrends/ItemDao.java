package rashitrends.rashitrends;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.content.ClipData;

import java.util.List;

@Dao
public interface ItemDao {

    @Insert
    public void insert(Items item);

    @Delete
    public void delete(Items item);

    @Update
    public void update(Items item);

    @Query("SELECT * FROM Items")
    public LiveData<List<Items>> getAllItems();
}
