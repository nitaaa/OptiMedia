package com.example.partnersincode.optimedia.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.adapters.SearchAdapter;
import com.example.partnersincode.optimedia.adapters.WatchObjectAdapter;
import com.example.partnersincode.optimedia.models.Book;
import com.example.partnersincode.optimedia.models.Game;
import com.example.partnersincode.optimedia.models.MediaObject;
import com.example.partnersincode.optimedia.models.Movie;
import com.example.partnersincode.optimedia.models.Series;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link viewSearch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class viewSearch extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<MediaObject> resultsArrayList;
    private String keyword;
    private Boolean favourited;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public viewSearch() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment viewSearch.
     */
    // TODO: Rename and change types and number of parameters
    public static viewSearch newInstance(String param1, String param2) {
        viewSearch fragment = new viewSearch();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            resultsArrayList = bundle.getParcelableArrayList("results");
            keyword = bundle.getString("keyword");
            favourited = bundle.getBoolean("favourite");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_view_search, container, false);

        RecyclerView reSearchResults = rootView.findViewById(R.id.reSearchResults);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        reSearchResults.setLayoutManager(layoutManager);
        SearchAdapter adapter = new SearchAdapter(resultsArrayList);
        reSearchResults.setAdapter(adapter);

        TextView txtKeySearch = rootView.findViewById(R.id.txtKeySearch);
        String text = "Search term: \"" + keyword + "\"";
        if (favourited)
            text += ", Showing Favourites.";
        txtKeySearch.setText(text);

        adapter.setOnClickListener( view -> {
            SearchAdapter.SearchViewHolder viewHolder = (SearchAdapter.SearchViewHolder) reSearchResults.findContainingViewHolder(view);

            MediaObject mediaObject = new MediaObject();
            String log ="";
            assert viewHolder != null;
            if (viewHolder.book != null){
                mediaObject = viewHolder.book;
                log = ((Book) mediaObject).getBookTitle();
            } else if (viewHolder.game != null){
                mediaObject = viewHolder.game;
                log = ((Game) mediaObject).getGameTitle();
            } else if (viewHolder.movie != null){
                mediaObject = viewHolder.movie;
                log = ((Movie) mediaObject).getTitle();
            } else if(viewHolder.series != null){
                mediaObject = viewHolder.series;
                log = ((Series) mediaObject).getTitle();
            }

            //TODO nav to display media object
            Toast.makeText(this.getContext(),log,Toast.LENGTH_SHORT).show();
        });



        return rootView;
    }
}