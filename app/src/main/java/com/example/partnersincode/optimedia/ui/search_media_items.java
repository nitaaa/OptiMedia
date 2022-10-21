package com.example.partnersincode.optimedia.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Book;
import com.example.partnersincode.optimedia.models.Game;
import com.example.partnersincode.optimedia.models.MediaObject;
import com.example.partnersincode.optimedia.models.WatchObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link search_media_items#newInstance} factory method to
 * create an instance of this fragment.
 */
public class search_media_items extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public search_media_items() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment search_media_items.
     */
    // TODO: Rename and change types and number of parameters
    public static search_media_items newInstance(String param1, String param2) {
        search_media_items fragment = new search_media_items();
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
        View rootView = inflater.inflate(R.layout.fragment_search_media_items, container, false);
        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());

        EditText edtTxtKeywordSearch = rootView.findViewById(R.id.edtTxtKeywordSearch);
        RadioButton rbS_All,rbS_Book,rbS_Game,rbS_WLI;
        rbS_All = rootView.findViewById(R.id.rbS_All);
        rbS_Book = rootView.findViewById(R.id.rbS_Book);
        rbS_Game = rootView.findViewById(R.id.rbS_Game);
        rbS_WLI = rootView.findViewById(R.id.rbS_WLI);
        CheckBox chbxShowFavourites;
        chbxShowFavourites = rootView.findViewById(R.id.chbxShowFavourites);

        Button btnSearchMedia = rootView.findViewById(R.id.btnSearchMedia);
        btnSearchMedia.setOnClickListener(view -> {
            ArrayList<MediaObject> resultsArrayList = new ArrayList<>();
            String keyword = edtTxtKeywordSearch.getText().toString();
            //add each condition for the searching
            if (rbS_All.isChecked()){
                resultsArrayList.addAll(dbHandler.getBooks(keyword));
                resultsArrayList.addAll(dbHandler.getGames(keyword));
                resultsArrayList.addAll(dbHandler.getWatchItems(keyword));
            } else if(rbS_Book.isChecked()){
                resultsArrayList.addAll(dbHandler.getBooks(keyword));
            } else if(rbS_Game.isChecked()){
                resultsArrayList.addAll(dbHandler.getGames(keyword));
            } else if(rbS_WLI.isChecked()){
                resultsArrayList.addAll(dbHandler.getWatchItems(keyword));
            }

            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("results", resultsArrayList); //use Class.isInstance(/object\)

            ArrayList<MediaObject> favResultsArrayList = new ArrayList<>();
            if (chbxShowFavourites.isChecked()){
                Log.d("search favourites", "checking checkbox: " + chbxShowFavourites.isChecked());
                for (MediaObject m : resultsArrayList) {
                    Log.d("ADD FAV", "start ");
                    if (m instanceof Book){
                        if (((Book) m).isFavourite()){
                            favResultsArrayList.add(m);
                            Log.d("ADD FAV", "book: " + ((Book) m).getBookID());
                        }
                    }
                    if (m instanceof Game){
                        if (((Game) m).isFavourite()) {
                            favResultsArrayList.add(m);
                            Log.d("ADD FAV", "game: " + ((Game) m).getGameID());
                        }
                    }
                    if (m instanceof WatchObject){
                        Log.d("ADD FAV", "m is instance: " + ((WatchObject) m).getID());
                        if (((WatchObject) m).getFavourite()){
                            favResultsArrayList.add(m);
                            Log.d("ADD FAV", "watch: " + ((WatchObject) m).getID());

                        }
                    }
                }
                bundle.putParcelableArrayList("results", favResultsArrayList);
            }

            bundle.putString("keyword", keyword);
            bundle.putBoolean("favourite", chbxShowFavourites.isChecked());
            Navigation.findNavController(view).navigate(R.id.nav_viewSearch, bundle);
        });

        return rootView;
    }
}