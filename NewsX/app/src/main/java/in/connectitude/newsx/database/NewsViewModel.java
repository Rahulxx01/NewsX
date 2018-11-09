package in.connectitude.newsx.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Database;
import android.graphics.Movie;
import android.support.annotation.NonNull;

import java.util.List;

import in.connectitude.newsx.model.NewsSources;

public class NewsViewModel extends AndroidViewModel {

    private LiveData<List<NewsSources>> newsList;


    public LiveData<List<NewsSources>> getNewsList() {
        return newsList;
    }

    public NewsViewModel(@NonNull Application application) {

        super(application);
        NewsDatabase newsDatabase =  NewsDatabase.getInstance(this.getApplication());
        newsList = newsDatabase.newsDao().loadAllNews();
    }
}
