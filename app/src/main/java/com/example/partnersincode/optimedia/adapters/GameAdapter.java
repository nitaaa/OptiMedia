package com.example.partnersincode.optimedia.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Game;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder>{
    private final List<Game> gameList;
    private View.OnClickListener onClickListener;

    public GameAdapter(List<Game> gameList) {
        this.gameList = gameList;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public class GameViewHolder extends RecyclerView.ViewHolder  {
        public TextView txtRecycGameTitle, txtRecycGameType;
        public Game game;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);

            txtRecycGameTitle = itemView.findViewById(R.id.txtRecycGameTitle);
            txtRecycGameType = itemView.findViewById(R.id.txtRecycGameType);
        }

        public void setGame(Game game) {
            this.game = game;

            txtRecycGameTitle.setText(game.getGameTitle());
            txtRecycGameType.setText(game.getGameType());
        }
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_game_library,parent, false);
        return new GameAdapter.GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        Game game = gameList.get(position);
        holder.setGame(game);
        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }
}
