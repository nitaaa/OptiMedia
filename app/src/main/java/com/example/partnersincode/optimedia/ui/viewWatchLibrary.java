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
import android.widget.TextView;
import android.widget.Toast;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.adapters.GameAdapter;
import com.example.partnersincode.optimedia.adapters.WatchObjectAdapter;
import com.example.partnersincode.optimedia.models.Game;
import com.example.partnersincode.optimedia.models.Library;
import com.example.partnersincode.optimedia.models.Movie;
import com.example.partnersincode.optimedia.models.Series;
import com.example.partnersincode.optimedia.models.WatchObject;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link viewWatchLibrary#newInstance} factory method to
 * create an instance of this fragment.
 */
public class viewWatchLibrary extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "viewWatchLibrary";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Library library;

    public viewWatchLibrary() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment viewWatchLibrary.
     */
    // TODO: Rename and change types and number of parameters
    public static viewWatchLibrary newInstance(String param1, String param2) {
        viewWatchLibrary fragment = new viewWatchLibrary();
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
            library = bundle.getParcelable("libraryInfo");
            Log.d(TAG, "onCreate: library passed: "+library.getLibraryName()+", id:"+library.getLibraryID());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_view_watch_library, container, false);
        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());
        List<WatchObject> watchList = dbHandler.getAllWatchItemsLibrary(library.getLibraryID(),"");
        RecyclerView reWatchLibrary = rootView.findViewById(R.id.recyclerWatchLibrary);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        reWatchLibrary.setLayoutManager(layoutManager);
        WatchObjectAdapter adapter = new WatchObjectAdapter(watchList);
        reWatchLibrary.setAdapter(adapter);

        TextView txtWatchLibraryName = rootView.findViewById(R.id.txtWatchLibraryName);
        txtWatchLibraryName.setText(library.getLibraryName());
        //Toast.makeText(this.getContext(), "number of items:" + watchList.size(),Toast.LENGTH_LONG).show();
        adapter.setOnClickListener( view -> {
            WatchObjectAdapter.WatchObjectViewHolder viewHolder = (WatchObjectAdapter.WatchObjectViewHolder) reWatchLibrary.findContainingViewHolder(view);
            Bundle bundle = new Bundle();
            if (viewHolder.movie != null){
                Movie movie = viewHolder.movie;
                bundle.putParcelable("movieInfo", movie);
                Toast.makeText(this.getContext(), movie.getTitle(),Toast.LENGTH_LONG).show();
                // TODO:Navigation from view book library to A02311
                // Navigation.findNavController(view).navigate(R.id., bundle);
            } else if (viewHolder.series != null){
                Series series = viewHolder.series;
                bundle.putParcelable("seriesInfo", series);
                // Toast.makeText(this.getContext(), series.getTitle(),Toast.LENGTH_LONG).show(); //Navigates so no toast
                // Navigation from view book library to A02312
                Navigation.findNavController(view).navigate(R.id.nav_createSeriesLog, bundle);
            }
        });

        //TODO: onclick for edit
        Button imgBtnEditWatchList = rootView.findViewById(R.id.imgBtnEditWatchList);
        imgBtnEditWatchList.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("libraryInfo", library);
            Navigation.findNavController(view).navigate(R.id.nav_editLibrary, bundle);
        });

        //TODO: onclick for add to library - open search for books

        //onclick for share
        Button imgBtnShareWLib = rootView.findViewById(R.id.imgBtnShareWLib);
        imgBtnShareWLib.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("WatchLibraryInfo", library);
            Navigation.findNavController(view).navigate(R.id.nav_shareLibrary, bundle);
        });
        return rootView;
    }
}