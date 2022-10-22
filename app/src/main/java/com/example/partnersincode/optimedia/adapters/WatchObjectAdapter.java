package com.example.partnersincode.optimedia.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Game;
import com.example.partnersincode.optimedia.models.Movie;
import com.example.partnersincode.optimedia.models.Series;
import com.example.partnersincode.optimedia.models.WatchObject;

import java.util.List;

public class WatchObjectAdapter extends RecyclerView.Adapter<WatchObjectAdapter.WatchObjectViewHolder>{
    private static final String TAG = "WatchObjectAdapter";
    private final List<WatchObject> watchObjectList;
    private View.OnClickListener onClickListener;
    private View.OnLongClickListener onLongClickListener;

    public WatchObjectAdapter(List<WatchObject> watchObjectList) {
        this.watchObjectList = watchObjectList;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener)
    {
        this.onLongClickListener = onLongClickListener;
    }

    public class WatchObjectViewHolder extends RecyclerView.ViewHolder {
        public TextView txtRecycTitle, txtRecycWatchType;
        public Movie movie;
        public Series series;

        public WatchObjectViewHolder(@NonNull View itemView) {
            super(itemView);

            txtRecycTitle = itemView.findViewById(R.id.txtRecycTitle);
            txtRecycWatchType = itemView.findViewById(R.id.txtRecycWatchType);
        }

        public void setSeries(Series series) {
            this.series = series;
            this.movie = null;
            txtRecycTitle.setText(series.getTitle());
            txtRecycWatchType.setText("Series");
            Log.d(TAG, "setSeries: "+series.getSeriesID());
        }

        public void setMovie(Movie movie){
            this.movie = movie;
            this.series = null;
            txtRecycTitle.setText(movie.getTitle());
            txtRecycWatchType.setText("Movie");
            Log.d(TAG, "setMovie: "+movie.getMovieID());
        }
    }

    @NonNull
    @Override
    public WatchObjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_watch_library,parent, false);
        return new WatchObjectAdapter.WatchObjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WatchObjectViewHolder holder, int position) {
        WatchObject watchObject = watchObjectList.get(position);
        if (watchObject instanceof Series){
            holder.setSeries((Series) watchObject);
        } else if(watchObject instanceof Movie){
            holder.setMovie((Movie) watchObject);
        }
        holder.itemView.setOnClickListener(onClickListener);
        holder.itemView.setOnLongClickListener(onLongClickListener);
    }

    @Override
    public int getItemCount() {
        return watchObjectList.size();
    }

}
