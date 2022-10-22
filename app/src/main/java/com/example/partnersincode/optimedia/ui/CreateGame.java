package com.example.partnersincode.optimedia.ui;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Game;
import com.example.partnersincode.optimedia.models.Genre;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateGame#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateGame extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "CreateGame";
    private Spinner spinGenre;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateGame() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment create_game.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateGame newInstance(String param1, String param2) {
        CreateGame fragment = new CreateGame();
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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_create_game, container, false);

        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());
        //get genre for spinner
        ArrayList<Genre> genres = dbHandler.getGenres();
        ArrayAdapter<Genre> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, genres);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        spinGenre = (Spinner) rootView.findViewById(R.id.spinGenre);
        spinGenre.setAdapter(adapter);
        TextView edtxtGameName = rootView.findViewById(R.id.edtxtGameName);
        TextView edtxtGameType = rootView.findViewById(R.id.edtxtGameType);

        Switch favourite = rootView.findViewById(R.id.cg_favourite);
        Switch started = rootView.findViewById(R.id.cg_started);
        Switch complete = rootView.findViewById(R.id.cg_complete);

        Button btncg_genre = rootView.findViewById(R.id.btncg_genre);
        btncg_genre.setOnClickListener(this::getNewGenreName);

        Button btnAddNewGame = rootView.findViewById(R.id.btnAddNewGame);
        btnAddNewGame.setOnClickListener(view -> {
            Game newGame = new Game();
            newGame.setGameTitle(edtxtGameName.getText().toString());
            newGame.setGameType(edtxtGameType.getText().toString());
            Genre curGenre = (Genre) spinGenre.getSelectedItem();
            newGame.setGenreID(curGenre.getGenreID());
            newGame.setFavourite(favourite.isChecked());
            newGame.setStarted(started.isChecked());
            newGame.setCompleted(complete.isChecked());

            int i = dbHandler.createNewGame(newGame);
            Log.d(TAG, "Game Added:" + newGame.getGameTitle());

            if (i > 0){
                Toast.makeText(this.getContext(), "Game Added: " + newGame.getGameTitle(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this.getContext(), "Unable to add game.", Toast.LENGTH_LONG).show();
            }

            edtxtGameName.setText("");
            edtxtGameType.setText("");
            spinGenre.setSelection(0);

            edtxtGameName.requestFocus();

        });

        return rootView;
    }

    private void getNewGenreName(View view)
    {
        LayoutInflater li = LayoutInflater.from(getContext());
        View promptsView = li.inflate(R.layout.prompt_new_genre, null);
        DatabaseHandler handler = new DatabaseHandler(this.getContext());
        List<Genre> genres = handler.getGenres();
        ArrayAdapter<Genre> adapter = new ArrayAdapter<>(this.getContext(),android.R.layout.simple_spinner_dropdown_item,genres);

        spinGenre.setAdapter(adapter);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getContext());

        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);


        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text

                                if(userInput.getText().toString().equals(""))
                                {
                                    Toast.makeText(getContext(),getString(R.string.invalidGenre), Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                }
                                handler.addGenre(userInput.getText().toString());
                                Genre genre = handler.getGenre(userInput.getText().toString());
                                genres.add(genre);
                                adapter.notifyDataSetChanged();


                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        }).show();
    }
}