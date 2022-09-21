package com.example.partnersincode.optimedia.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Game;
import com.example.partnersincode.optimedia.models.MovieLog;

import java.util.List;

public class AddGameAdapter extends RecyclerView.Adapter<AddGameAdapter.AddGameViewHolder>{

    private View.OnClickListener onClickListener;
    private final List<Game> game;

    public AddGameAdapter(List<Game> game) {
        this.game = game;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public static class AddGameViewHolder extends RecyclerView.ViewHolder {
        public TextView txtRecyclerGameName;
        public Game game;

        public AddGameViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRecyclerGameName = itemView.findViewById(R.id.txtRecyclerGameName);
        }

        public void setGame(Game game) {
            this.game = game;
            txtRecyclerGameName.setText(game.getGameTitle());
        }
    }

    @NonNull
    @Override
    public MovieLogAdapter.MovieLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_authors,parent, false);
        MovieLogAdapter.MovieLogViewHolder viewHolder = new MovieLogAdapter.MovieLogViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorAdapter.AuthorViewHolder holder, int position) {
        MovieLog movieLog = movieLog.get(position);
        holder.setAuthor(author);
        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return authorList.size();
    }
}
