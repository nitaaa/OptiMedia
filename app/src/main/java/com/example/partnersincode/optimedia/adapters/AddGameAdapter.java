package com.example.partnersincode.optimedia.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Game;
import com.example.partnersincode.optimedia.models.WatchObject;

import java.util.ArrayList;
import java.util.List;

public class AddGameAdapter extends RecyclerView.Adapter<AddGameAdapter.AddGameViewHolder>{

    private View.OnClickListener onClickListener;
    private final List<Game> gameList;
    static ArrayList<Game> selectedObjects;

    public AddGameAdapter(List<Game> game) {
        this.gameList = game;
        selectedObjects = new ArrayList<>();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public static class AddGameViewHolder extends RecyclerView.ViewHolder {
        public TextView txtRecyclerGameName;
        public Game game;
        public Boolean selected = false;

        public AddGameViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRecyclerGameName = itemView.findViewById(R.id.txtRecylerGameName);
        }

        public void setGame(Game game) {
            this.game = game;
            txtRecyclerGameName.setText(game.getGameTitle());
        }

        public Game getGame()
        {
            return game;
        }

        public void setSelected()
        {
            //If it isn't selected by clicked on, make it green(or other colour) and set selected
            if (!selected) {
                selected = true;
                selectedObjects.add(getGame());
                itemView.setBackgroundResource(R.color.selectedColour);
            }
            else
            {
                //If already selected and clicked on, set unselected
                selected = false;
                itemView.setBackgroundResource(R.color.design_default_color_background);
                selectedObjects.remove(getGame());
            }
        }
    }

    @NonNull
    @Override
    public AddGameAdapter.AddGameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_games,parent, false);
        AddGameAdapter.AddGameViewHolder viewHolder = new AddGameAdapter.AddGameViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddGameAdapter.AddGameViewHolder holder, int position) {
        Game game = gameList.get(position);
        holder.setGame(game);
        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public static ArrayList<Game> getSelectedObjects() {
        return selectedObjects;
    }
}
