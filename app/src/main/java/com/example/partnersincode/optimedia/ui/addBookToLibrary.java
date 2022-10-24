package com.example.partnersincode.optimedia.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.adapters.AddBookAdapter;
import com.example.partnersincode.optimedia.models.Book;
import com.example.partnersincode.optimedia.models.Library;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addBookToLibrary#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addBookToLibrary extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public addBookToLibrary() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addGameToLibrary.
     */
    // TODO: Rename and change types and number of parameters
    public static addBookToLibrary newInstance(String param1, String param2) {
        addBookToLibrary fragment = new addBookToLibrary();
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
        View rootView = inflater.inflate(R.layout.fragment_add_book_to_library, container, false);
        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());

        //get libraries for spinner
        ArrayList<Library> libraries = dbHandler.getBookLibraries();
        ArrayAdapter<Library> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, libraries);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        Spinner spinLibrary = (Spinner) rootView.findViewById(R.id.spinLibrary);
        spinLibrary.setAdapter(adapter);

        TextView edtxtSearch = rootView.findViewById(R.id.edtxtBook);

        // recycler viewer
        List<Book> books = dbHandler.getBooks();
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerBook);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        AddBookAdapter bookAdapter = new AddBookAdapter(books);
        recyclerView.setAdapter(bookAdapter);

        bookAdapter.setOnClickListener( view -> {
            AddBookAdapter.AddBookViewHolder viewHolder = (AddBookAdapter.AddBookViewHolder) recyclerView.findContainingViewHolder(view);

            if (viewHolder != null) {
                viewHolder.setSelected();
            }
        });

        Button btnSearch = rootView.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(view ->
        {
            List<Book> books1 = dbHandler.getBooks(edtxtSearch.getText().toString());
            RecyclerView recyclerView2 = rootView.findViewById(R.id.recyclerBook);

            RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext());
            recyclerView2.setLayoutManager(layoutManager2);
            AddBookAdapter bookAdapter2 = new AddBookAdapter(books1);
            recyclerView2.setAdapter(bookAdapter2);
        });

        Button btnAdd = rootView.findViewById(R.id.btnBooktoLib);
        btnAdd.setOnClickListener(view ->
        {
            Library library = (Library) spinLibrary.getSelectedItem();

            for (Book added :
                    bookAdapter.getSelectedObjects()) {

                dbHandler.createBookLibrary(added, library);
            }
            Toast.makeText(getContext(), getString(R.string.toastAddToBookLibrary,library.getLibraryName()),Toast.LENGTH_SHORT).show();
            Navigation.findNavController(view).navigate(R.id.nav_addNewMedia);
        });

        return rootView;
    }
}