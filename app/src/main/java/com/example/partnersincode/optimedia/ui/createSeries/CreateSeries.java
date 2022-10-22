package com.example.partnersincode.optimedia.ui.createSeries;

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

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


import com.example.partnersincode.optimedia.models.Genre;
import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;

import java.util.ArrayList;

public class CreateSeries extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_series_fragment, container, false);

        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());
        ArrayList<Genre> genres = dbHandler.getGenres();
        ArrayAdapter<Genre> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, genres);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        Spinner spinnerGenre = view.findViewById(R.id.spinnerGenre);
        spinnerGenre.setAdapter(adapter);

        Button button = view.findViewById(R.id.btnCreateSeries);
        button.setOnClickListener(v-> {
            String Title = ((EditText) view.findViewById(R.id.edtSeriesTitle)).getText().toString();
            Genre genre = (Genre) spinnerGenre.getSelectedItem();
            boolean bFav = ((Switch)view.findViewById((R.id.A08400_favourite))).isChecked();
            boolean bStarted = ((Switch)view.findViewById((R.id.A08400_started))).isChecked();
            boolean bCom = ((Switch)view.findViewById((R.id.A08400_completed))).isChecked();

            dbHandler.createSeries(Title,genre.getGenreID(),bFav,bStarted,bCom);
            Toast.makeText(this.getContext(),"Added Series", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(view).navigate(R.id.nav_home);
        });

        return view;
    }

}
