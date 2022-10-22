package com.example.partnersincode.optimedia.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.GameLog;

import java.util.List;

public class GameLogAdapter extends RecyclerView.Adapter<GameLogAdapter.GameLogViewHolder>{

    private View.OnClickListener onClickListener;
    private final List<GameLog> gameLog;

    public GameLogAdapter(List<GameLog> gameLog) {
        this.gameLog = gameLog;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public static class GameLogViewHolder extends RecyclerView.ViewHolder {
        public TextView txtRecyclerGameLog;
        public GameLog gameLog;

        public GameLogViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRecyclerGameLog = itemView.findViewById(R.id.txtRecylerGameLog);
        }

        public void setGameLog(GameLog gameLog) {
            this.gameLog = gameLog;
            txtRecyclerGameLog.setText(gameLog.getGLTitle() + " " + gameLog.getGLNote());
        }
    }

    @NonNull
    @Override
    public GameLogAdapter.GameLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_game_log,parent, false);
        GameLogAdapter.GameLogViewHolder viewHolder = new GameLogAdapter.GameLogViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GameLogAdapter.GameLogViewHolder holder, int position) {
        GameLog log = gameLog.get(position);
        holder.setGameLog(log);
        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return gameLog.size();
    }
}
