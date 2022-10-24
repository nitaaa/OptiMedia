package com.example.partnersincode.optimedia.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


import com.example.partnersincode.optimedia.models.Genre;
import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;

import java.util.ArrayList;

public class CreateSeries extends Fragment {

    DatabaseHandler dbHandler;
    ArrayList<Genre> genres;
    ArrayAdapter<Genre> adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_series_fragment, container, false);

         dbHandler = new DatabaseHandler(this.getContext());
        genres = dbHandler.getGenres();
        adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, genres);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        Spinner spinnerGenre = view.findViewById(R.id.spinnerGenre);
        spinnerGenre.setAdapter(adapter);
        Button btnAddGenre = view.findViewById(R.id.btnAddGenre);
        btnAddGenre.setOnClickListener(this::getNewGenreName);

        Button button = view.findViewById(R.id.btnCreateSeries);
        button.setOnClickListener(v-> {
            String Title = ((EditText) view.findViewById(R.id.edtSeriesTitle)).getText().toString();
            Genre genre = (Genre) spinnerGenre.getSelectedItem();
            String link = ((EditText) view.findViewById(R.id.seriesLink)).getText().toString();
            boolean bFav = ((Switch)view.findViewById((R.id.A08400_favourite))).isChecked();
            boolean bStarted = ((Switch)view.findViewById((R.id.A08400_started))).isChecked();
            boolean bCom = ((Switch)view.findViewById((R.id.A08400_completed))).isChecked();

            dbHandler.createSeries(Title,genre.getGenreID(),link,bFav,bStarted,bCom);
            Toast.makeText(this.getContext(),"Added Series", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(view).navigate(R.id.nav_addNewMedia);
        });

        return view;
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

}
