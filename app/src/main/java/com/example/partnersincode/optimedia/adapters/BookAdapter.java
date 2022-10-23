package com.example.partnersincode.optimedia.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Author;
import com.example.partnersincode.optimedia.models.Book;
import com.example.partnersincode.optimedia.models.Library;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>{
    private final List<Book> bookList;
    private View.OnClickListener onClickListener;
    private View.OnLongClickListener onLongClickListener;

    public BookAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener)
    {
        this.onLongClickListener = onLongClickListener;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        public TextView txtRecycBookName;
        public TextView txtRecycAuthor;
        public Book book;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            txtRecycBookName = itemView.findViewById(R.id.txtRecycBookName);
            txtRecycAuthor = itemView.findViewById(R.id.txtRecycAuthor);
        }

        public void setBook(Book book) {
            // TODO: set which library is selected
            this.book = book;

            txtRecycBookName.setText(book.getBookTitle());
            DatabaseHandler dbHandler = new DatabaseHandler(itemView.getContext());
            Author author = dbHandler.getAuthorByID(book.getAuthorID());
            if (author.getAuthorName() != null)
                txtRecycAuthor.setText(author.getFullName());
        }
    }

    @NonNull
    @Override
    public BookAdapter.BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_book_library,parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.setBook(book);
        holder.itemView.setOnClickListener(onClickListener);
        holder.itemView.setOnLongClickListener(onLongClickListener);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public void remove(Book book)
    {
        bookList.remove(book);
        notifyDataSetChanged();
    }

}
