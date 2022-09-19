package com.example.partnersincode.optimedia.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Genre;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder>{

    private View.OnClickListener onClickListener;
    private final List<Genre> genreList;

    public GenreAdapter(List<Genre> genreList) {
        this.genreList = genreList;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public static class GenreViewHolder extends RecyclerView.ViewHolder {
        public TextView txtRecyclerGenre;
        public Genre genre;

        public GenreViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRecyclerGenre = itemView.findViewById(R.id.txtRecyclerGenre);
        }

        public void setGenre(Genre genre) {
            this.genre = genre;
            txtRecyclerGenre.setText(genre.getGenreName());
        }
    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_genres,parent, false);
        GenreViewHolder viewHolder = new GenreViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        Genre genre = genreList.get(position);
        holder.setGenre(genre);
        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }


}
