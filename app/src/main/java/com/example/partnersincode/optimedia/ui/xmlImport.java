package com.example.partnersincode.optimedia.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Book;
import com.example.partnersincode.optimedia.models.Game;
import com.example.partnersincode.optimedia.models.Genre;
import com.example.partnersincode.optimedia.models.Movie;
import com.example.partnersincode.optimedia.models.Series;
import com.example.partnersincode.optimedia.models.WatchObject;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link xmlImport#newInstance} factory method to
 * create an instance of this fragment.
 */
public class xmlImport extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public xmlImport() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment xmlImport.
     */
    // TODO: Rename and change types and number of parameters
    public static xmlImport newInstance(String param1, String param2) {
        xmlImport fragment = new xmlImport();
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
        View root =  inflater.inflate(R.layout.fragment_xml_export, container, false);

        EditText view = root.findViewById(R.id.edtxtXML);
        Button btnImport = root.findViewById(R.id.btnImport);


        Document doc = stringToXML(view.getText().toString());

        convertXML(doc);




        return root;
    }

    private void convertXML(Document doc)
    {

        //TODO db handler
        DatabaseHandler db = new DatabaseHandler(getContext());
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath path = xPathFactory.newXPath();

        try {
            XPathExpression expr = path.compile("//library/");
            NodeList resultSet = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

            Node node = resultSet.item(0).cloneNode(true);


            String libraryName = node.getNodeName();
            ArrayList<Object> library = new ArralyList<>();

            NodeList set1 = node.getChildNodes();

            for (int loop = 0; loop < set1.getLength(); loop++) {

                Node node2 = set1.item(loop);
                String nodeName = node2.getNodeName();

                if(nodeName.equals("series")) {

                    NamedNodeMap map  = node.getAttributes();
                    String title = map.getNamedItem("title").getNodeValue();
                    String genre = map.getNamedItem("genre").getNodeValue();
                    Series series;
                    //TODO everything
                    try{
                        Genre dbGenre = db.getGenre(genre);
                        series = new Series(-1,dbGenre.getGenreID(),title,"", false,false,false);

                    }catch(Exception e)
                    {
                        Genre dbGenre = new Genre(-1,genre);
                        db.addGenre(dbGenre.getGenreName());
                        series = new Series(-1,dbGenre.getGenreID(),title,"", false,false,false);

                    }
                    library.add(series);

                }

                if(nodeName.equals("movie"))
                {
                    NamedNodeMap map  = node.getAttributes();
                    String title = map.getNamedItem("title").getNodeValue();
                    String genre = map.getNamedItem("genre").getNodeValue();
                    Movie movie;
                    //TODO everything
                    try{
                        Genre dbGenre = db.getGenre(genre);
                        movie = new Movie(-1,dbGenre.getGenreID(),title,"", false,false,false);

                    }catch(Exception e)
                    {
                        Genre dbGenre = new Genre(-1,genre);
                        db.addGenre(dbGenre.getGenreName());
                        movie = new Movie(-1,dbGenre.getGenreID(),title,"", false,false,false);

                    }
                    library.add(movie);
                }

                if(nodeName.equals("game"))
                {
                    NamedNodeMap map  = node.getAttributes();
                    String title = map.getNamedItem("title").getNodeValue();
                    String type = map.getNamedItem("type").getNodeValue();
                    String genre = map.getNamedItem("genre").getNodeValue();
                    Game game;
                    //TODO everything
                    try{
                        Genre dbGenre = db.getGenre(genre);
                        game = new Game(-1,dbGenre.getGenreID(),title,type, false,false,false);

                    }catch(Exception e)
                    {
                        Genre dbGenre = new Genre(-1,genre);
                        db.addGenre(dbGenre.getGenreName());
                        game = new Game(-1,dbGenre.getGenreID(),title,type, false,false,false);

                    }
                    library.add(game);
                }

                if(nodeName.equals("book"))
                {
                    NamedNodeMap map  = node.getAttributes();
                    String authorName = map.getNamedItem("authorName").getNodeValue();
                    String authorSurname = map.getNamedItem("authorSurname").getNodeValue();
                    String title = map.getNamedItem("title").getNodeValue();
                    String type = map.getNamedItem("ISBN").getNodeValue();
                    String genre = map.getNamedItem("genre").getNodeValue();
                    Book book;
                    //TODO everything
                    try{
                        Genre dbGenre = db.getGenre(genre);

                        book = new Book(-1,dbGenre.getGenreID(),title,type, false,false,false);

                    }catch(Exception e)
                    {
                        Genre dbGenre = new Genre(-1,genre);
                        db.addGenre(dbGenre.getGenreName());
                        book = new Book(-1,dbGenre.getGenreID(),title,type, false,false,false);

                    }
                    library.add(book);
                }

            }


        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }



//        DatabaseHandler db = new DatabaseHandler(this.getContext());
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        try {
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            document = builder.newDocument();
//            Element rootNode = document.createElement("library");
//            document.appendChild(rootNode);
//            rootNode.setAttribute("libraryName", library.getLibraryName());
//            rootNode.setAttribute("libraryType", library.getLibraryType());
//
//
//            if(library.getLibraryType().equals("Watch"))
//            {
//                List<WatchObject> watch = db.getAllWatchItemsLibrary(library.getLibraryID(), "");
//                for (WatchObject object:
//                        watch) {
//
//                    Element child = null;
//                    if(object instanceof Series)
//                    {
//                        child = document.createElement("series");
//
//                    }
//                    else
//                    {
//                        child = document.createElement("movie");
//
//                    }
//                    try {
//                        String genre = db.getGenre(object.getGenreID()).getGenreName();
//                        child.setAttribute("genre",genre);
//                    }catch (Exception e) {}
//
//                    child.setAttribute("title", object.getTitle());
//                    rootNode.appendChild(child);
//
//                }
//
//            }
//
//            if(library.getLibraryType().equals("Game"))
//            {
//                List<Game> game = db.getAllGamesLibrary(library.getLibraryID(),"");
//                for (Game object: game) {
//
//                    Element child = document.createElement("game");
//                    try {
//                        String genre = db.getGenre(object.getGenreID()).getGenreName();
//                        child.setAttribute("genre",genre);
//                    }catch (Exception e) {}
//                    child.setAttribute("title", object.getGameTitle());
//                    child.setAttribute("gameType", object.getGameType());
//                    rootNode.appendChild(child);
//                }
//
//            }
//            if(library.getLibraryType().equals("Book"))
//            {
//                List<Book> book = db.getAllBooksLibrary(library.getLibraryID(),"");
//
//                for (Book object:
//                        book) {
//                    Element child = document.createElement("book");
//
//                    String authorName = db.getAuthorByID(object.getAuthorID()).getAuthorName();
//                    String authorSurname = db.getAuthorByID(object.getAuthorID()).getAuthorSurname();
//                    child.setAttribute("authorName", authorName );
//                    child.setAttribute("authorSurname", authorSurname );
//                    try {
//                        String genre = db.getGenre(object.getGenreID()).getGenreName();
//                        child.setAttribute("genre",genre);
//                    }catch (Exception e) {}
//                    child.setAttribute("title", object.getBookTitle());
//                    child.setAttribute("ISBN", object.getISBN());
//                    rootNode.appendChild(child);
//                }
//
//            }
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        }
    }

    private Document stringToXML(String contents)
    {
        Document doc;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
             doc = builder.parse(contents);

        }
        catch (Exception e) { e.printStackTrace();}


        return doc;
    }
}