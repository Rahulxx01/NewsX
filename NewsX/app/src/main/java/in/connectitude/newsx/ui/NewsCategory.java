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



        String category = getIntent().getStringExtra(getString(R.string.category_name));
        if(category.equals(getString(R.string.business))){
            setTitle(R.string.BusinessTitle);

        }else if(category.equals(getString(R.string.general))){

            setTitle(R.string.GeneralTitle);

        }else if(category.equals(getString(R.string.entertainment))){

            setTitle(R.string.EntertainementTitle);

        }else if(category.equals(getString(R.string.sports))){

            setTitle(R.string.SportsTitle);

        }else if(category.equals(getString(R.string.science))){

            setTitle(R.string.ScienceTitle);

        }else if(category.equals(getString(R.string.health))){

            setTitle(R.string.HealthTitle);

        }else if(category.equals(getString(R.string.technology))){

            setTitle(R.string.TechnologyTitle);

        }
        FINAL_URL = CATEGORY_URL+category+API_KEY;

        if(null == savedInstanceState){
            IndividualCategoryFragment individualCategoryFragment = new  IndividualCategoryFragment();
            Bundle fragmentBundle1 = new Bundle();
            fragmentBundle1.putString(getString(R.string.category), category);
            individualCategoryFragment.setArguments(fragmentBundle1);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.newsCategoryContainer, individualCategoryFragment)
                    .commit();
        }

    }

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







