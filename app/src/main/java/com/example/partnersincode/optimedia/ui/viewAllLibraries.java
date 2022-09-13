package com.example.partnersincode.optimedia.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.adapters.LibraryAdapter;
import com.example.partnersincode.optimedia.models.Library;

import java.util.List;

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
            //testing - replace with navigation
//            if (library.getLibraryType().equals("Book")) {
//                dbHandler.getAllBooksLibrary(library.getLibraryID(),"");
//            }
            if (library.getLibraryType().equals("Book")) {
                Navigation.findNavController(view).navigate(R.id.nav_viewBookLibrary, bundle); //navigate to view library
            } else if (library.getLibraryType().equals("Game")) {
                Navigation.findNavController(view).navigate(R.id.nav_viewGameLibrary, bundle);
            }
        });

        return rootView;
    }
}