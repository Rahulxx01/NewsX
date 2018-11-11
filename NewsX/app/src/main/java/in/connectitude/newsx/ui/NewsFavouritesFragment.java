package in.connectitude.newsx.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Movie;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.InterstitialAd;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.connectitude.newsx.R;
import in.connectitude.newsx.adapters.LatestNewsAdapter;
import in.connectitude.newsx.database.NewsDatabase;
import in.connectitude.newsx.database.NewsViewModel;
import in.connectitude.newsx.model.NewsSources;


public class NewsFavouritesFragment extends Fragment {


    @BindView(R.id.favouritesNews_recyclerView)
    RecyclerView favouritesRecyclerView;
    private NewsDatabase movieDatabase;
    LatestNewsAdapter newsAdapter;
    @BindView(R.id.noNewsAdded)
    TextView noNewsAddedTextView;

    public NewsFavouritesFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_favourites, container, false);
        ButterKnife.bind(this, rootView);
        movieDatabase = NewsDatabase.getInstance((getContext()));
        favouritesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        setupViewModel();
        return rootView;
    }
    private void setupViewModel() {
        NewsViewModel viewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        viewModel.getNewsList().observe(this, new Observer<List<NewsSources>>() {
            @Override
            public void onChanged(@Nullable List<NewsSources> news) {
                newsAdapter = new LatestNewsAdapter(getContext(), news);
                favouritesRecyclerView.setAdapter(newsAdapter);
                noNewsAddedTextView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
