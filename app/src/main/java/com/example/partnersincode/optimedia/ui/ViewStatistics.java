package com.example.partnersincode.optimedia.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewStatistics#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewStatistics extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ViewStatistics() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewStatistics.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewStatistics newInstance(String param1, String param2) {
        ViewStatistics fragment = new ViewStatistics();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_statistics, container, false);
        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());

        TextView txtSPG = rootView.findViewById(R.id.txtSPG);
        //todo see DatabaseHandler.java line 1530
        //txtSPG.setText(txtSPG.getText() + dbHandler.statsSeriesPopGenre());

        TextView txtFS = rootView.findViewById(R.id.txtFS);
        txtFS.setText(txtFS.getText() + Integer.toString(dbHandler.statsFinishedSeries()));

        TextView txtFM = rootView.findViewById(R.id.txtFM);
        txtFM.setText(txtFM.getText() + Integer.toString(dbHandler.statsFinishedMovies()));

        TextView txtFG = rootView.findViewById(R.id.txtFG);
        txtFG.setText(txtFG.getText() + Integer.toString(dbHandler.statsFinishedGames()));

        TextView txtFB = rootView.findViewById(R.id.txtFB);
        txtFB.setText(txtFB.getText() + Integer.toString(dbHandler.statsFinishedBooks()));

        TextView txtSLC = rootView.findViewById(R.id.txtSLC);
        txtSLC.setText(txtSLC.getText() + Integer.toString(dbHandler.statsSeriesLogCount()));

        TextView txtMLC = rootView.findViewById(R.id.txtMLC);
        txtMLC.setText(txtMLC.getText() + Integer.toString(dbHandler.statsMovieLogCount()));

        TextView txtGLC = rootView.findViewById(R.id.txtGLC);
        txtGLC.setText(txtGLC.getText() + Integer.toString(dbHandler.statsGameLogCount()));

        TextView txtBLC = rootView.findViewById(R.id.txtBLC);
        txtBLC.setText(txtBLC.getText() + Integer.toString(dbHandler.statsBookLogCount()));

        return rootView;
    }
}