package com.example.firebase_practice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyTViewHolder> {

    private Context context;
    private List<Book> bookList;

    public BookAdapter(List<Book> bookList) {
    }

    public class MyTViewHolder extends RecyclerView.ViewHolder{
        public ImageView book_photo;
        public  TextView title, author, pages, score;

        public MyTViewHolder(View view){
            super(view);
            book_photo = view.findViewById(R.id.item_book_img);
            title = view.findViewById(R.id.item_book_title);
            author = view.findViewById(R.id.item_book_author);
            score = view.findViewById(R.id.item_book_score);
            pages = view.findViewById(R.id.item_book_pages);
        }
    }
    public BookAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }
    @NonNull
    @Override
    public MyTViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);

        return new MyTViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTViewHolder holder, int position) {
        Book item = bookList.get(position);

//        holder.book_photo.setImageResource(item.getPhoto());
        holder.title.setText(item.getTitle());
        holder.author.setText(item.getAuthor());
        holder.score.setText(item.getScore());
        holder.pages.setText(item.getPages());
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }
}
