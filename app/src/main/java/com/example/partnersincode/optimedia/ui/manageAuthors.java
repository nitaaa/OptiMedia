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
import com.example.partnersincode.optimedia.adapters.AuthorAdapter;
import com.example.partnersincode.optimedia.models.Author;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link manageAuthors#newInstance} factory method to
 * create an instance of this fragment.
 */
public class manageAuthors extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public manageAuthors() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment manageAuthors.
     */
    // TODO: Rename and change types and number of parameters
    public static manageAuthors newInstance(String param1, String param2) {
        manageAuthors fragment = new manageAuthors();
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
        View rootView = inflater.inflate(R.layout.fragment_manage_authors, container, false);
        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());

        List<Author> authors = dbHandler.getAuthors();
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerGameLog);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        AuthorAdapter adapter = new AuthorAdapter(authors);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener( view -> {
            AuthorAdapter.AuthorViewHolder viewHolder = (AuthorAdapter.AuthorViewHolder) recyclerView.findContainingViewHolder(view);
            Author author = viewHolder.author;
            //Toast.makeText(this.getContext(), author.getFullName(),Toast.LENGTH_LONG).show();
            //TODO: Edit Author Navigation
            Bundle bundle = new Bundle();
            bundle.putParcelable("authorInfo", author);
            Navigation.findNavController(view).navigate(R.id.nav_createAuthor, bundle);
        });

        Button btnCreateAuthor = rootView.findViewById(R.id.btnCreateGameLog);
        btnCreateAuthor.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.nav_createAuthor));

        return rootView;
    }
}