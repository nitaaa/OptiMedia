package com.example.partnersincode.optimedia.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.adapters.BookAdapter;
import com.example.partnersincode.optimedia.adapters.GameAdapter;
import com.example.partnersincode.optimedia.adapters.WatchObjectAdapter;
import com.example.partnersincode.optimedia.models.Book;
import com.example.partnersincode.optimedia.models.Game;
import com.example.partnersincode.optimedia.models.WatchObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewList extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ViewList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewList.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewList newInstance(String param1, String param2) {
        ViewList fragment = new ViewList();
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
        View rootView = inflater.inflate(R.layout.fragment_view_list, container, false);

        Bundle bundle = this.getArguments();
        ArrayList list = (ArrayList)bundle.getSerializable("list");
        String name = bundle.getString("libraryName");

       TextView lblName = rootView.findViewById(R.id.lblName);
        lblName.setText(name);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerItems);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        if (list.get(0) instanceof Book) {
            BookAdapter bookAdapter = new BookAdapter(list);
            recyclerView.setAdapter(bookAdapter);
            bookAdapter.setOnClickListener((View view)->{
                Bundle bundleBook = new Bundle();
                BookAdapter.BookViewHolder viewHolder = (BookAdapter.BookViewHolder) recyclerView.findContainingViewHolder(view);
                bundleBook.putParcelable("mediaObject",viewHolder.book);
                bundleBook.putString("type","Book");
                Navigation.findNavController(view).navigate(R.id.nav_viewMediaObject,bundleBook);
            });
        }

        if (list.get(0) instanceof WatchObject){
            WatchObjectAdapter watchAdapter = new  WatchObjectAdapter (list);
            recyclerView.setAdapter(watchAdapter);
           watchAdapter.setOnClickListener((View view)->{
               Bundle bundleWatch = new Bundle();
               WatchObjectAdapter.WatchObjectViewHolder viewHolder = (WatchObjectAdapter.WatchObjectViewHolder) recyclerView.findContainingViewHolder(view);
             if (viewHolder.movie !=null) {
                 bundleWatch.putParcelable("mediaObject", viewHolder.movie);
                 bundleWatch.putString("type", "Movie");
             }else{
                 bundleWatch.putParcelable("mediaObject", viewHolder.series);
                 bundleWatch.putString("type", "Series");
             }
               Navigation.findNavController(view).navigate(R.id.nav_viewMediaObject,bundleWatch);
           });
        }

            if (list.get(0) instanceof Game){
              GameAdapter gameAdapter = new GameAdapter(list);
                recyclerView.setAdapter(gameAdapter);
                gameAdapter.setOnClickListener((View view)->{
                    Bundle bundleGame = new Bundle();
                    GameAdapter.GameViewHolder viewHolder = (GameAdapter.GameViewHolder) recyclerView.findContainingViewHolder(view);
                    bundleGame.putParcelable("mediaObject",viewHolder.game);
                    bundleGame.putString("type","Game");
                    Navigation.findNavController(view).navigate(R.id.nav_viewMediaObject,bundleGame);
                });
            }

        return rootView;
    }
}