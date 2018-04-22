package uk.ac.lincoln.yorkminster;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

public class databaseInit {
    public static void populate(@NonNull final database db) {
        PopulateDb task = new PopulateDb(db);
        task.execute();
    }

    private static class PopulateDb extends AsyncTask<Void, Void, Void> {

        private final database mDb;

        PopulateDb(database db) {
            mDb = db;
        }

        private static void addChest(final database db, chestEntity chest) {
            db.chestDao().insertChest(chest);
        }

        private static void populateWithData(database db) {
            chestEntity chest = new chestEntity(
                    "Nave",
                    "Stand at the west doors and look down the Nave. It was built 1291-1360, so it’s more than 600 years old. The statue behind you is St Peter, the patron saint of York Minster. The “Keys of Heaven” are his symbol. Look out for red shields with crossed keys around the Minster. The statue you found is St Peter. He is the patron saint of York Minster. The full legal title of York Minster is “The Cathedral and Metropolitical Church of Saint Peter in York.” The Minster has been dedicated to St Peter ever since the first Minster was built in 627AD. His feast day is the 29th June. The statue itself  dates from 1906.",
                    53.226762,
                    -0.547793,
                    6
            );
            addChest(db, chest);
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithData(mDb);
            return null;
        }
    }
}
