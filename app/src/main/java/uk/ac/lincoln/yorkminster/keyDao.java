package uk.ac.lincoln.yorkminster;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface keyDao {
    @Query("SELECt * FROM keys")
    List<keyEntity> getKeys();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertKey(keyEntity key);

    @Query("DELETE FROM keys")
    void deleteAllKeys();
}