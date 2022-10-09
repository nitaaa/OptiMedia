package com.example.partnersincode.optimedia.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Book;
import com.example.partnersincode.optimedia.models.Game;
import com.example.partnersincode.optimedia.models.Library;
import com.example.partnersincode.optimedia.models.Series;
import com.example.partnersincode.optimedia.models.WatchObject;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link xmlExport#newInstance} factory method to
 * create an instance of this fragment.
 */
public class xmlExport extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private Library library;
    private Document document;
    private String output;

    public xmlExport() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment xmlExport.
     */
    // TODO: Rename and change types and number of parameters
    public static xmlExport newInstance(String param1, String param2) {
        xmlExport fragment = new xmlExport();
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

        Bundle bundle = this.getArguments();
        if(bundle!=null)
        {
            //TODO: Set key to correct name
            library = (Library) bundle.getParcelable("library");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_xml_export, container, false);

        generateXML();
        TextView view = root.findViewById(R.id.xmlView);
        xmlToString();
        view.setText(output);

        return root;

    }

    private void generateXML()
    {
        DatabaseHandler db = new DatabaseHandler(this.getContext());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.newDocument();
            Element rootNode = document.createElement("library");
            document.appendChild(rootNode);
            rootNode.setAttribute("libraryName", library.getLibraryName());
            rootNode.setAttribute("libraryType", library.getLibraryType());


        if(library.getLibraryType().equals("Watch"))
        {
            List<WatchObject> watch = db.getAllWatchItemsLibrary(library.getLibraryID(), "");
            for (WatchObject object:
                 watch) {

                Element child = null;
                if(object instanceof Series)
                {
                    child = document.createElement("series");

                }
                else
                {
                    child = document.createElement("movie");

                }
                try {
                    String genre = db.getGenre(object.getGenreID()).getGenreName();
                    child.setAttribute("genre",genre);
                }catch (Exception e) {}

                child.setAttribute("title", object.getTitle());
                rootNode.appendChild(child);

            }

        }

        if(library.getLibraryType().equals("Game"))
        {
            List<Game> game = db.getAllGamesLibrary(library.getLibraryID(),"");
            for (Game object: game) {

                Element child = document.createElement("game");
                try {
                    String genre = db.getGenre(object.getGenreID()).getGenreName();
                    child.setAttribute("genre",genre);
                }catch (Exception e) {}
                child.setAttribute("title", object.getGameTitle());
                child.setAttribute("gameType", object.getGameType());
                rootNode.appendChild(child);
            }

        }
        if(library.getLibraryType().equals("Book"))
        {
            List<Book> book = db.getAllBooksLibrary(library.getLibraryID(),"");

            for (Book object:
                 book) {
                Element child = document.createElement("book");

                String authorName = db.getAuthorByID(object.getAuthorID()).getAuthorName();
                String authorSurname = db.getAuthorByID(object.getAuthorID()).getAuthorSurname();
                child.setAttribute("authorName", authorName );
                child.setAttribute("authorSurname", authorSurname );
                try {
                    String genre = db.getGenre(object.getGenreID()).getGenreName();
                    child.setAttribute("genre",genre);
                }catch (Exception e) {}
                child.setAttribute("title", object.getBookTitle());
                child.setAttribute("ISBN", object.getISBN());
                rootNode.appendChild(child);
            }

        }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void xmlToString()
    {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try{

            transformer = tf.newTransformer();
            StringWriter writer = new StringWriter();

            transformer.transform(new DOMSource(document), new StreamResult(writer));
            output = writer.getBuffer().toString();

        }
        catch (Exception e) {}
    }
}