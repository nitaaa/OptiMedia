package com.example.partnersincode.optimedia.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.adapters.MovieLogAdapter;
import com.example.partnersincode.optimedia.models.GameLog;
import com.example.partnersincode.optimedia.models.MovieLog;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link manageGameLog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class manageGameLog extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public manageGameLog() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment manageGameLog.
     */ /*
    // TODO: Rename and change types and number of parameters
    public static manageGameLog newInstance(String param1, String param2) {
        manageGameLog fragment = new manageGameLog();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    } */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int GameID;
        Bundle getBundle = this.getArguments();
        if (getBundle != null) {
            GameID = getBundle.getInt("getGameID");
        }else
            GameID=-1;

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_manage_game_log, container, false);
        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());

        List<GameLog> logs = dbHandler.getGameLog();
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerGameLog);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        MovieLogAdapter adapter = new MovieLogAdapter(logs);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener( view -> {
            MovieLogAdapter.MovieLogViewHolder viewHolder = (MovieLogAdapter.MovieLogViewHolder) recyclerView.findContainingViewHolder(view);
            MovieLog movieLog = viewHolder.movieLog;
            //Toast.makeText(this.getContext(), movieLog.getMovieID()),Toast.LENGTH_LONG).show();

            Bundle bundle = new Bundle();
            bundle.putSerializable("movieLogInfo",movieLog);
            bundle.putString("Intent", "Edit");
            Navigation.findNavController(view).navigate(R.id.nav_createMovieLog, bundle);
        });

        Button btnCreateMovieLog = rootView.findViewById(R.id.btnCreateGameLog);
        btnCreateMovieLog.setOnClickListener(view ->{
            Bundle bundle = new Bundle();
            bundle.putInt("GameID",GameID);
            bundle.putString("Intent", "Add");
            Navigation.findNavController(view).navigate(R.id.nav_createMovieLog,bundle);
        });

        return rootView;
    }
}