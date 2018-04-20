package uk.ac.lincoln.yorkminster;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {chestEntity.class}, version = 1)
public abstract class database extends RoomDatabase {
    private static volatile database INSTANCE;

    public abstract chestDao chestDao();

    public static database getInstance(Context context){
        if (INSTANCE == null){
            synchronized (database.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            database.class,"appDatabase.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
