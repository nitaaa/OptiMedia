package com.example.partnersincode.optimedia.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.adapters.LibraryAdapter;
import com.example.partnersincode.optimedia.models.Library;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link viewAllLibraries#newInstance} factory method to
 * create an instance of this fragment.
 */
public class viewAllLibraries extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public viewAllLibraries() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment viewAllLibraries.
     */
    // TODO: Rename and change types and number of parameters
    public static viewAllLibraries newInstance(String param1, String param2) {
        viewAllLibraries fragment = new viewAllLibraries();
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
        View rootView = inflater.inflate(R.layout.fragment_view_all_libraries, container, false);
        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());
        List<Library> libraryList = dbHandler.getAllLibraries();
        RecyclerView reListAllLibraries = rootView.findViewById(R.id.reListAllLibraries);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        reListAllLibraries.setLayoutManager(layoutManager);
        LibraryAdapter adapter = new LibraryAdapter(libraryList);
        reListAllLibraries.setAdapter(adapter);

        adapter.setOnClickListener( view -> {
            LibraryAdapter.LibraryViewHolder viewHolder = (LibraryAdapter.LibraryViewHolder) reListAllLibraries.findContainingViewHolder(view);
            Library library = viewHolder.library;
            Toast.makeText(this.getContext(), library.getLibraryName(),Toast.LENGTH_LONG).show();
            Bundle bundle = new Bundle();
            bundle.putParcelable("libraryInfo", library);

            if (library.getLibraryType().equals("Book")) {
                Navigation.findNavController(view).navigate(R.id.nav_viewBookLibrary, bundle);
            } else if (library.getLibraryType().equals("Game")) {
                Navigation.findNavController(view).navigate(R.id.nav_viewGameLibrary, bundle);
            } else if (library.getLibraryType().equals("Watch")) {
                Navigation.findNavController(view).navigate(R.id.nav_viewWatchLibrary, bundle);
            }
        });

        Button btnBookLibraries = rootView.findViewById(R.id.btnBookLibraries);
        Button btnWatchLibraries = rootView.findViewById(R.id.btnWatchLibraries);
        Button btnGameLibraries = rootView.findViewById(R.id.btnGameLibraries);
        Button btnShowAllLibraries = rootView.findViewById(R.id.btnShowAllLibraries);

        btnBookLibraries.setOnClickListener(view -> {
            List<Library> booksList = libraryList.stream().filter(library -> library.getLibraryType().equals("Book")).collect(Collectors.toList());
            adapter.setLibraryList(booksList);
            reListAllLibraries.setAdapter(adapter);
        });

        btnWatchLibraries.setOnClickListener(view -> {
            List<Library> watchList = libraryList.stream().filter(library -> library.getLibraryType().equals("Watch")).collect(Collectors.toList());
            adapter.setLibraryList(watchList);
            reListAllLibraries.setAdapter(adapter);
        });

        btnGameLibraries.setOnClickListener(view -> {
            List<Library> gamesList = libraryList.stream().filter(library -> library.getLibraryType().equals("Game")).collect(Collectors.toList());
            adapter.setLibraryList(gamesList);
            reListAllLibraries.setAdapter(adapter);
        });

        btnShowAllLibraries.setOnClickListener(view -> {
            adapter.setLibraryList(libraryList);
            reListAllLibraries.setAdapter(adapter);
        });


        return rootView;
    }
}