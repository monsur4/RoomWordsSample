package com.android.roomwordssample;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {
    private static WordRoomDatabase sWordRoomDatabase;

    public abstract WordDao wordDao();

    public static WordRoomDatabase getDatabaseInstance(Context context) {
        if (sWordRoomDatabase == null) {
            synchronized (WordRoomDatabase.class) {
                if(sWordRoomDatabase == null) {
                    sWordRoomDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sCallback)
                            .build();
                }
            }
        }
        return sWordRoomDatabase;
    }

    private static RoomDatabase.Callback sCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsyncTask(sWordRoomDatabase).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private final WordDao mWordDao;
        String[] words = {"dolphin", "crocodile", "cobra"};

        public PopulateDbAsyncTask(WordRoomDatabase db) {
            mWordDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            mWordDao.deleteAll();
            for (int i = 0; i <= words.length - 1; i++) {
                Word word = new Word(words[i]);
                mWordDao.insert(word);
            }
            return null;
        }
    }
}
