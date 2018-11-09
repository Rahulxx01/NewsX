package in.connectitude.newsx.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.graphics.Movie;

import java.util.List;

import in.connectitude.newsx.model.NewsSources;
import lombok.NonNull;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news_favourites")
    LiveData<List<NewsSources>> loadAllNews();

    @Insert
    void insertNews(NewsSources news);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateNews(NewsSources news);

    @Query("DELETE FROM news_favourites WHERE title= :title")
    void deleteNews(String title);

    @Query("SELECT * FROM news_favourites WHERE title = :title")
    LiveData<List<NewsSources>> loadNewsByTitle(String title);


    @Query("SELECT * FROM news_favourites WHERE title= :title")
    boolean checkIfNewsExists(String title);

}
