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
import com.example.partnersincode.optimedia.models.Author;
import com.example.partnersincode.optimedia.models.Book;
import com.example.partnersincode.optimedia.models.Genre;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link createBook#newInstance} factory method to
 * create an instance of this fragment.
 */
public class createBook extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static final String TAG = "CreateBook";

    public createBook() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment createBook.
     */
    // TODO: Rename and change types and number of parameters
    public static createBook newInstance(String param1, String param2) {
        createBook fragment = new createBook();
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
        View rootView = inflater.inflate(R.layout.fragment_create_book, container, false);

        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());

        TextView edtxtBookISBN = rootView.findViewById(R.id.edtxtName);
        TextView edtxtBookTitle = rootView.findViewById(R.id.edtxtSurname);

        //get genre for spinner
        ArrayList<Genre> genres = dbHandler.getGenres();
        ArrayAdapter<Genre> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, genres);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        Spinner spinGenre = (Spinner) rootView.findViewById(R.id.spinGenre);
        spinGenre.setAdapter(adapter);

        //get author for spinner
        ArrayList<Author> authors = dbHandler.getAuthors();
        ArrayAdapter<Author> adapter2 = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, authors);
        adapter2.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        Spinner spinAuthor = (Spinner) rootView.findViewById(R.id.spinAuthor);
        spinAuthor.setAdapter(adapter2);

        Button btnAddNewBook = rootView.findViewById(R.id.btnAddNewAuthor);
        btnAddNewBook.setOnClickListener(view -> {
            Book newBook = new Book();
            newBook.setISBN(edtxtBookISBN.getText().toString());
            newBook.setBookTitle(edtxtBookTitle.getText().toString());
            Genre curGenre = (Genre) spinGenre.getSelectedItem();
            newBook.setGenreID(curGenre.getGenreID());
            Author curAuthor = (Author) spinAuthor.getSelectedItem();
            newBook.setFavourite(false);
            newBook.setStarted(false);
            newBook.setCompleted(false);

            int i = dbHandler.createNewBook(newBook);
            Log.d(TAG, "Book Added:" + newBook.getBookTitle());

            if (i > 0){
                Toast.makeText(this.getContext(), "Book Added: " + newBook.getBookTitle(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this.getContext(), "Unable to add book.", Toast.LENGTH_LONG).show();
            }

            edtxtBookISBN.setText("");
            edtxtBookTitle.setText("");
            spinGenre.setSelection(0);
            spinAuthor.setSelection(0);

            edtxtBookTitle.requestFocus();

        });

        return rootView;
    }
}