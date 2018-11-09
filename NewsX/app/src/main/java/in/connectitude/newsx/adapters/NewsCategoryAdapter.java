package in.connectitude.newsx.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.connectitude.newsx.ui.NewsCategory;
import in.connectitude.newsx.R;
import in.connectitude.newsx.model.NewsSources;

public class NewsCategoryAdapter extends RecyclerView.Adapter<NewsCategoryAdapter.TheoryCardViewHolder> {
    public Context mContext;

    public List<NewsSources> mListItems;

    public ArrayList<NewsSources> mTheoryListItems;


    public NewsCategoryAdapter(Context context, List<NewsSources> mListItems) {

        mContext = context;
        this.mListItems = mListItems;
    }

    @Override
    public TheoryCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_category_item, parent, false);
        return new TheoryCardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final TheoryCardViewHolder holder, int position) {


        final NewsSources listItem = mListItems.get(position);
        holder.topicTextView.setText(listItem.getSourceCategory());
        holder.imageView.setImageResource(listItem.getImageCategory());


    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }

    public class TheoryCardViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.newsCategory_Title)
        public TextView topicTextView;
        @BindView(R.id.newsCategory_imageView)
        public ImageView imageView;
        public LinearLayout mCardElement;

        public TheoryCardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    int position = getAdapterPosition();
                    Intent intent = new Intent(view.getContext(), NewsCategory.class);
                    intent.putExtra("category_name",mListItems.get(position).getNewsCategoryName());
                    context.startActivity(intent);

                }
            });



        }
    }


}
