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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_news_category, container, false);
        categoryRecyclerView = rootView.findViewById(R.id.newsCategory_recyclerView);
        categoryRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        categoryList = new ArrayList<NewsSources>();
        categoryList.add(new NewsSources("General",R.drawable.general,"general"));
        categoryList.add(new NewsSources("Entertainment",R.drawable.general,"entertainment"));
        categoryList.add(new NewsSources("Business",R.drawable.business,"business"));
        categoryList.add(new NewsSources("Health",R.drawable.health,"health"));

        categoryList.add(new NewsSources("Science",R.drawable.science,"science"));
        categoryList.add(new NewsSources("Sports",R.drawable.sports,"sports"));
        categoryList.add(new NewsSources("Technology",R.drawable.technology,"technology"));

        NewsCategoryAdapter categoryAdapter = new NewsCategoryAdapter(getContext(),categoryList);
        categoryRecyclerView.setAdapter(categoryAdapter);


        return rootView;
    }


}
