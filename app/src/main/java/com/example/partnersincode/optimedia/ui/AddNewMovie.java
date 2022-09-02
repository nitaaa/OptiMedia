package com.example.partnersincode.optimedia.ui;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
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
import com.example.partnersincode.optimedia.models.Genre;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class AddNewMovie extends Fragment {

//    private AddNewMovieViewModel mViewModel;

    private EditText movieTitle;
    private EditText movieLink;
    private Spinner spinGenre;


    //Variables saved and used when adding a new movie
    DatabaseHandler handler;
    String title;
    String link;
    Genre selGenre;



    public static AddNewMovie newInstance() {
        return new AddNewMovie();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.add_new_movie_fragment, container, false);

        //UI elements
        movieTitle = root.findViewById(R.id.A08400_txtMovieTitle);
        movieLink = root.findViewById(R.id.A08400_txtMovieLink);
        //setting the onclick listeners manually, because XML did not want to find methods, idk why
        Button setting = root.findViewById(R.id.A08400_btnSave);
        setting.setOnClickListener(this::onSaveClicked);

        //Set up code for setting Genre
        handler = new DatabaseHandler(this.getContext());


        ArrayList<Genre> genres = handler.getGenres();
        ArrayAdapter<Genre> adapter = new ArrayAdapter<>(this.getContext(),android.R.layout.simple_spinner_dropdown_item,genres);
        spinGenre = root.findViewById(R.id.A04800_spinGenre);
        spinGenre.setAdapter(adapter);


        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(AddNewMovieViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public void onSaveClicked(View view)
    {
         title = movieTitle.getText().toString();
         link = movieLink.getText().toString();
         selGenre =  (Genre) spinGenre.getSelectedItem();






        if(title.equals(""))
        {Toast.makeText(this.getContext(), getResources().getString(R.string.invalidMovieTitle),Toast.LENGTH_SHORT).show();
            return;}


        if(handler.isMovieInList(title))
        {
            //If there is a similarly named movie, we show display a message to the user
            showMovieAlreadyExists();

        }



    }

    private void showMovieAlreadyExists()
    {

        AlertDialog movieAlreadyExists = new AlertDialog.Builder(getContext()).create();

        movieAlreadyExists.setMessage(getString(R.string.movieAlreadyInDB,title));
        movieAlreadyExists.setButton(DialogInterface.BUTTON_POSITIVE,getString(R.string.yes), (dialog, which)-> {addMovie();});
        movieAlreadyExists.setButton(DialogInterface.BUTTON_NEGATIVE,getString(R.string.no),(dialog, which) -> {dialog.cancel();});

        movieAlreadyExists.show();


    }

    private void addMovie()
    {
        //Add movie if not in the list
        handler.addMovie(title, link, selGenre);


        Toast.makeText(getActivity(),getResources().getString(R.string.message_movieAdded,title),Toast.LENGTH_SHORT).show();
        //Close the window
        getActivity().onBackPressed();
    }







}