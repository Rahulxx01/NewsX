package in.connectitude.newsx.adapters;





import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.connectitude.newsx.R;
import in.connectitude.newsx.model.NewsSources;
import in.connectitude.newsx.ui.NewsDetailActivity;


public class LatestNewsAdapter extends RecyclerView.Adapter<LatestNewsAdapter.LatestNewsCardViewHolder> {

    public Context mContext;

    public List<NewsSources> mListItems;

    public ArrayList<NewsSources> mLatestNewsListItems;


    NewsSources listItem;

    InterstitialAd mInterstitialAd;

    public LatestNewsAdapter(Context context, List<NewsSources> mListItems) {

        mContext = context;
        this.mListItems = mListItems;

    }


    public LatestNewsAdapter(Context context, List<NewsSources> mListItems,InterstitialAd mInterstitialAd) {

        mContext = context;
        this.mListItems = mListItems;
        this.mInterstitialAd = mInterstitialAd;

    }

    @Override
    public LatestNewsAdapter.LatestNewsCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.latest_news_list_item, parent, false);
        return new LatestNewsAdapter.LatestNewsCardViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(final LatestNewsAdapter.LatestNewsCardViewHolder holder, int position) {


        listItem = mListItems.get(position);
        String author = "";


        holder.author.setText(listItem.getName());
        String date = null;
        try {
            date = formatDate(listItem.getPublishedAt());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.datePublished.setText(date);
        holder.title.setText(listItem.getTitle());
        Picasso.with(mContext).load(listItem.getUrlToImage()).into(holder.newsImageView);


    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }

    public class LatestNewsCardViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.news_imageView)
        public ImageView newsImageView;
        public LinearLayout mCardElement;


        @BindView(R.id.sourceName)
        TextView author;

        @BindView(R.id.newsTitle)
        TextView title;

        @BindView(R.id.source_time)
        TextView datePublished;

        @BindView(R.id.browserLink)
        ImageView browserLink;

        @BindView(R.id.shareLink)
        ImageView shareLink;


        public LatestNewsCardViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);



            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                        Context context = view.getContext();
                        int position = getAdapterPosition();
                        Intent intent = new Intent(view.getContext(), NewsDetailActivity.class);
                        intent.putExtra("name", mListItems.get(position).getName());
                        intent.putExtra("title", mListItems.get(position).getTitle());
                        intent.putExtra("url", mListItems.get(position).getUrl());
                        intent.putExtra("description", mListItems.get(position).getDescription());

                    try {
                        intent.putExtra("publishedAt", formatDate(mListItems.get(position).getPublishedAt()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    intent.putExtra("url_image", mListItems.get(position).getUrlToImage());
                        intent.putExtra("content", mListItems.get(position).getContent());
                        context.startActivity(intent);



                }
            });

            browserLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    int position = getAdapterPosition();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse( mListItems.get(position).getUrl()));
                    context.startActivity(i);
                }
            });


            shareLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    int position = getAdapterPosition();
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, mListItems.get(position).getUrl());
                    context.startActivity(shareIntent);
                }
            });

        }
    }


    public String formatDate(String date) throws ParseException {

            return date.substring(0,10);


    }


}

