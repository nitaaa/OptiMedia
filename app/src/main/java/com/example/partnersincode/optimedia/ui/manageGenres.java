package com.example.partnersincode.optimedia.ui;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.adapters.GenreAdapter;
import com.example.partnersincode.optimedia.models.Genre;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link manageAuthors#newInstance} factory method to
 * create an instance of this fragment.
 */
public class manageGenres extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manage_genres, container, false);

        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());
        List<Genre> genres = dbHandler.getGenres();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerGenres);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        GenreAdapter adapter = new GenreAdapter(genres);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener( item -> {
            GenreAdapter.GenreViewHolder viewHolder = (GenreAdapter.GenreViewHolder) recyclerView.findContainingViewHolder(item);
            Genre genre = viewHolder.genre;

            Bundle bundle = new Bundle();
            bundle.putParcelable("genreInfo", genre);
          Navigation.findNavController(item).navigate(R.id.nav_createGenre, bundle);

        });

        Button btnCreateGenre = view.findViewById(R.id.btnCreateGenre);
        btnCreateGenre.setOnClickListener(item -> Navigation.findNavController(item).navigate(R.id.nav_createGenre));

        return view;
    }
}
