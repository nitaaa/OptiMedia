package com.example.partnersincode.optimedia.ui;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

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

public class AddNewMovie extends Fragment {

//    private AddNewMovieViewModel mViewModel;

    private EditText movieTitle;
    private EditText movieLink;
    private Spinner spinGenre;



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
        Button setting = root.findViewById(R.id.A08400_btnCancel);
        setting.setOnClickListener(this::onCancelClicked);
        setting = root.findViewById(R.id.A08400_btnSave);
        setting.setOnClickListener(this::onSaveClicked);

        //Set up code for setting Genre
        DatabaseHandler db = new DatabaseHandler(this.getContext());


        ArrayList<Genre> genres = db.getGenres();
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
        String title = movieTitle.getText().toString();
        String link = movieLink.getText().toString();
        Genre selGenre =  (Genre) spinGenre.getSelectedItem();



        DatabaseHandler handler = new DatabaseHandler(this.getContext());
        SQLiteDatabase db = handler.getWritableDatabase();

        ContentValues movie = new ContentValues();

        movie.put("movieTitle",title);
        movie.put("genreID",selGenre.getGenreID());
        long id = db.insertWithOnConflict("Movie",null,movie, SQLiteDatabase.CONFLICT_IGNORE);

        String SQL = String.format("INSERT INTO WatchListItem (movieID, link)" +
                "\n VALUES (%d, \"%s\" )",id,link);

        db.execSQL(SQL);

        Toast.makeText(getActivity(),getResources().getString(R.string.message_movieAdded,title),Toast.LENGTH_SHORT).show();
        //Close the window
        onCancelClicked(view);
    }

    public void onCancelClicked(View view) {
//        Navigation.findNavController(view).navigate(R.id.nav_home);
        getActivity().onBackPressed();
    }

}