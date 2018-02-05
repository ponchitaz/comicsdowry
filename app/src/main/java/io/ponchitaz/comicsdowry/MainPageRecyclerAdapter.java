package io.ponchitaz.comicsdowry;

import android.content.Context;
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


public class MainPageRecyclerAdapter extends RecyclerView.Adapter<MainPageRecyclerAdapter.MainPageViewHolder>{
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
    public void onBindViewHolder(MainPageViewHolder holder, int position) {
        ComicsBook comicsList = list.get(position);
//        holder.bookPic.setImageURI();
        holder.bookTitle.setText(comicsList.getBookTitle());
        holder.bookAuthors.setText(comicsList.getBookAuthors());
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

        public MainPageViewHolder(View itemView){
            super(itemView);
            mainListingCard = (CardView) itemView.findViewById(R.id.main_comics_card);
            bookTitle = (TextView) itemView.findViewById(R.id.comicsTitle);
            bookAuthors = (TextView) itemView.findViewById(R.id.comicsAuthors);
            addToShelfButton = (Button) itemView.findViewById(R.id.addToShelfButton);
            addToWishListButton = (Button) itemView.findViewById(R.id.addToWishlistButton);
        }
//        public void setImage(final Context context, final String bookPicture){
//            bookPic = (ImageView) itemView.findViewById(R.id.comicsPicture);
//            Glide.with(context).load(bookPicture).into(bookPic);
//              }
//
 }
}

