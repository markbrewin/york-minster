package uk.ac.lincoln.yorkminster;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;

public class databaseInit {
    database appDatabase = new database() {
        @Override
        public chestDao chestDao() {
            return null;
        }

        @Override
        protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
            return null;
        }

        @Override
        protected InvalidationTracker createInvalidationTracker() {
            return null;
        }
    };


}
