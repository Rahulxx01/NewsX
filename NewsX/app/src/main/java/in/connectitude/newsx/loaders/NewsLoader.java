package in.connectitude.newsx.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.graphics.Movie;

import java.util.List;

import in.connectitude.newsx.model.NewsSources;
import in.connectitude.newsx.network.NewsSourcesUtils;

import static in.connectitude.newsx.utils.Constants.INDIA_LATEST_NEWS;

public class NewsLoader extends AsyncTaskLoader<List<NewsSources>> {
    String mUrl;
    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    public List<NewsSources> loadInBackground() {
        if(mUrl == null){
            return null;
        }
        List<NewsSources> newsSources = NewsSourcesUtils.fetchHeadlinesData(mUrl);
        return newsSources;
    }
}
