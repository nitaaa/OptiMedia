package com.example.partnersincode.optimedia.ui;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.controllers.AddToWatchLibraryAdaptor;
import com.example.partnersincode.optimedia.models.Library;
import com.example.partnersincode.optimedia.models.Movie;
import com.example.partnersincode.optimedia.models.WatchObject;

import java.sql.SQLData;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddToWatchLibrary#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddToWatchLibrary extends Fragment {


    //UI elements
    EditText searchField;
    Spinner selectedLibrary;
    RecyclerView recView;

    //Adaptor for recycler view
    AddToWatchLibraryAdaptor adaptor;


    public AddToWatchLibrary() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddToWatchLibrary.
     */
    // TODO: Rename and change types and number of parameters
    public static AddToWatchLibrary newInstance(String param1, String param2) {
        AddToWatchLibrary fragment = new AddToWatchLibrary();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_add_to_watch_library, container, false);

        //Get references to UI elements
        setUpUI(root);

        return root;
    }


    private void setUpUI(View root)
    {
        searchField = root.findViewById(R.id.A02310_searchField);
        recView = root.findViewById(R.id.A02310_RecView);
        selectedLibrary = root.findViewById(R.id.A02310_spinnerLib);

        DatabaseHandler db = new DatabaseHandler(this.getContext());
        ArrayList<Library> watchLibraries = db.getWatchLibraries();
        ArrayAdapter<Library> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_dropdown_item,watchLibraries);
        selectedLibrary = root.findViewById(R.id.A02310_spinnerLib);
        selectedLibrary.setAdapter(adapter);

        //Set up button actions
        Button current = root.findViewById(R.id.A02310_SearchButton);
        current.setOnClickListener(this::onSearchClicked);

        current = root.findViewById(R.id.A02310_AddSelected);
        current.setOnClickListener(this::onAddSelectedClicked);

        //Set up the recyclerView
        setUpRecyclerView();


    }

    private void setUpRecyclerView() {

        //Initialize adapter
        adaptor = new AddToWatchLibraryAdaptor(new DatabaseHandler(this.getContext()));

        //set up recycler layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recView.setAdapter(adaptor);
        recView.setLayoutManager(layoutManager);


        //set the onclick listener for each cardview
        //This will set the viewHolder as selected and will change colour
        adaptor.setOnClickListener(view -> {
            AddToWatchLibraryAdaptor.WatchObjectViewHolder viewHolder = (AddToWatchLibraryAdaptor.WatchObjectViewHolder) recView.findContainingViewHolder(view);

            if (viewHolder != null) {
                viewHolder.setSelected();
            }
        });

    }

    private void onAddSelectedClicked(View view)
    {
        addObjectsToLibrary();
        getActivity().onBackPressed();
    }

    private void onSearchClicked(View view)
    {
        String searchTerm = searchField.getText().toString();

        if(searchTerm.equals(""))
        {
            Toast.makeText(getContext(),R.string.invalidSearchTerm,Toast.LENGTH_SHORT).show();


        }

        //Do some thing with this text to call another use case
        Toast.makeText(getContext(), "This is where A07000 will be called to search for "+searchTerm,Toast.LENGTH_SHORT).show();

    }

    //This adds to the database, assuming the movie/series and watchlist item already exists
    @SuppressLint("Range")
    private void addObjectsToLibrary()
    {
        Library library = (Library) selectedLibrary.getSelectedItem();

        for (WatchObject added :
                adaptor.getSelectedWatchObjects()) {

            String idFieldName = "";

            if (added instanceof Movie) idFieldName = "movieID";
            else idFieldName = "seriesID";

            //get the id, if the id is -1, then we got the wrong object and thus the wrong number
            int objectID = added.getID();
            if (objectID == -1) return;

            //Get the id of the watchlistitem holding this movie/series object
            DatabaseHandler db = new DatabaseHandler(getContext());
            int WLI_ID = db.getWLI_ID(idFieldName,objectID);

            //Add movie or series to watchlibrary
            db.addWLItoLibrary(library,WLI_ID);

        }
    }


}