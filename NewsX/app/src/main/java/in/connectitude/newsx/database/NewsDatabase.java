package in.connectitude.newsx.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.graphics.Movie;
import android.util.Log;

import in.connectitude.newsx.R;
import in.connectitude.newsx.model.NewsSources;

@Database(entities = {NewsSources.class},version = 1,exportSchema = false)
public abstract class NewsDatabase extends RoomDatabase {
    private static final String LOG_TAG = NewsDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "news_database";
    private static NewsDatabase sInstance;

    public static NewsDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG,context.getString(R.string.Creating_new_database));
                sInstance = Room.databaseBuilder(context.getApplicationContext(),NewsDatabase.class,NewsDatabase.DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build();

            }
        }
        Log.d(LOG_TAG,context.getString(R.string.Getting_database_instance));
        return sInstance;

    }

    public abstract NewsDao newsDao();
}

