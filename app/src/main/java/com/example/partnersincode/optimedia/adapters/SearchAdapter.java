package com.example.partnersincode.optimedia.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Book;
import com.example.partnersincode.optimedia.models.Game;
import com.example.partnersincode.optimedia.models.MediaObject;
import com.example.partnersincode.optimedia.models.Movie;
import com.example.partnersincode.optimedia.models.Series;
import com.example.partnersincode.optimedia.models.WatchObject;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private final List<MediaObject> mediaObjects;
    private View.OnClickListener onClickListener;
    private static final String TAG = "SearchAdapter";

    public SearchAdapter(List<MediaObject> mediaObjects) {
        this.mediaObjects = mediaObjects;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        public TextView txtRecycTitle, txtRecycWatchType;
        public Book book;
        public Game game;
        public Movie movie;
        public Series series;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            txtRecycTitle = itemView.findViewById(R.id.txtRecycTitle);
            txtRecycWatchType = itemView.findViewById(R.id.txtRecycWatchType);
        }

        public void setBook(Book book){
            this.book = book;
            this.game = null;
            this.movie = null;
            this.series = null;
            txtRecycTitle.setText(book.getBookTitle());
            txtRecycWatchType.setText("Book");
            Log.d(TAG, "setBook: "+book.getBookID());
        }

        public void setGame(Game game){
            this.book = null;
            this.game = game;
            this.movie = null;
            this.series = null;
            txtRecycTitle.setText(game.getGameTitle());
            txtRecycWatchType.setText("Game");
            Log.d(TAG, "setGame: "+game.getGameID());
        }

        public void setMovie(Movie movie){
            this.book = null;
            this.game = null;
            this.movie = movie;
            this.series = null;
            txtRecycTitle.setText(movie.getTitle());
            txtRecycWatchType.setText("Movie");
            Log.d(TAG, "setMovie: "+movie.getMovieID());
        }

        public void setSeries(Series series) {
            this.book = null;
            this.game = null;
            this.series = series;
            this.movie = null;
            txtRecycTitle.setText(series.getTitle());
            txtRecycWatchType.setText("Series");
            Log.d(TAG, "setSeries: "+series.getSeriesID());
        }

    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_watch_library,parent, false);
        SearchAdapter.SearchViewHolder svh = new SearchViewHolder(view);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        MediaObject mediaObject = mediaObjects.get(position);
        if (mediaObject instanceof Book){
            holder.setBook((Book) mediaObject);
        } else if (mediaObject instanceof Game){
            holder.setGame((Game) mediaObject);
        } else if (mediaObject instanceof Series){
            holder.setSeries((Series) mediaObject);
        } else if(mediaObject instanceof Movie){
            holder.setMovie((Movie) mediaObject);
        }
        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return  mediaObjects.size();
    }


}
