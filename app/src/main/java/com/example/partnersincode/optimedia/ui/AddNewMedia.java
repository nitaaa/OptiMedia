package com.example.partnersincode.optimedia.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.partnersincode.optimedia.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddNewMedia#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddNewMedia extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddNewMedia() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddNewMedia.
     */
    // TODO: Rename and change types and number of parameters
    public static AddNewMedia newInstance(String param1, String param2) {
        AddNewMedia fragment = new AddNewMedia();
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

        //navigation to other pages

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View rootView = inflater.inflate(R.layout.fragment_add_new_media, container, false);
        final Button btnNewBook = (Button) rootView.findViewById(R.id.btnNewBook);
        final Button btnNewGame = (Button) rootView.findViewById(R.id.btnNewGame);
        final Button btnNewMovie = (Button) rootView.findViewById(R.id.btnNewMovie);
        final Button btnNewSeries = (Button) rootView.findViewById(R.id.btnNewSeries);

        btnNewBook.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.nav_createBook));
        btnNewGame.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.nav_create_game));
        btnNewMovie.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.nav_addNewMovie));
        btnNewSeries.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.nav_createSeries));

        Button btnAddBookToLib = rootView.findViewById(R.id.btnAddBookToLib);
        Button btnAddGameToLib = rootView.findViewById(R.id.btnAddGameToLib);
        Button btnAddWatchToLib = rootView.findViewById(R.id.btnAddWatchToLib);
        btnAddBookToLib.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.nav_addBookToLib));
        btnAddGameToLib.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.nav_addGameToLibrary));
        btnAddWatchToLib.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.nav_addToWatchLibrary));

        Button btnManageAuthor = rootView.findViewById(R.id.btnManageAuthor);
        Button btnManageGenre = rootView.findViewById(R.id.btnManageGenre);
        btnManageAuthor.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.nav_manageAuthors));
        btnManageGenre.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.nav_manageGenre));

        return rootView;
    }
}