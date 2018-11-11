package in.connectitude.newsx.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.connectitude.newsx.R;
import in.connectitude.newsx.adapters.NewsCategoryAdapter;
import in.connectitude.newsx.model.NewsSources;
public class NewsCategoryFragment extends Fragment {
    RecyclerView categoryRecyclerView;
    ArrayList<NewsSources> categoryList;
    public NewsCategoryFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_category, container, false);
        categoryRecyclerView = rootView.findViewById(R.id.newsCategory_recyclerView);
        categoryRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        categoryList = new ArrayList<NewsSources>();
        categoryList.add(new NewsSources(getString(R.string.GeneralCategory),R.drawable.general,getString(R.string.generalNewsCategory)));
        categoryList.add(new NewsSources(getString(R.string.entertainmentCategory),R.drawable.general,getString(R.string.entertainmentNewsCategory)));
        categoryList.add(new NewsSources(getString(R.string.businessCategory),R.drawable.business,getString(R.string.businessNewsCategory)));
        categoryList.add(new NewsSources(getString(R.string.healthCategory),R.drawable.health,getString(R.string.healthNewsCategory)));
        categoryList.add(new NewsSources(getString(R.string.scienceCategory),R.drawable.science,getString(R.string.scienceNewsCategory)));
        categoryList.add(new NewsSources(getString(R.string.sportsCategory),R.drawable.sports,getString(R.string.sportsNewsCategory)));
        categoryList.add(new NewsSources(getString(R.string.technologyCategory),R.drawable.technology,getString(R.string.technologyNewsCategory)));

        NewsCategoryAdapter categoryAdapter = new NewsCategoryAdapter(getContext(),categoryList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        return rootView;
    }
}
