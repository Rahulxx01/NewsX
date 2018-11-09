package in.connectitude.newsx.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import in.connectitude.newsx.adapters.LatestNewsAdapter;
import in.connectitude.newsx.model.NewsSources;
import in.connectitude.newsx.network.NewsSourcesUtils;

import static in.connectitude.newsx.utils.Constants.INDIA_LATEST_NEWS;

public class LatestNewsViewModel extends ViewModel {

    private class NewsSouceAsynTask extends AsyncTask<String, Void, LiveData<List<NewsSources>>> {

        @Override
        protected LiveData<List<NewsSources>> doInBackground(String... strings) {
            LiveData<List<NewsSources>> result = (LiveData<List<NewsSources>>) NewsSourcesUtils.fetchHeadlinesData(INDIA_LATEST_NEWS);
            return result;
            //  return null;
        }



    }



}
