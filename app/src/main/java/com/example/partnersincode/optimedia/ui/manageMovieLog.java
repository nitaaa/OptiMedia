package com.example.partnersincode.optimedia.ui;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.adapters.MovieLogAdapter;
import com.example.partnersincode.optimedia.models.Movie;
import com.example.partnersincode.optimedia.models.MovieLog;

import java.util.List;
import java.util.stream.Collectors;


public class manageMovieLog extends Fragment {

  /*  // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
*/
    public manageMovieLog() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment manageMovieLog.
     *//*
    // TODO: Rename and change types and number of parameters
    public static manageMovieLog newInstance(String param1, String param2) {
        manageMovieLog fragment = new manageMovieLog();
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
    }*/

    Movie movie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int MovieID;
        Bundle getBundle = this.getArguments();
        if (getBundle != null) {
            movie = getBundle.getParcelable("movieInfo");
            MovieID = movie.getMovieID();
        }else
            MovieID=-1;

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_manage_movie_log, container, false);
        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());

        List<MovieLog> logs = dbHandler.getMovieLog().stream().filter(movieLog -> movieLog.getMovieID()==MovieID).collect(Collectors.toList());
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
            bundle.putParcelable("movieInfo",movie);
            bundle.putString("Intent", "Edit");
            Navigation.findNavController(view).navigate(R.id.nav_createMovieLog, bundle);
        });

        Button btnCreateMovieLog = rootView.findViewById(R.id.btnCreateGameLog);
       btnCreateMovieLog.setOnClickListener(view ->{
           Bundle bundle = new Bundle();
           bundle.putParcelable("movieInfo",movie);
           bundle.putString("Intent", "Add");
           Navigation.findNavController(view).navigate(R.id.nav_createMovieLog,bundle);
       });

        return rootView;
    }
}