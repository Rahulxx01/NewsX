package in.connectitude.newsx.ui;

import android.app.LoaderManager;
import android.content.Context;
import android.graphics.Movie;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.connectitude.newsx.R;
import in.connectitude.newsx.adapters.LatestNewsAdapter;
import in.connectitude.newsx.loaders.NewsLoader;
import in.connectitude.newsx.model.Articles;
import in.connectitude.newsx.model.News;
import in.connectitude.newsx.model.NewsSources;
import in.connectitude.newsx.model.Sources;
import in.connectitude.newsx.network.ApiService;
import in.connectitude.newsx.network.NewsSourcesUtils;
import in.connectitude.newsx.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.connectitude.newsx.utils.Constants.INDIA_LATEST_NEWS;


public class NewsHighlightsFragment extends Fragment  {

    public List<NewsSources> sourceList;
    LatestNewsAdapter sourceAdapter;
    public SwipeRefreshLayout mSwipeRefresh;

    @BindView(R.id.latestNews_recyclerView)
    RecyclerView mSourceNewsRecyclerView;
    @BindView(R.id.latestNews_ProgressBar)
    ProgressBar mProgressBar;

    private InterstitialAd mInterstitialAd;



    private static final int FORECAST_LOADER_ID = 0;


    public NewsHighlightsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_news_highlights, container, false);
        ButterKnife.bind(this,rootView);




            if (checkInternetConnectivity()) {

                new NewsSouceAsynTask().execute();

            } else {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            }

            mSourceNewsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


            mSwipeRefresh = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout_latestNews);
            mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {



                    new NewsSouceAsynTask().execute();

                }
            });






            // newHighlightsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));









        return rootView;




    }


    public boolean checkInternetConnectivity() {
        //Check internet connection//
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        // Get details on the currently active default data network//
        NetworkInfo netInformation = connectivityManager.getActiveNetworkInfo();
        // If there is a network connection, then fetch data//
        if (netInformation != null && netInformation.isConnected()) {
            return true;
        } else {
            return false;
        }


    }


    private class NewsSouceAsynTask extends AsyncTask<String, Void, List<NewsSources>> {

        @Override
        protected List<NewsSources> doInBackground(String... strings) {
            List<NewsSources> result = NewsSourcesUtils.fetchHeadlinesData(INDIA_LATEST_NEWS);
            return result;
            //  return null;
        }

        @Override
        protected void onPostExecute(List<NewsSources> list) {
            //super.onPostExecute(pcmSimplexes);
            if (list != null && !list.isEmpty()) {
                sourceList = list;

                sourceAdapter = new LatestNewsAdapter(getContext(), list,mInterstitialAd);

                mSourceNewsRecyclerView.setAdapter(sourceAdapter);
                mProgressBar.setVisibility(View.GONE);

            } else {
                Toast.makeText(getContext(), "Something Went Wrong in the Server", Toast.LENGTH_LONG).show();
            }
        }

    }

}
