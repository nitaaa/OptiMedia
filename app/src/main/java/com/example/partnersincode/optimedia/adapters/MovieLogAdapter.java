package com.example.partnersincode.optimedia.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Movie;
import com.example.partnersincode.optimedia.models.MovieLog;

import java.util.List;

public class MovieLogAdapter extends RecyclerView.Adapter<MovieLogAdapter.MovieLogViewHolder>{

    private View.OnClickListener onClickListener;
    private final List<MovieLog> movieLog;

    public MovieLogAdapter(List<MovieLog> movieLog) {
        this.movieLog = movieLog;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public static class MovieLogViewHolder extends RecyclerView.ViewHolder {
        public TextView txtRecyclerMovieLog;
        public MovieLog movieLog;

        public MovieLogViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRecyclerMovieLog = itemView.findViewById(R.id.txtRecylerMovieLog);
        }

        public void setMovieLog(MovieLog movieLog) {
            this.movieLog = movieLog;
            txtRecyclerMovieLog.setText(movieLog.getM_note() + " " + movieLog.getM_timestamp());
        }
    }

    @NonNull
    @Override
    public MovieLogAdapter.MovieLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_movie_log,parent, false);
        MovieLogAdapter.MovieLogViewHolder viewHolder = new MovieLogAdapter.MovieLogViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieLogAdapter.MovieLogViewHolder holder, int position) {
        MovieLog log = movieLog.get(position);
        holder.setMovieLog(log);
        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return movieLog.size();
    }

}
