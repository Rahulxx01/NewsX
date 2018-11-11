package in.connectitude.newsx.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import in.connectitude.newsx.R;
import in.connectitude.newsx.ui.NewsCategoryFragment;
import in.connectitude.newsx.ui.NewsFavouritesFragment;
import in.connectitude.newsx.ui.NewsHighlightsFragment;

public class MainTabPageAdapter extends FragmentPagerAdapter {

    Context context;

    public MainTabPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new NewsHighlightsFragment();
            case 1:
                return new NewsCategoryFragment();
            case 2:
                return new NewsFavouritesFragment();

            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return 3;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.HeadLines);
            case 1:
                return context.getString(R.string.Categories);
            case 2:
                return context.getString(R.string.favourites);
            default:
                return null;
        }

    }
}
