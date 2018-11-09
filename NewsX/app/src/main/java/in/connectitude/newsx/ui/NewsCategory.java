package in.connectitude.newsx.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.connectitude.newsx.R;
import in.connectitude.newsx.adapters.LatestNewsAdapter;
import in.connectitude.newsx.loaders.NewsLoader;
import in.connectitude.newsx.model.NewsSources;
import in.connectitude.newsx.network.NewsSourcesUtils;
import in.connectitude.newsx.loaders.NewsLoader;


import static in.connectitude.newsx.utils.Constants.INDIA_LATEST_NEWS;

public class NewsCategory extends AppCompatActivity  {

    public List<NewsSources> sourceList;
    LatestNewsAdapter sourceAdapter;
    public SwipeRefreshLayout mSwipeRefresh;

  /*  @BindView(R.id.categoryNewsActivity_recyclerView)
    RecyclerView mSourceNewsRecyclerView;
    @BindView(R.id.categoryNews_ProgressBar)
    ProgressBar mProgressBar;*/

    public String CATEGORY_URL = "https://newsapi.org/v2/top-headlines?country=in&category=";
    public String API_KEY = "&apiKey=c35fbbe4f0f24045bba98b491faeca54";
    public String FINAL_URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);



        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String category = getIntent().getStringExtra("category_name");
        setTitle(category);
        FINAL_URL = CATEGORY_URL+category+API_KEY;

        if(null == savedInstanceState){

           ;
            IndividualCategoryFragment individualCategoryFragment = new  IndividualCategoryFragment();
            Bundle fragmentBundle1 = new Bundle();
            fragmentBundle1.putString("FINAL_URL", FINAL_URL);
            individualCategoryFragment.setArguments(fragmentBundle1);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.newsCategoryContainer, individualCategoryFragment)
                    .commit();




        }






           /* if (checkInternetConnectivity()) {

                new NewsSouceAsynTask().execute();

            } else {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            }
            mSourceNewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));


            mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout_categoryNews);
            mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {

                    new NewsSouceAsynTask().execute();

                }
            });*/





    }



  /*  private class NewsSouceAsynTask extends AsyncTask<String, Void, List<NewsSources>> {

        @Override
        protected List<NewsSources> doInBackground(String... strings) {
            List<NewsSources> result = NewsSourcesUtils.fetchHeadlinesData(FINAL_URL);
            return result;
            //  return null;
        }

        @Override
        protected void onPostExecute(List<NewsSources> list) {
            //super.onPostExecute(pcmSimplexes);
            if (list != null && !list.isEmpty()) {
                sourceList = list;

                sourceAdapter = new LatestNewsAdapter(getApplicationContext(), list);

                mSourceNewsRecyclerView.setAdapter(sourceAdapter);
                mProgressBar.setVisibility(View.GONE);

            } else {
                Toast.makeText(getApplicationContext(), "Something Went Wrong in the Server", Toast.LENGTH_LONG).show();
            }
        }

    }

    public boolean checkInternetConnectivity() {
        //Check internet connection//
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // Get details on the currently active default data network//
        NetworkInfo netInformation = connectivityManager.getActiveNetworkInfo();
        // If there is a network connection, then fetch data//
        if (netInformation != null && netInformation.isConnected()) {
            return true;
        } else {
            return false;
        }


    }*/


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}







