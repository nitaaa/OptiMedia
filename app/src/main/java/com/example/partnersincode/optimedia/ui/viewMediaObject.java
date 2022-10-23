package com.example.partnersincode.optimedia.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Author;
import com.example.partnersincode.optimedia.models.Book;
import com.example.partnersincode.optimedia.models.Game;
import com.example.partnersincode.optimedia.models.Genre;
import com.example.partnersincode.optimedia.models.Library;
import com.example.partnersincode.optimedia.models.MediaObject;
import com.example.partnersincode.optimedia.models.Movie;
import com.example.partnersincode.optimedia.models.Series;
import com.example.partnersincode.optimedia.models.WatchObject;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link viewMediaObject#newInstance} factory method to
 * create an instance of this fragment.
 */
public class viewMediaObject extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private MediaObject mediaObject;
    private String type;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public viewMediaObject() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment viewMediaObject.
     */
    // TODO: Rename and change types and number of parameters
    public static viewMediaObject newInstance(String param1, String param2) {
        viewMediaObject fragment = new viewMediaObject();
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
            mediaObject = bundle.getParcelable("mediaObject");
            type = bundle.getString("type");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_view_media_object, container, false);
        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());

        TextView txtTitle = rootView.findViewById(R.id.txtTitle);
        TextView txtAuth_vwo = rootView.findViewById(R.id.txtAuth_vwo);
        TextView txtGenre_vwo = rootView.findViewById(R.id.txtGenre_vwo);
        TextView txttype = rootView.findViewById(R.id.txttype);
        TextView txt3rdProp = rootView.findViewById(R.id.txt3rdProp);

        List<Genre> genres = dbHandler.getGenres();

        switch (type) {
            case "Book": {
                Book book = (Book) mediaObject;
                txtTitle.setText(book.getBookTitle());
                Author author = dbHandler.getAuthorByID(book.getAuthorID());
                txtAuth_vwo.setText(author.getFullName());
                txtAuth_vwo.setVisibility(View.VISIBLE);
                Genre genre = genres.stream().filter(genre1 -> genre1.getGenreID() == book.getGenreID()).findFirst().get();
                txtGenre_vwo.setText(genre.toString());
                txttype.setText(type);
                txt3rdProp.setText(book.getISBN());
                break;
            }
            case "Game": {
                Game game = (Game) mediaObject;
                txtTitle.setText(game.getGameTitle());
                Genre genre = genres.stream().filter(genre1 -> genre1.getGenreID() == game.getGenreID()).findFirst().get();
                txtGenre_vwo.setText(genre.toString());
                txttype.setText(type);
                txt3rdProp.setText(game.getGameType());
                break;
            }
            case "Movie": {
                Movie movie = (Movie) mediaObject;
                txtTitle.setText(movie.getTitle());
                Genre genre = genres.stream().filter(genre1 -> genre1.getGenreID() == movie.getGenreID()).findFirst().get();
                txtGenre_vwo.setText(genre.toString());
                txttype.setText(type);
                String link = "<a href=\""+movie.getLink()+"\">"+movie.getLink()+"</a>";
                txt3rdProp.setText(movie.getLink());
                txt3rdProp.setMovementMethod(LinkMovementMethod.getInstance());
                break;
            }
            case "Series": {
                Series series = (Series) mediaObject;
                txtTitle.setText(series.getTitle());
                Genre genre = genres.stream().filter(genre1 -> genre1.getGenreID() == series.getGenreID()).findFirst().get();
                txtGenre_vwo.setText(genre.toString());
                txttype.setText(type);
                txt3rdProp.setText(series.getLink());
                txt3rdProp.setMovementMethod(LinkMovementMethod.getInstance());
                break;
            }
        }

        Button btnAddtoLib_vmo = rootView.findViewById(R.id.btnAddtoLib_vmo);
        Button btnShare_vwo = rootView.findViewById(R.id.btnShare_vwo);
        Button btnAdd_vwo = rootView.findViewById(R.id.btnAdd_vwo);

        btnAddtoLib_vmo.setOnClickListener(view -> {
            List<Library> libraries = dbHandler.getAllLibraries();

            List<Library> showLibaries;
            if (type.equals("Book")||type.equals("Game"))
                showLibaries = libraries.stream().filter(library -> library.getLibraryType().equals(type)).collect(Collectors.toList());
            else
                showLibaries = libraries.stream().filter(library -> library.getLibraryType().equals("Watch")).collect(Collectors.toList());

            LinearLayout linlayAdd = rootView.findViewById(R.id.linlayAdd);
            Spinner spinAddtoLib = rootView.findViewById(R.id.spinAddtoLib);

            ArrayAdapter<Library> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, showLibaries);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinAddtoLib.setAdapter(adapter);

            linlayAdd.setVisibility(View.VISIBLE);
        });

        btnAdd_vwo.setOnClickListener(view ->{
            Spinner spinAddtoLib = rootView.findViewById(R.id.spinAddtoLib);
            Library library = (Library) spinAddtoLib.getSelectedItem();
            String name = "";
            if (type.equals("Book")){
                dbHandler.addBookToLib(library.getLibraryID(), ((Book) mediaObject).getBookID());
                name = ((Book) mediaObject).getBookTitle();
            } else if (type.equals("Game")){
                dbHandler.createGameLibrary((Game) mediaObject, library);
                name = ((Game) mediaObject).getGameTitle();
            } else if (type.equals("Movie")){
                int wli_id = dbHandler.getWLI_IDbyMovieID(((Movie)mediaObject).getMovieID());
                dbHandler.addWLItoLibrary(library, wli_id);
                name = ((WatchObject) mediaObject).getTitle();
            } else if (type.equals("Series")){
                int wli_id = dbHandler.getWLI_IDbySeriesID(((Series)mediaObject).getSeriesID());
                dbHandler.addWLItoLibrary(library, wli_id);
                name = ((WatchObject) mediaObject).getTitle();
            }

            Toast.makeText(this.getContext(), name + " added to " + library.getLibraryName(), Toast.LENGTH_SHORT).show();
        });

        btnShare_vwo.setOnClickListener(view -> {
            int libID = dbHandler.createLibrary("tempshare", type);
            Library shareLib = dbHandler.getLibraryByID(libID);
            if (type.equals("Book")){
                dbHandler.addBookToLib(libID, ((Book) mediaObject).getBookID());
            } else if (type.equals("Game")){
                dbHandler.createGameLibrary((Game) mediaObject, shareLib);
            } else if (type.equals("Movie")){
                int wli_id = dbHandler.getWLI_IDbyMovieID(((Movie)mediaObject).getMovieID());
                dbHandler.addWLItoLibrary(shareLib, wli_id);
            } else if (type.equals("Series")){
                int wli_id = dbHandler.getWLI_IDbySeriesID(((Series)mediaObject).getSeriesID());
                dbHandler.addWLItoLibrary(shareLib, wli_id);
            }

            Bundle bundle = new Bundle();
            bundle.putParcelable("libraryInfo", shareLib);
            Navigation.findNavController(view).navigate(R.id.nav_xmlExport, bundle);
        });

        return rootView;
    }
}