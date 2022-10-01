package com.example.partnersincode.optimedia.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.SeriesLog;

import java.util.List;

public class SeriesLogAdapter extends RecyclerView.Adapter<SeriesLogAdapter.SeriesLogViewHolder>{
    private final List<SeriesLog> seriesLogList;
    private View.OnClickListener onClickListener;

    public SeriesLogAdapter(List<SeriesLog> seriesLogList) {
        this.seriesLogList = seriesLogList;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public static class SeriesLogViewHolder extends RecyclerView.ViewHolder {
        public SeriesLog seriesLog;
        public TextView txtSLogReTitle,txtSLogReTime,txtSLogReNote;
        public LinearLayout linLayoutSLogRecycler;
        
        public SeriesLogViewHolder(@NonNull View itemView) {
            super(itemView);

            txtSLogReTitle = itemView.findViewById(R.id.txtSLogReTitle);
            txtSLogReTime = itemView.findViewById(R.id.txtSLogReTime);
            txtSLogReNote = itemView.findViewById(R.id.txtSLogReNote);
            linLayoutSLogRecycler = itemView.findViewById(R.id.linLayoutSLogRecycler);
        }

        public void setSeriesLog(SeriesLog seriesLog) {
            this.seriesLog = seriesLog;

            String title = "Season " + seriesLog.getSeason()+" - Episode "+seriesLog.getEpisode();
            txtSLogReTitle.setText(title);
            txtSLogReTime.setText(seriesLog.getS_timestamp());
            txtSLogReNote.setText(seriesLog.getS_note());
        }
    }

    @NonNull
    @Override
    public SeriesLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_series_log,parent, false);
        return new SeriesLogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesLogViewHolder holder, int position) {
        SeriesLog seriesLog = seriesLogList.get(position);
        holder.setSeriesLog(seriesLog);
        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return seriesLogList.size();
    }

    public void addSeriesLog(SeriesLog seriesLog){
        seriesLogList.add(seriesLog);
    }
}
