package uk.ac.lincoln.yorkminster;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface chestDao {
    @Query("SELECt * FROM chests")
    List<chestEntity> getChests();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertChest(chestEntity chest);

    @Query("DELETE FROM chests")
    void deleteALlChests();
}