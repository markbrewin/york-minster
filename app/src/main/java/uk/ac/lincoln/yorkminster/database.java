package uk.ac.lincoln.yorkminster;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {chestEntity.class, keyEntity.class}, version = 1)
public abstract class database extends RoomDatabase {

    private static volatile database INSTANCE;

    public abstract chestDao chestDao();
    public abstract keyDao keyDao();

    public static database getAppDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(),database.class)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
