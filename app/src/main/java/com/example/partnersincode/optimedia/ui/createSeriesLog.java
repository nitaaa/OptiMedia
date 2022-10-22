package com.example.partnersincode.optimedia.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.adapters.SeriesLogAdapter;
import com.example.partnersincode.optimedia.models.Genre;
import com.example.partnersincode.optimedia.models.Library;
import com.example.partnersincode.optimedia.models.Series;
import com.example.partnersincode.optimedia.models.SeriesLog;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link createSeriesLog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class createSeriesLog extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "createSeriesLog";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Series series;
    private SeriesLogAdapter.SeriesLogViewHolder prevSelected, viewHolder;

    public createSeriesLog() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment createSeriesLog.
     */
    // TODO: Rename and change types and number of parameters
    public static createSeriesLog newInstance(String param1, String param2) {
        createSeriesLog fragment = new createSeriesLog();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            series = bundle.getParcelable("seriesInfo");
            Log.d(TAG, "onCreate: series passed: "+series.getTitle());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_create_series_log, container, false);
        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());
        ArrayList<SeriesLog> seriesLogs = dbHandler.getAllSeriesLogByID(series.getSeriesID());
        RecyclerView recyclerSeriesLog = rootView.findViewById(R.id.recyclerSeriesLog);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerSeriesLog.setLayoutManager(layoutManager);
        SeriesLogAdapter adapter = new SeriesLogAdapter(seriesLogs);
        recyclerSeriesLog.setAdapter(adapter);

        //Fill in series info
        TextView txtSLogSTitle,txtSLogSGenre,txtSLogLink;
        txtSLogSTitle = rootView.findViewById(R.id.txtMovie);
        txtSLogSGenre = rootView.findViewById(R.id.txtGameGenre);
        txtSLogLink = rootView.findViewById(R.id.txtLink);

        Switch switchSLogFavourite,switchSLogStarted,switchSLogComplete;
        switchSLogFavourite = rootView.findViewById((R.id.switchSLogFavourite));
        switchSLogStarted = rootView.findViewById((R.id.switchSLogStarted));
        switchSLogComplete = rootView.findViewById((R.id.switchSLogComplete));

        ArrayList<Genre> genres = dbHandler.getGenres();
        txtSLogSTitle.setText(series.getTitle());
        txtSLogSGenre.setText(genres.get(series.getGenreID()).toString());
        txtSLogLink.setText(series.getLink());

        switchSLogFavourite.setChecked(series.getFavourite());
        switchSLogFavourite.setOnCheckedChangeListener((compoundButton, b) -> {
            series.setFavourite(b);
            dbHandler.updateSeries(series);
        });
        switchSLogStarted.setChecked(series.getStarted());
        switchSLogStarted.setOnCheckedChangeListener((compoundButton, b) -> {
            series.setStarted(b);
            dbHandler.updateSeries(series);
        });
        switchSLogComplete.setChecked(series.getComplete());
        switchSLogComplete.setOnCheckedChangeListener((compoundButton, b) -> {
            series.setComplete(b);
            dbHandler.updateSeries(series);
        });

        EditText edtTxtSLSeason,edtTxtSLogEpisode,edtTxtSLogTime,edtTxtSLogNote;
        edtTxtSLSeason = rootView.findViewById(R.id.edtTxtSLSeason);
        edtTxtSLogEpisode = rootView.findViewById(R.id.edtTxtSLogEpisode);
        edtTxtSLogTime = rootView.findViewById(R.id.edtTxtSLogTime);
        edtTxtSLogNote = rootView.findViewById(R.id.edtTxtSLogNote);

        Button btnAddSeriesLog = rootView.findViewById(R.id.btnAddSeriesLog);
        btnAddSeriesLog.setOnClickListener(view -> {
            SeriesLog seriesLog = new SeriesLog(series.getSeriesID(), edtTxtSLSeason.getText().toString(), edtTxtSLogEpisode.getText().toString(),
                     edtTxtSLogNote.getText().toString(), edtTxtSLogTime.getText().toString());
            int id = dbHandler.createSeriesLog(seriesLog);
            seriesLog.setSL_ID(id);
            adapter.addSeriesLog(seriesLog);

            recyclerSeriesLog.scrollToPosition(0);
            edtTxtSLSeason.setText("");
            edtTxtSLogEpisode.setText("");
            edtTxtSLogNote.setText("");
            edtTxtSLogTime.setText("");
        });

        Button btnShareSeries = rootView.findViewById(R.id.btnShareSeries);
        btnShareSeries.setOnClickListener(view -> {
            Library library = new Library();
            //TODO Not sure how to pass this? create a new library in db then pass the library?
            Bundle bundle = new Bundle();
            bundle.putParcelable("seriesInfo", series);
            Navigation.findNavController(view).navigate(R.id.nav_shareLibrary, bundle);
        });

        Button btnDeleteSeries = rootView.findViewById(R.id.btnDeleteSeries);
        btnDeleteSeries.setOnClickListener(view -> {
            //TODO ADRIAAN - method for deleting series from library
        });

        //view the log content
        adapter.setOnClickListener(view -> {
            viewHolder = (SeriesLogAdapter.SeriesLogViewHolder) recyclerSeriesLog.findContainingViewHolder(view);
            try {
                if (prevSelected != null && prevSelected != viewHolder) {
                    LinearLayout prevLinearLayout = prevSelected.itemView.findViewById(R.id.linLayoutSLogRecycler);
                    prevLinearLayout.setVisibility(LinearLayout.GONE);
                }
                SeriesLog seriesLog = viewHolder.seriesLog;

                //Toggle Visibility
                if (viewHolder.linLayoutSLogRecycler.getVisibility() == LinearLayout.GONE) {
                    viewHolder.linLayoutSLogRecycler.setVisibility(LinearLayout.VISIBLE);
                } else {
                    viewHolder.linLayoutSLogRecycler.setVisibility(LinearLayout.GONE);
                }
                Log.d(TAG, "clicked button ");
                prevSelected = viewHolder;
            } catch (Exception e){
                Log.d(TAG, "onCreateView: "+e.getMessage());
                e.printStackTrace();
            }
        });

        return rootView;
    }
}