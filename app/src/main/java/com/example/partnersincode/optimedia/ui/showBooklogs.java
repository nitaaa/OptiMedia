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
import com.example.partnersincode.optimedia.adapters.BooklogAdapter;
import com.example.partnersincode.optimedia.adapters.GenreAdapter;
import com.example.partnersincode.optimedia.models.Book;
import com.example.partnersincode.optimedia.models.Booklog;
import com.example.partnersincode.optimedia.models.Genre;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link manageAuthors#newInstance} factory method to
 * create an instance of this fragment.
 */
public class showBooklogs extends Fragment {

    Book book;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int BookID;
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            book = bundle.getParcelable("bookInfo");
            BookID = book.getBookID();
        }else
            BookID=-1;



        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_booklogs, container, false);

        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());
        List<Booklog> booklogs = dbHandler.getBooklogsFromBook(BookID);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerBooklogs);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        BooklogAdapter adapter = new BooklogAdapter(booklogs);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener( item -> {
            BooklogAdapter.BLViewHolder viewHolder = (BooklogAdapter.BLViewHolder) recyclerView.findContainingViewHolder(item);
            Booklog booklog = viewHolder.booklog;

            Bundle bundleSend = new Bundle();
            bundleSend.putParcelable("bookInfo",book);
            bundleSend.putParcelable("blInfo", booklog);
            bundleSend.putString("Intent", "Edit");
          Navigation.findNavController(item).navigate(R.id.nav_createBooklogs, bundleSend);

        });

        Button btnCreateGenre = view.findViewById(R.id.btnCreateBooklog);
        btnCreateGenre.setOnClickListener(item -> {
            Bundle bundleSend = new Bundle();
            bundleSend.putInt("blBookID", BookID);
            bundleSend.putString("Intent", "Add");
            Navigation.findNavController(item).navigate(R.id.nav_createBooklogs,bundleSend);
        });

        return view;
    }
}
