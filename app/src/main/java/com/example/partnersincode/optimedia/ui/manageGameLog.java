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

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.adapters.GameLogAdapter;
import com.example.partnersincode.optimedia.models.Game;
import com.example.partnersincode.optimedia.models.GameLog;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link manageGameLog#} factory method to
 * create an instance of this fragment.
 */
public class manageGameLog extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Game game;
    private static final String TAG = "manageGameLog";

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
     * @param
     * @param
     * @return A new instance of fragment manageGameLog.
     */
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
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            game = bundle.getParcelable("gameInfo");
            Log.d(TAG, "onCreate: series passed: "+game.getGameTitle());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().getWindow().setTitle(TAG);

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_manage_game_log, container, false);
        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());

        List<GameLog> logs = dbHandler.getGameLogs();
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerGameLog);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        GameLogAdapter adapter = new GameLogAdapter(logs);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener( view -> {
            GameLogAdapter.GameLogViewHolder viewHolder = (GameLogAdapter.GameLogViewHolder) recyclerView.findContainingViewHolder(view);
            GameLog gameLog = viewHolder.gameLog;
            //Toast.makeText(this.getContext(), gameLog.getGameID()),Toast.LENGTH_LONG).show();

            Bundle bundle = new Bundle();
            bundle.putParcelable("gameInfo",game);
            bundle.putSerializable("gameLogInfo",gameLog);
            bundle.putString("Intent", "Edit");
            Navigation.findNavController(view).navigate(R.id.nav_createGameLog, bundle);
        });

        Button btnCreateGameLog = rootView.findViewById(R.id.btnCreateGameLog);
        btnCreateGameLog.setOnClickListener(view ->{
            Bundle bundle = new Bundle();
            bundle.putString("Intent", "Add");
            bundle.putParcelable("gameInfo",game);
            Navigation.findNavController(view).navigate(R.id.nav_createGameLog,bundle);
        });

        return rootView;
    }
}