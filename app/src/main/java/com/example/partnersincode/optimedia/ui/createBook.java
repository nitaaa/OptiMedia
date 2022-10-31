package com.example.partnersincode.optimedia.ui;

import android.content.DialogInterface;
import android.os.Bundle;
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

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Author;
import com.example.partnersincode.optimedia.models.Book;
import com.example.partnersincode.optimedia.models.Genre;

import java.util.List;

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

    //Variables saved and used when adding a new movie
    DatabaseHandler dbHandler;
    List<Genre> genres;
    ArrayAdapter<Genre> adapter;
    List<Author> authors;
    ArrayAdapter<Author> adapter2;

    Switch fav;
    Switch com;
    Switch start;

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

        dbHandler = new DatabaseHandler(this.getContext());

        TextView edtxtBookISBN = rootView.findViewById(R.id.edtxtName);
        TextView edtxtBookTitle = rootView.findViewById(R.id.edtxtSurname);

        fav = rootView.findViewById(R.id.A08400_favourite);
        start = rootView.findViewById(R.id.A08400_started);
        com = rootView.findViewById(R.id.A08400_completed);

        //get genre for spinner
        genres = dbHandler.getGenres();
        adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, genres);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        Spinner spinGenre = (Spinner) rootView.findViewById(R.id.spinGenre);
        spinGenre.setAdapter(adapter);

        //get author for spinner
        authors = dbHandler.getAuthors();
        adapter2 = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, authors);
        adapter2.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        Spinner spinAuthor = (Spinner) rootView.findViewById(R.id.spinAuthor);
        spinAuthor.setAdapter(adapter2);

        //add new genre or author
        rootView.findViewById(R.id.btnAddGenre).setOnClickListener(this::getNewGenreName);
        rootView.findViewById(R.id.btnAddAuthor).setOnClickListener(this::getNewAuthorName);

        Button btnAddNewBook = rootView.findViewById(R.id.btnSaveBook);
        btnAddNewBook.setOnClickListener(view -> {
            Book newBook = new Book();
            newBook.setISBN(edtxtBookISBN.getText().toString());
            newBook.setBookTitle(edtxtBookTitle.getText().toString());
            Genre curGenre = (Genre) spinGenre.getSelectedItem();
            newBook.setGenreID(curGenre.getGenreID());
            Author curAuthor = (Author) spinAuthor.getSelectedItem();
            newBook.setAuthorID(curAuthor.getAuthorID());

            boolean bFav = fav.isChecked();
            boolean bStarted = start.isChecked();
            boolean bCom = com.isChecked();
            newBook.setFavourite(bFav);
            newBook.setStarted(bStarted);
            newBook.setCompleted(bCom);

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

    private void getNewGenreName(View view)
    {
        LayoutInflater li = LayoutInflater.from(getContext());
        View promptsView = li.inflate(R.layout.prompt_new_genre, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getContext());

        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);


        alertDialogBuilder
                .setCancelable(false)
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
                                dbHandler.addGenre(userInput.getText().toString());
                                Genre genre = dbHandler.getGenre(userInput.getText().toString());
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

    private void getNewAuthorName(View view)
    {
        LayoutInflater li = LayoutInflater.from(getContext());
        View promptsView = li.inflate(R.layout.prompt_new_author, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getContext());

        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        final EditText userInput2 = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput2);


        alertDialogBuilder
                .setCancelable(false)
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
                                dbHandler.createNewAuthor(new Author(-1, userInput.getText().toString(), userInput2.getText().toString()));
                                Author author = dbHandler.getAuthorByName(userInput.getText().toString(), userInput2.getText().toString());
                                authors.add(author);
                                adapter2.notifyDataSetChanged();


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