package com.example.partnersincode.optimedia.ui;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Library;
import com.example.partnersincode.optimedia.models.MovieLog;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link createMovieLog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class createMovieLog extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private MovieLog movieLog;
    private Boolean editing;
    private static final String TAG = "CreateMovieLog";

    public createMovieLog() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment createMovieLog.
     */
    // TODO: Rename and change types and number of parameters
    public static createMovieLog newInstance(String param1, String param2) {
        createMovieLog fragment = new createMovieLog();
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
            movieLog = bundle.getParcelable("movieLogInfo");
            editing = true;
            Log.d(TAG, "onCreate: movieLog passed: "+movieLog.toString());
        } else {
            editing = false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_create_movie_log, container, false);
        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());

        //TextView edtxtMovieID = rootView.findViewById(R.id.edtxtMovieID);
        TextView edtxtTime = rootView.findViewById(R.id.edtxtTime);
        TextView edtxtNote = rootView.findViewById(R.id.edtxtNote);
        Button btnAddMovieLog = rootView.findViewById(R.id.btnAddMovieLog);

        //edtxtMovieID.setText(movieLog.getMovieID());
        edtxtTime.setText(movieLog.getM_timestamp());
        edtxtNote.setText(movieLog.getM_note());
        btnAddMovieLog.setOnClickListener(view -> {
            //movieLog.setMovieID(Integer.parseInt(edtxtMovieID.getText().toString()));
            movieLog.setM_timestamp(edtxtTime.getText().toString());
            movieLog.setM_note(edtxtNote.getText().toString());

            //TODO: query is probably the problem here
            dbHandler.createNewMovieLog(movieLog);
            Toast.makeText(this.getContext(), "Movie Log added: " + movieLog.toString(), Toast.LENGTH_LONG).show();
        });

        return rootView;
    }
}