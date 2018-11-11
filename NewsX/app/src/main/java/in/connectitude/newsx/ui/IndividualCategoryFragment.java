package in.connectitude.newsx.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.connectitude.newsx.R;
import in.connectitude.newsx.adapters.LatestNewsAdapter;
import in.connectitude.newsx.model.Article;
import in.connectitude.newsx.model.News;
import in.connectitude.newsx.model.NewsSources;
import in.connectitude.newsx.network.NewsSourcesUtils;
import in.connectitude.newsx.viewmodel.LatestNewsViewModel;


public class IndividualCategoryFragment extends Fragment {


    public List<NewsSources> sourceList;
    LatestNewsAdapter sourceAdapter;
    public SwipeRefreshLayout mSwipeRefresh;

    @BindView(R.id.categoryNewsActivity_recyclerView)
    RecyclerView mSourceNewsRecyclerView;
    @BindView(R.id.categoryNews_ProgressBar)
    ProgressBar mProgressBar;

    public String CATEGORY_URL = "https://newsapi.org/v2/top-headlines?country=in&category=";
    public String API_KEY = "&apiKey=c35fbbe4f0f24045bba98b491faeca54";
    public String FINAL_URL = "";

    News newsData;
    List<Article> articles;


    public IndividualCategoryFragment() {
        // Required empty public constructor
    }


    String category;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_individual_category, container, false);
        ButterKnife.bind(this, rootView);


        //FINAL_URL = getArguments().getString("FINAL_URL");

        category = getArguments().getString(getString(R.string.category_string_fragment));
        mProgressBar.setVisibility(View.VISIBLE);

        mSourceNewsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (checkInternetConnectivity()) {

            if (category.equals(getString(R.string.business))) {

                LatestNewsViewModel model = ViewModelProviders.of(this).get(LatestNewsViewModel.class);
                model.getNewsBusiness().observe(this, new Observer<List<NewsSources>>() {
                    @Override
                    public void onChanged(@Nullable List<NewsSources> heroList) {
                        sourceAdapter = new LatestNewsAdapter(getContext(), heroList);
                        mSourceNewsRecyclerView.setAdapter(sourceAdapter);
                        mProgressBar.setVisibility(View.GONE);
                    }
                });

            } else if (category.equals(getString(R.string.general))) {
                LatestNewsViewModel model = ViewModelProviders.of(this).get(LatestNewsViewModel.class);
                model.getNewsGeneral().observe(this, new Observer<List<NewsSources>>() {
                    @Override
                    public void onChanged(@Nullable List<NewsSources> heroList) {
                        sourceAdapter = new LatestNewsAdapter(getContext(), heroList);
                        mSourceNewsRecyclerView.setAdapter(sourceAdapter);
                        mProgressBar.setVisibility(View.GONE);
                    }
                });


            } else if (category.equals(getString(R.string.health))) {
                LatestNewsViewModel model = ViewModelProviders.of(this).get(LatestNewsViewModel.class);
                model.getNewsHealth().observe(this, new Observer<List<NewsSources>>() {
                    @Override
                    public void onChanged(@Nullable List<NewsSources> heroList) {
                        sourceAdapter = new LatestNewsAdapter(getContext(), heroList);
                        mSourceNewsRecyclerView.setAdapter(sourceAdapter);
                        mProgressBar.setVisibility(View.GONE);

                    }
                });


            } else if (category.equals(getString(R.string.science))) {

                LatestNewsViewModel model = ViewModelProviders.of(this).get(LatestNewsViewModel.class);
                model.getNewsScience().observe(this, new Observer<List<NewsSources>>() {
                    @Override
                    public void onChanged(@Nullable List<NewsSources> heroList) {
                        sourceAdapter = new LatestNewsAdapter(getContext(), heroList);
                        mSourceNewsRecyclerView.setAdapter(sourceAdapter);
                        mProgressBar.setVisibility(View.GONE);

                    }
                });


            } else if (category.equals(getString(R.string.sports))) {
                LatestNewsViewModel model = ViewModelProviders.of(this).get(LatestNewsViewModel.class);
                model.getNewsSports().observe(this, new Observer<List<NewsSources>>() {
                    @Override
                    public void onChanged(@Nullable List<NewsSources> heroList) {
                        sourceAdapter = new LatestNewsAdapter(getContext(), heroList);
                        mSourceNewsRecyclerView.setAdapter(sourceAdapter);
                        mProgressBar.setVisibility(View.GONE);

                    }
                });


            } else if (category.equals(getString(R.string.technology))) {

                LatestNewsViewModel model = ViewModelProviders.of(this).get(LatestNewsViewModel.class);
                model.getNewsTechnology().observe(this, new Observer<List<NewsSources>>() {
                    @Override
                    public void onChanged(@Nullable List<NewsSources> heroList) {
                        sourceAdapter = new LatestNewsAdapter(getContext(), heroList);
                        mSourceNewsRecyclerView.setAdapter(sourceAdapter);
                        mProgressBar.setVisibility(View.GONE);

                    }
                });


            } else {
                //Entertainment
                LatestNewsViewModel model = ViewModelProviders.of(this).get(LatestNewsViewModel.class);
                model.getNewsEntertainment().observe(this, new Observer<List<NewsSources>>() {
                    @Override
                    public void onChanged(@Nullable List<NewsSources> heroList) {
                        sourceAdapter = new LatestNewsAdapter(getContext(), heroList);
                        mSourceNewsRecyclerView.setAdapter(sourceAdapter);
                        mProgressBar.setVisibility(View.GONE);

                    }
                });
            }


        } else {
            Toast.makeText(getContext(), R.string.No_Internet_Connection, Toast.LENGTH_LONG).show();
        }



        return rootView;
    }


    private class NewsSouceAsynTask extends AsyncTask<String, Void, List<NewsSources>> {

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

                sourceAdapter = new LatestNewsAdapter(getContext(), list);

                mSourceNewsRecyclerView.setAdapter(sourceAdapter);
                mProgressBar.setVisibility(View.GONE);

            } else {
                Toast.makeText(getContext(), R.string.something_went_wrong, Toast.LENGTH_LONG).show();
            }
        }

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


}
