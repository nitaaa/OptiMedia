package com.example.partnersincode.optimedia.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Game;
import com.example.partnersincode.optimedia.models.GameLog;
import com.example.partnersincode.optimedia.models.Genre;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link createGameLog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class createGameLog extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Game game;
    private int GameID;
    private Boolean editing;
    private GameLog gameLog;
    private static final String TAG = "CreateGameLog";

    public createGameLog() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment createGameLog.
     */
    // TODO: Rename and change types and number of parameters
    public static createGameLog newInstance(String param1, String param2) {
        createGameLog fragment = new createGameLog();
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
        if (bundle.getString("Intent").equals("Edit")) {
            game =  bundle.getParcelable("gameInfo");
            gameLog = (GameLog) bundle.getSerializable("gameLogInfo");
            editing = true;
        } else {
            game =  bundle.getParcelable("gameInfo");
            gameLog = (GameLog) bundle.getSerializable("gameLogInfo");
            editing = false;
        }
        GameID = game.getGameID();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_create_game_log, container, false);
        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());

        TextView edtxtTitle = rootView.findViewById(R.id.edtxtTitle);
        TextView edtxtNote = rootView.findViewById(R.id.edtxtNote);
        Button btnAddGameLog = rootView.findViewById(R.id.btnAddGameLog);


        TextView txtTitle = rootView.findViewById(R.id.txtGame);
        TextView txtGenre = rootView.findViewById(R.id.txtGameGenre);
        TextView txtType = rootView.findViewById(R.id.txtGameType);

        ArrayList<Genre> genres = dbHandler.getGenres();
        txtTitle.setText(game.getGameTitle());
        txtGenre.setText(genres.get(game.getGenreID()).toString());
        txtType.setText(game.getGameType());

        Switch switchSLogFavourite,switchSLogStarted,switchSLogComplete;
        switchSLogFavourite = rootView.findViewById((R.id.switchSLogFavourite));
        switchSLogStarted = rootView.findViewById((R.id.switchSLogStarted));
        switchSLogComplete = rootView.findViewById((R.id.switchSLogComplete));

        switchSLogFavourite.setChecked(game.isFavourite());
        switchSLogFavourite.setOnCheckedChangeListener((compoundButton, b) -> {
            game.setFavourite(b);
            dbHandler.updateGame(game);
        });
        switchSLogStarted.setChecked(game.isStarted());
        switchSLogStarted.setOnCheckedChangeListener((compoundButton, b) -> {
            game.setStarted(b);
            dbHandler.updateGame(game);
        });
        switchSLogComplete.setChecked(game.isCompleted());
        switchSLogComplete.setOnCheckedChangeListener((compoundButton, b) -> {
            game.setCompleted(b);
            dbHandler.updateGame(game);
        });

        if (editing){
            btnAddGameLog.setText("Edit Game Log");
            edtxtTitle.setText(gameLog.getGLTitle());
            edtxtNote.setText(gameLog.getGLNote());
            btnAddGameLog.setOnClickListener(view -> {
                String title=edtxtTitle.getText().toString();
                String note=edtxtNote.getText().toString();
                dbHandler.updateGameLog(gameLog.getGL_ID(),title, note);

                Toast.makeText(this.getContext(), "Game Log Edited " , Toast.LENGTH_LONG).show();
                edtxtNote.setText("");
                edtxtTitle.setText("");
                getActivity().onBackPressed();
            });
        } else {
            btnAddGameLog.setOnClickListener(view -> {
                GameLog log = new GameLog();
                log.setGameID(GameID);
                log.setGLTitle(edtxtTitle.getText().toString());
                log.setGLNote(edtxtNote.getText().toString());
                dbHandler.addGameLog(log);

                Toast.makeText(this.getContext(), "Game Log Added", Toast.LENGTH_LONG).show();
                edtxtTitle.setText("");
                edtxtNote.setText("");
                getActivity().onBackPressed();
            });
        }

        return rootView;
    }
}