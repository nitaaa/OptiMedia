package com.example.partnersincode.optimedia.ui;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
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
import com.example.partnersincode.optimedia.models.Book;
import com.example.partnersincode.optimedia.models.Library;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link viewBookLibrary#newInstance} factory method to
 * create an instance of this fragment.
 */
public class viewBookLibrary extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "ViewBookLibrary:";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Library library;

    public viewBookLibrary() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment viewBookLibrary.
     */
    // TODO: Rename and change types and number of parameters
    public static viewBookLibrary newInstance(String param1, String param2) {
        viewBookLibrary fragment = new viewBookLibrary();
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
        View rootView = inflater.inflate(R.layout.fragment_view_book_library, container, false);
        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());
        List<Book> bookList = dbHandler.getAllBooksLibrary(library.getLibraryID(),"");
        RecyclerView reBookLibrary = rootView.findViewById(R.id.recyclerBookLibrary);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        reBookLibrary.setLayoutManager(layoutManager);
        BookAdapter adapter = new BookAdapter(bookList);
        reBookLibrary.setAdapter(adapter);

        TextView txtBookLibraryName = rootView.findViewById(R.id.txtBookLibraryName);
        txtBookLibraryName.setText(library.getLibraryName());

        adapter.setOnClickListener( view -> {
            BookAdapter.BookViewHolder viewHolder = (BookAdapter.BookViewHolder) reBookLibrary.findContainingViewHolder(view);
            Book book = viewHolder.book;
            Toast.makeText(this.getContext(), book.toString(),Toast.LENGTH_LONG).show();
            Bundle bundle = new Bundle();
            bundle.putParcelable("bookInfo", book);
            Navigation.findNavController(view).navigate(R.id.nav_viewBooklogs, bundle); //navigate to A02111
        });
        //Set long click listener for removing from library
        adapter.setOnLongClickListener( view ->
        {
            BookAdapter.BookViewHolder viewHolder = (BookAdapter.BookViewHolder) reBookLibrary.findContainingViewHolder(view);
            Book book = viewHolder.book;
            AlertDialog dialog = new AlertDialog.Builder(getContext()).create();
            dialog.setMessage(getString(R.string.confirmRemoveFromLib,book.getBookTitle(),library.getLibraryName()));
            dialog.setButton(AlertDialog.BUTTON_NEGATIVE,getString(R.string.no),(a,b) -> dialog.cancel());
            dialog.setButton(AlertDialog.BUTTON_POSITIVE,getString(R.string.yes),(a,b) -> dbHandler.removeFromLibrary(library,book));
            dialog.show();

            return true;
        });


        //TODO: onclick for edit
        Button imgBtnEditBook = rootView.findViewById(R.id.imgBtnEditBook);
        imgBtnEditBook.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("libraryInfo", library);
            Navigation.findNavController(view).navigate(R.id.nav_editLibrary, bundle);
        });

        //TODO: onclick for add to library - open search for books

        //onclick for share
        Button imgBtnShareBLib = rootView.findViewById(R.id.imgBtnShareBLib);
        imgBtnShareBLib.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("libraryInfo", library);
            Navigation.findNavController(view).navigate(R.id.nav_xmlExport, bundle);
        });
        return rootView;
    }
}