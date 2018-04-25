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
        private static void addKey(final database db, keyEntity key){
            db.keyDao().insertKey(key);
        }

        private static void populateWithData(database db) {
            //NAVE
            chestEntity chest = new chestEntity(
                    1,
                    "Nave",
                    "Stand at the west doors and look down the Nave. It was built 1291-1360, so it’s more than 600 years old. The statue behind you is St Peter, the patron saint of York Minster. The “Keys of Heaven” are his symbol. Look out for red shields with crossed keys around the Minster. The statue you found is St Peter. He is the patron saint of York Minster. The full legal title of York Minster is “The Cathedral and Metropolitical Church of Saint Peter in York.” The Minster has been dedicated to St Peter ever since the first Minster was built in 627AD. His feast day is the 29th June. The statue itself  dates from 1906.",
                    53.221256,-0.54244572
            );
            addChest(db, chest);

            //THE STRIKING CLOCK
            chest = new chestEntity(
                    2,
                    "The Striking Clock",
                    "The Striking Clock has two oak figures called “quarterjacks”. They strike the bars every 15 minutes. The figures were made in Tudor times. To see the figures move, wait for the next quarter. This detail is on the monument to Rear Admiral Christopher Craddock. He died when his ship HMS Good Hope was sunk in a naval battle during the first World War.   At the time his bravery was considered heroic and he was even compared to the great Lord Nelson. However, had he survived he may have had to face a court martial for risking his squadron.",
                    0.1,
                    0.5
            );
            addChest(db, chest);

            //THE DRAGON
            chest = new chestEntity(
                    3,
                    "The Dragon",
                    "Watch out for The Dragon above your head! It is on a pivot. It was probably used to lift a heavy font lid in medieval times. A font holds the water for baptism. Turn to look at the Great West Window at the end of the Nave. Can you see why its nickname is “the heart of Yorkshire”? This carved roof boss depicts the ascension of Christ into Heaven. This is a copy of an original medieval boss and shows the bottom of Jesus' feet as he goes upwards into Heaven. The original boss and the entire Nave roof and vault were destroyed  by a fire in 1840.  Fortunately, only a few years earlier, an artist called John Brown had made very accurate drawings of the details in the Minster.",
                    0.1,
                    0.5
            );
            addChest(db, chest);

            //THE FIDDLER
            chest = new chestEntity(
                    4,
                    "The Fiddler",
                    "Find the fiddler on the wall. Some people think that this was “Pirate” Blackburne’s shantyman! More likely it is Mr Camidge, the Minster organist 200 years ago. It was originally on the South Transept roof. Your treasure is hidden to the left if you are looking at the fiddler. Hopefully this wasn’t too difficult to find, although people we have asked think it is one of the trickiest. The figure is of Judas Maccabeus who was a famous warrior leader of the Jews before Jesus was born. That is why he is on this military monument. To be honest we asked you to find it because we loved the face!",
                    0.1,
                    0.5
            );
            addChest(db, chest);

            //SOUTH TRANSEPT AND ROSE WINDOW
            chest = new chestEntity(
                    5,
                    "South Transept and Rose Window",
                    "Look towards the South Transept. In 1984 fire destroyed the roof of the South Transept. New round wooden carvings called “bosses” decorate the ceiling. Six were designed by children who won the BBC Blue Peter competition after the fire. They are the coloured carvings which you can see along the two sides of the vaulting. Look up at the round Rose Window. Around the edge are red and white (Tudor) and red (Lancastrian) roses. This Tudor glass was badly cracked and blackened in the blaze, but has been carefully restored. This roof boss was designed by Rebecca Rose Welsh when she was 6 years old as part of the 1986 Blue Peter competition held after the fire of 1984.  In July 2009 a service was held to mark 25 years since the fire. We managed to trace some of the competition winners and they were invited to the service. If you want to know more about the fire find our 1984 Fire fact sheet in the “Resources “ section of the website.",
                    0.1,
                    0.5
            );
            addChest(db, chest);

            //NORTH QUIRE AISLE
            chest = new chestEntity(
                    6,
                    "North Quire Aisle",
                    "You are now in the North Quire Aisle. The window in the picture is The Saint William Window; can you see it? St William is York Minster’s very own saint. William died in suspicious circumstances; some say he may have been murdered. After his death many miracles were reported at his tomb which is why he was made a saint in 1227. This window tells the story of William’s life, his career in the Church and the miracles that occurred after his death. This head is of St Edward the Confessor, he is often shown holding a ring. Legend has it that on his was to a chapel dedicated to St John, Edward was asked for help by a beggar Edward had no money and so gave the beggar his ring. Years later two men became stranded in the Holy land and were helped by an old man who was carrying a ring. The old man asked them to return it to Edward telling him that in six months time he would meet St John in heaven.",
                    0.1,
                    0.5
            );
            addChest(db, chest);

            //CATHEDRA
            chest = new chestEntity(
                    7,
                    "Cathedra",
                    "This is where daily Evensong takes place. The carved wood and furniture are only 180 years old - a fire in 1829 destroyed the medieval woodwork and the roof. This huge wooden chair is the “cathedra” - or the seat for the archbishop. The Latin word gives us the name “cathedral”. This lion is one of the feet of the eagle‐shaped lectern that holds the Bible in the Quire. Each day at Evensong the bible is read from it. For many years this lectern was in the Nave. It was originally given to the Minster by Thomas Cracroft in 1686. It is made of brass and is said to weigh over one tonne.",
                    0.1,
                    0.5
            );
            addChest(db, chest);

            //CENTRAL TOWER
            chest = new chestEntity(
                    8,
                    "Central Tower",
                    "You are under the Central Tower, so look up! It’s 60 metres high, weighing 16,000 tons; about the same weight as 40 jumbo jets! It was finished in 1472. The beautiful seated figure over the gateway into the Quire is of “Christ in Majesty”. This way of depicting Christ is often used and portrays him as the King of Heaven. On the Quire Screen this belief is reinforced by the fact that Christ is seated above the carved Kings of England from William I to Henry VI.",
                    0.1,
                    0.5
            );
            addChest(db, chest);

            //THE CHAPTER HOUSE
            chest = new chestEntity(
                    9,
                    "The Chapter House",
                    "The Chapter House. Completed around 1280 this amazing structure was built to hold meetings of the Dean & Chapter, the Minster’s government. See if you can find the Dean’s stall and don’t forget to look for the treasure. In the Bible John the Baptist referred to Jesus with the words “Behold the Lamb of  God that takes away the sin of the world”. This type of image on the roof boss you found is called an “Agnus Dei” and is a way of representing Jesus that has been used in art, glass carving and sculpture since the Middle Ages.",
                    0.1,
                    0.5
            );
            addChest(db, chest);

            //FIVE SISTERS WINDOW
            chest = new chestEntity(
                    10,
                    "Five Sisters Window",
                    "You are in the North Transept. The Five Sisters Window was made about 750 years ago. The “greyish” glass is called “grisaille”. Look at each window to identify one of these patterns repeated from the top to the bottom. You will have found this detail on the Astronomical Clock. It shows the positions of the stars over York. The clock was made as a memorial to pilots and air crew who lost their lives in the Second World War, flying from bases in the North of England. They would have used the stars to   help with their navigation at night.",
                    0.1,
                    0.5
            );
            addChest(db, chest);


            keyEntity key = new keyEntity(
                    1,"Nave Key", 53.221256,-0.54244572
            );
            addKey(db, key);

            key = new keyEntity(
                    2,"Striking Clock Key", 0.1, 0.5
            );
            addKey(db, key);

            key = new keyEntity(
                    3,"The Dragon Key", 0.1, 0.5
            );
            addKey(db, key);

            key = new keyEntity(
                    4,"The Fiddler Key", 0.1, 0.5
            );
            addKey(db, key);

            key = new keyEntity(
                    5,"South Transept and Rose Window Key", 0.1, 0.5
            );
            addKey(db, key);

            key = new keyEntity(
                    6,"North Quire Key", 0.1, 0.5
            );
            addKey(db, key);

            key = new keyEntity(
                    7,"Cathedra Key", 0.1, 0.5
            );
            addKey(db, key);

            key = new keyEntity(
                    8,"Central Tower Key", 0.1, 0.5
            );
            addKey(db, key);

            key = new keyEntity(
                    9,"The Chapter House Key", 0.1, 0.5
            );
            addKey(db, key);

            key = new keyEntity(
                    10, "Five Sisters Key", 0.1, 0.5
            );
            addKey(db, key);
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithData(mDb);
            return null;
        }
    }
}
