package com.example.partnersincode.optimedia.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Booklog;
import com.example.partnersincode.optimedia.models.Genre;

import java.util.List;

public class BooklogAdapter extends RecyclerView.Adapter<BooklogAdapter.BLViewHolder> {

    private View.OnClickListener onClickListener;
    private final List<Booklog> blList;

    public BooklogAdapter(List<Booklog> blList) {
        this.blList = blList;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public static class BLViewHolder extends RecyclerView.ViewHolder {
        public TextView txtRecyclerBL;
        public TextView txtRecyclerBLNote;
        public Booklog booklog;

        public BLViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRecyclerBL = itemView.findViewById(R.id.txtRecyclerBooklog);
            txtRecyclerBLNote = itemView.findViewById(R.id.txtRecyclerBooklogNote);
        }

        @SuppressLint("SetTextI18n")
        public void setBooklog(Booklog booklog) {
            this.booklog = booklog;
            txtRecyclerBL.setText(booklog.getBlTitle() + ", p" + booklog.getBlPageNumber());
            txtRecyclerBLNote.setText(booklog.getBlNote());
        }
    }

    @NonNull
    @Override
    public BLViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_booklogs, parent, false);
        BLViewHolder viewHolder = new BLViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BLViewHolder holder, int position) {
        Booklog booklog = blList.get(position);
        holder.setBooklog(booklog);
        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return blList.size();
    }



}
