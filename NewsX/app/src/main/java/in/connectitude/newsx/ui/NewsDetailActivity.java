package in.connectitude.newsx.ui;

import android.appwidget.AppWidgetManager;
import android.arch.persistence.room.Database;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Movie;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.connectitude.newsx.R;
import in.connectitude.newsx.database.AppExecutors;
import in.connectitude.newsx.database.NewsDatabase;
import in.connectitude.newsx.model.NewsSources;
import in.connectitude.newsx.utils.Constants;
import in.connectitude.newsx.widget.NewsWidgetProvider;

public class NewsDetailActivity extends AppCompatActivity {

    @BindView(R.id.newsTitle_TextView)
    TextView titleTextView;

    @BindView(R.id.newsName_textView)
    TextView nameTextView;

    @BindView(R.id.publishedAt_textView)
    TextView publishedAtTextView;

    @BindView(R.id.newsDetail_ImageView)
    ImageView newsImagaView;

    @BindView(R.id.news_description)
    TextView descriptionTextView;

    @BindView(R.id.news_content)
    TextView contentTextView;




    @BindView(R.id.detailNewsWebView)
    WebView detailNewsWebView;

    String imageposterImagePath;

    @BindView(R.id.browserDetailImageView)
    ImageView browserImageView;

    @BindView(R.id.shareDetailImageView)
    ImageView shareImageView;


    NewsDatabase newsDatabase;


    @BindView(R.id.favouritesButton)
    ToggleButton favouritesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        if (!checkInternetConnectivity()) {
            detailNewsWebView.setVisibility(View.GONE);
        }

        final String title = getIntent().getStringExtra(getString(R.string.titleDetail));
        titleTextView.setText(getIntent().getStringExtra(getString(R.string.titleDetail)));
        nameTextView.setText(getIntent().getStringExtra(getString(R.string.newNameDetail)));
        publishedAtTextView.setText(getIntent().getStringExtra(getString(R.string.publishedAtDetail)));
        descriptionTextView.setText(getIntent().getStringExtra(getString(R.string.newsDescriptionDetail)));
        contentTextView.setText(getIntent().getStringExtra(getString(R.string.contentDetail)));


        Picasso.with(this).load(getIntent().getStringExtra(getString(R.string.urlImageDetail))).into(newsImagaView);

        WebSettings webSettings = detailNewsWebView.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (checkInternetConnectivity()) {
            detailNewsWebView.loadUrl(getIntent().getStringExtra(getString(R.string.urlDetail)));
        } else {
            Toast.makeText(this, R.string.plz_check_your_internetConnection, Toast.LENGTH_LONG).show();
            detailNewsWebView.setVisibility(View.GONE);
        }



        browserImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences(Constants.NEWS, MODE_PRIVATE).edit();
                editor.putString(getString(R.string.titleDetail),getIntent().getStringExtra(getString(R.string.titleDetail)));
                editor.putString(getString(R.string.newNameDetail),getIntent().getStringExtra(getString(R.string.newNameDetail)));
                editor.putString(getString(R.string.newsDescriptionDetail), getIntent().getStringExtra(getString(R.string.newsDescriptionDetail)));
                editor.commit();

                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(getBaseContext(), NewsWidgetProvider.class));
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.newsWidgetTitle);
                NewsWidgetProvider.updateNewsWidgets(getApplicationContext(),appWidgetManager,appWidgetIds);
                Toast.makeText(getApplicationContext(), R.string.widget_added_to_homescreen, Toast.LENGTH_SHORT).show();
            }
        });


        shareImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, getIntent().getStringExtra(getString(R.string.urlDetail)));
                startActivity(shareIntent);
            }
        });


        newsDatabase = NewsDatabase.getInstance(getApplicationContext());

        if (exists(title)) {
            //favouritesButton.setBackground(getDrawable(R.drawable.ic_star_selected));
            favouritesButton.setChecked(true);
        } else {
            //favouritesButton.setBackground(getDrawable(R.drawable.ic_star_not_selected));
            favouritesButton.setChecked(false);
        }


        favouritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(exists(title)){
                    deleteNews(title);
                    favouritesButton.setChecked(false);
                    Toast.makeText(getApplicationContext(),getIntent().getStringExtra(getString(R.string.titleDataBase))+getString(R.string.removedFromFav),Toast.LENGTH_LONG).show();

                }else{

                    onSaveButtonClicked(title);
                    favouritesButton.setChecked(true);
                    Toast.makeText(getApplicationContext(),getIntent().getStringExtra(getString(R.string.titleDataBase))+getString(R.string.addedToFab),Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void onSaveButtonClicked(String title) {

        String newsTitle = titleTextView.getText().toString();
        String name = nameTextView.getText().toString();
        String author = getIntent().getStringExtra(getString(R.string.authorDetail));
        String description = descriptionTextView.getText().toString();
        String url =  getIntent().getStringExtra(getString(R.string.urlDetail));
        String urlToImage = getIntent().getStringExtra(getString(R.string.urlImageDetail));
        String publishedAt = publishedAtTextView.getText().toString();
        String content = contentTextView.getText().toString();




        final NewsSources news = new NewsSources(name,author,newsTitle,description,url,urlToImage,publishedAt,content);
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                newsDatabase.newsDao().insertNews(news);
            }
        });

    }

    public void deleteNews(final String title){
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                newsDatabase.newsDao().deleteNews(title);
            }
        });
    }


    public boolean exists(final String title){


        if(newsDatabase.newsDao().checkIfNewsExists(title)){
            return true;
        }else{
            return false;
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
    }

}
