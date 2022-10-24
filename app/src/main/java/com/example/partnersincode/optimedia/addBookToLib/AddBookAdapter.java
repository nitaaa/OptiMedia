package com.example.partnersincode.optimedia.addBookToLib;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Book;

import java.util.ArrayList;
import java.util.List;

public class AddBookAdapter extends RecyclerView.Adapter<AddBookAdapter.AddBookViewHolder>{

    private View.OnClickListener onClickListener;
    private final List<Book> bookList;
    static ArrayList<Book> selectedObjects;

    public AddBookAdapter(List<Book> book) {
        this.bookList = book;
        selectedObjects = new ArrayList<>();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public static class AddBookViewHolder extends RecyclerView.ViewHolder {
        public TextView txtRecyclerBookName;
        public Book book;
        public Boolean selected = false;

        public AddBookViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRecyclerBookName = itemView.findViewById(R.id.txtRecylerBookName);
        }

        public void setBook(Book book) {
            this.book = book;
            txtRecyclerBookName.setText(book.getBookTitle());
        }

        public Book getBook()
        {
            return book;
        }

        public void setSelected()
        {
            //If it isn't selected by clicked on, make it green(or other colour) and set selected
            if (!selected) {
                selected = true;
                selectedObjects.add(getBook());
                itemView.setBackgroundResource(R.color.selectedColour);
            }
            else
            {
                //If already selected and clicked on, set unselected
                selected = false;
                itemView.setBackgroundResource(R.color.design_default_color_background);
                selectedObjects.remove(getBook());
            }
        }
    }

    @NonNull
    @Override
    public AddBookAdapter.AddBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_books,parent, false);
       AddBookAdapter.AddBookViewHolder viewHolder = new AddBookAdapter.AddBookViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddBookAdapter.AddBookViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.setBook(book);
        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public static ArrayList<Book> getSelectedObjects() {
        return selectedObjects;
    }
}

