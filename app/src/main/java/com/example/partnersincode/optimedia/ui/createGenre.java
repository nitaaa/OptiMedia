package com.example.partnersincode.optimedia.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Genre;

public class createGenre extends Fragment {
    private Genre editGenre;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        Boolean bEdit;
        if (bundle != null) {
            editGenre = bundle.getParcelable("genreInfo");
            bEdit = true;
        } else {
            bEdit = false;
        }

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_create_genre, container, false);
        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());

        TextView edtName = rootView.findViewById(R.id.edtxtGenre);
        Button btnNewGenre = rootView.findViewById(R.id.btnAddGenre);

        if (bEdit){
            btnNewGenre.setText("Edit Genre");
            edtName.setText(editGenre.getGenreName());
            btnNewGenre.setOnClickListener(view -> {
                String genre=edtName.getText().toString();
                dbHandler.updateGenre(editGenre.getGenreID(),genre);

                Toast.makeText(this.getContext(), "Genre Edited: " + genre, Toast.LENGTH_LONG).show();
                edtName.setText("");
                getActivity().onBackPressed();
              //  Navigation.findNavController(rootView).navigate(R.id.nav_manageGenre);
            });
        } else {
            btnNewGenre.setOnClickListener(view -> {
                String newGenre = edtName.getText().toString();
                dbHandler.addGenre(newGenre);

                Toast.makeText(this.getContext(), "Genre Added: " + newGenre, Toast.LENGTH_LONG).show();
                edtName.setText("");
                getActivity().onBackPressed();
                //Navigation.findNavController(rootView).navigate(R.id.nav_manageGenre);
            });
        }

        return rootView;
    }
}