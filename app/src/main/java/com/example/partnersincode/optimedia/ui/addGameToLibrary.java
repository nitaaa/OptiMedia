package com.example.partnersincode.optimedia.ui;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.adapters.AddGameAdapter;
import com.example.partnersincode.optimedia.models.Game;
import com.example.partnersincode.optimedia.models.Library;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addGameToLibrary#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addGameToLibrary extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public addGameToLibrary() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addGameToLibrary.
     */
    // TODO: Rename and change types and number of parameters
    public static addGameToLibrary newInstance(String param1, String param2) {
        addGameToLibrary fragment = new addGameToLibrary();
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
        View rootView = inflater.inflate(R.layout.fragment_add_game_to_library, container, false);
        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());

        //get libraries for spinner
        ArrayList<Library> libraries = dbHandler.getGameLibraries();
        ArrayAdapter<Library> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, libraries);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        Spinner spinLibrary = (Spinner) rootView.findViewById(R.id.spinLibrary);
        spinLibrary.setAdapter(adapter);

        TextView edtxtSearch = rootView.findViewById(R.id.edtxtGame);

        // recycler viewer
        List<Game> games = dbHandler.getGames();
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerGames);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        AddGameAdapter gameAdapter = new AddGameAdapter(games);
        recyclerView.setAdapter(gameAdapter);

        gameAdapter.setOnClickListener( view -> {
            AddGameAdapter.AddGameViewHolder viewHolder = (AddGameAdapter.AddGameViewHolder) recyclerView.findContainingViewHolder(view);
            //Toast.makeText(this.getContext(), author.getFullName(),Toast.LENGTH_LONG).show();
            //TODO:
            if (viewHolder != null) {
                viewHolder.setSelected();
            }
        });

        Button btnSearch = rootView.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(view ->
                {
                    //List<Game> games2 = dbHandler.getGames(edtxtSearch.toString());
               //    recyclerView.setLayoutManager(layoutManager);
                 //   recyclerView.setAdapter(gameAdapter);

                    List<Game> games2 = dbHandler.getGames(edtxtSearch.getText().toString());
                    RecyclerView recyclerView2 = rootView.findViewById(R.id.recyclerGames);

                    RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext());
                    recyclerView2.setLayoutManager(layoutManager2);
                    AddGameAdapter gameAdapter2 = new AddGameAdapter(games2);
                    recyclerView2.setAdapter(gameAdapter2);
                });

        Button btnAdd = rootView.findViewById(R.id.btnAddGameLog);
        btnAdd.setOnClickListener(view ->
        {
            Library library = (Library) spinLibrary.getSelectedItem();

            for (Game added :
                   gameAdapter.getSelectedObjects()) {

                dbHandler.createGameLibrary(added, library);
            }
            Toast.makeText(getContext(), getString(R.string.toastAddTogameLibrary,library.getLibraryName()),Toast.LENGTH_SHORT).show();

        });

        return rootView;
    }
}