package io.ponchitaz.comicsdowry;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * This is where the magic is done - the adapter class with view holder.
 * This is about how to display comics cards and how to pass data taken from DB
 * using models into the recycler view.
 */


public class MainPageRecyclerAdapter extends RecyclerView.Adapter<MainPageRecyclerAdapter.MainPageViewHolder> {
    List<ComicsBook> list;
    Context context;

    public MainPageRecyclerAdapter(List<ComicsBook> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MainPageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_listing_card_view, parent, false);
        MainPageViewHolder mainPageViewHolder = new MainPageViewHolder(view);
        return mainPageViewHolder;
    }

    @Override
    public void onBindViewHolder(MainPageViewHolder holder, final int position) {
        final ComicsBook comicsList = list.get(position);
        holder.bookTitle.setText(comicsList.getBookTitle());
        holder.bookAuthors.setText(comicsList.getBookAuthors());
        Glide.with(context).load(comicsList.getBookPicUrl()).into(holder.bookPic);
        holder.bookPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ComicsPage.class);
                intent.putExtra("bookInfo", comicsList.bookId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        int arr = 0;
        try {
            if (list.size() != 0) {
                arr = list.size();
            }
        } catch (Exception e) {
        }
        return arr;
    }

    class MainPageViewHolder extends RecyclerView.ViewHolder {
        CardView mainListingCard;
        ImageView bookPic;
        TextView bookTitle;
        TextView bookAuthors;
        Button addToShelfButton;
        Button addToWishListButton;
        View bookView;

        public MainPageViewHolder(View itemView) {
            super(itemView);
            bookView = itemView;
            mainListingCard = (CardView) itemView.findViewById(R.id.main_comics_card);
            bookTitle = (TextView) itemView.findViewById(R.id.comicsTitle);
            bookAuthors = (TextView) itemView.findViewById(R.id.comicsAuthors);
            bookPic = (ImageView) itemView.findViewById(R.id.comicsPicture);
            addToShelfButton = (Button) itemView.findViewById(R.id.addToShelfButton);
            addToWishListButton = (Button) itemView.findViewById(R.id.addToWishlistButton);
        }
    }
}

