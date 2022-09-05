package com.example.partnersincode.optimedia.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Author;

import java.util.List;

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.AuthorViewHolder>{

    private View.OnClickListener onClickListener;
    private final List<Author> authorList;

    public AuthorAdapter(List<Author> authorList) {
        this.authorList = authorList;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public static class AuthorViewHolder extends RecyclerView.ViewHolder {
        public TextView txtRecyclerAuthorName;
        public Author author;

        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRecyclerAuthorName = itemView.findViewById(R.id.txtRecyclerAuthorName);
        }

        public void setAuthor(Author author) {
            this.author = author;
            txtRecyclerAuthorName.setText(author.getFullName());
        }
    }

    @NonNull
    @Override
    public AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_authors,parent, false);
        AuthorViewHolder viewHolder = new AuthorViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorViewHolder holder, int position) {
        Author author = authorList.get(position);
        holder.setAuthor(author);
        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return authorList.size();
    }


}
