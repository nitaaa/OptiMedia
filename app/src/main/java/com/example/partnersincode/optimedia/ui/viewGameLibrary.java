package com.example.partnersincode.optimedia.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.adapters.BookAdapter;
import com.example.partnersincode.optimedia.adapters.GameAdapter;
import com.example.partnersincode.optimedia.models.Book;
import com.example.partnersincode.optimedia.models.Game;
import com.example.partnersincode.optimedia.models.Library;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link viewGameLibrary#newInstance} factory method to
 * create an instance of this fragment.
 */
public class viewGameLibrary extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "ViewGameLibrary - ";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Library library;

    public viewGameLibrary() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment viewGameLibrary.
     */
    // TODO: Rename and change types and number of parameters
    public static viewGameLibrary newInstance(String param1, String param2) {
        viewGameLibrary fragment = new viewGameLibrary();
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
            library = bundle.getParcelable("libraryInfo");
            Log.d(TAG, "onCreate: library passed: "+library.getLibraryName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_view_game_library, container, false);
        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());
        List<Game> gameList = dbHandler.getAllGamesLibrary(library.getLibraryID(),"");
        RecyclerView reGameLibrary = rootView.findViewById(R.id.recyclerGameLibrary);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        reGameLibrary.setLayoutManager(layoutManager);
        GameAdapter adapter = new GameAdapter(gameList);
        reGameLibrary.setAdapter(adapter);

        TextView txtGameLibraryName = rootView.findViewById(R.id.txtGameLibraryName);
        txtGameLibraryName.setText(library.getLibraryName());

        adapter.setOnClickListener( view -> {
            GameAdapter.GameViewHolder viewHolder = (GameAdapter.GameViewHolder) reGameLibrary.findContainingViewHolder(view);
            Game game = viewHolder.game;
            Toast.makeText(this.getContext(), game.toString(),Toast.LENGTH_LONG).show();
            Bundle bundle = new Bundle();
            bundle.putParcelable("gameInfo", game);
            Navigation.findNavController(view).navigate(R.id.nav_manageGameLog, bundle); //navigate to A02111
        });

        //TODO: onclick for edit
        Button imgBtnEditGame = rootView.findViewById(R.id.imgBtnEditGame);
        imgBtnEditGame.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("libraryInfo", library);
            Navigation.findNavController(view).navigate(R.id.nav_editLibrary, bundle);
        });

        //TODO: onclick for add to library - open search for books

        //onclick for share
        Button imgBtnShareGLib = rootView.findViewById(R.id.imgBtnShareGLib);
        imgBtnShareGLib.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("GameLibraryInfo", library);
            Navigation.findNavController(view).navigate(R.id.nav_shareLibrary, bundle);
        });
        return rootView;
    }
}