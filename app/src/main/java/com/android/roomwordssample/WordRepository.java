package com.android.roomwordssample;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<PagedList<Word>> mAllWords;

    public WordRepository(Application application){
        WordRoomDatabase mDb = WordRoomDatabase.getDatabaseInstance(application);
        mWordDao = mDb.wordDao();
        // this LivePagedListBuilder is used to build pagedlist livedata
        mAllWords = new LivePagedListBuilder<>(mWordDao.getAllWords(), 10).build();
    }

    public LiveData<PagedList<Word>> getAllWords(){
        return mAllWords;
    }

    public void insert(Word word){
        new InsertAsyncTask(mWordDao).execute(word);
    }

    private static class InsertAsyncTask extends AsyncTask<Word, Void, Void>{
        private WordDao mAsyncTaskWordDao;
        private InsertAsyncTask(WordDao wordDao){
            mAsyncTaskWordDao = wordDao;
        }
        @Override
        protected Void doInBackground(Word... words) {
            Word wordToInsert = words[0];
            mAsyncTaskWordDao.insert(wordToInsert);
            return null;
        }
    }

}
