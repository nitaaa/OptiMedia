package com.example.partnersincode.optimedia.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Author;
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
import org.xml.sax.InputSource;

import java.io.Serializable;
import java.io.StringReader;
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

    String libraryName;
    String passedXML;
    EditText text;

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

        Bundle bundle = getArguments();
        if(bundle!=null)
        {
            passedXML = bundle.getString("XML");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_xml_import, container, false);
        text = root.findViewById(R.id.edtxtXML);
        text.setSingleLine(false);
        if(passedXML!=null)
        {

            text.setText(passedXML);
        }
        root.findViewById(R.id.btnImport).setOnClickListener(this::onImportClicked);

        return root;
    }


    private ArrayList<Object> convertXML(Document doc)
    {

        //TODO db handler
        DatabaseHandler db = new DatabaseHandler(getContext());
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath path = xPathFactory.newXPath();
        ArrayList<Object> library = new ArrayList<>();

        try {
            XPathExpression expr = path.compile("library");
            NodeList resultSet = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

            Node node = resultSet.item(0).cloneNode(true);


             libraryName = node.getAttributes().getNamedItem("libraryName").getNodeValue();


            NodeList set1 = node.getChildNodes();

            for (int loop = 0; loop < set1.getLength(); loop++) {

                Node node2 = set1.item(loop);
                String nodeName = node2.getNodeName();

                if(nodeName.equals("series")) {

                    NamedNodeMap map  = node2.getAttributes();
                    String title = map.getNamedItem("title").getNodeValue();
                    String genre = map.getNamedItem("genre").getNodeValue();
                    String link = map.getNamedItem("link").getNodeValue();
                    Series series;
                    //TODO everything
                    try{
                        Genre dbGenre = db.getGenre(genre);
                        series = new Series(-1,dbGenre.getGenreID(),title,link, false,false,false);

                    }catch(Exception e)
                    {
                        Genre dbGenre = new Genre(-1,genre);
                        db.addGenre(dbGenre.getGenreName());
                        int genreId = db.getGenre(dbGenre.getGenreName()).getGenreID();
                        series = new Series(-1,genreId,title,"", false,false,false);

                    }
                    library.add(series);

                }

                if(nodeName.equals("movie"))
                {
                    NamedNodeMap map  = node2.getAttributes();
                    String title = map.getNamedItem("title").getNodeValue();
                    String genre = map.getNamedItem("genre").getNodeValue();
                    String link = map.getNamedItem("link").getNodeValue();
                    Movie movie;
                    //TODO everything
                    try{
                        Genre dbGenre = db.getGenre(genre);
                        movie = new Movie(-1,dbGenre.getGenreID(),title,link, false,false,false);

                    }catch(Exception e)
                    {
                        Genre dbGenre = new Genre(-1,genre);
                        db.addGenre(dbGenre.getGenreName());
                        int genreId = db.getGenre(dbGenre.getGenreName()).getGenreID();
                        movie = new Movie(-1,genreId,title,"", false,false,false);

                    }
                    library.add(movie);
                }

                if(nodeName.equals("game"))
                {
                    NamedNodeMap map  = node2.getAttributes();
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
                        int genreId = db.getGenre(dbGenre.getGenreName()).getGenreID();
                        game = new Game(-1,genreId,title,type, false,false,false);

                    }
                    library.add(game);
                }

                if(nodeName.equals("book"))
                {
                    NamedNodeMap map  = node2.getAttributes();
                    String authorName = map.getNamedItem("authorName").getNodeValue();
                    String authorSurname = map.getNamedItem("authorSurname").getNodeValue();
                    String title = map.getNamedItem("title").getNodeValue();
                    String ISBN = map.getNamedItem("ISBN").getNodeValue();
                    String genre = map.getNamedItem("genre").getNodeValue();
                    Book book;
                    //TODO everything
                    Genre dbGenre = null;
                    Author dbAuthor = null;
                    try{
                        dbGenre =  db.getGenre(genre);

                    }catch(Exception e)
                    {
                        dbGenre = new Genre(-1,genre);
                        db.addGenre(dbGenre.getGenreName());
                        int genreId = db.getGenre(dbGenre.getGenreName()).getGenreID();
                        dbGenre.setGenreID(genreId);
                    }

                    try{
                        dbAuthor = db.getAuthorByName(authorSurname,authorName);
                    }catch (Exception e)
                    {
                        Author author = new Author(-1,authorName,authorSurname);
                        db.createNewAuthor(author);
                        dbAuthor =  db.getAuthorByName(authorSurname,authorName);
                    }

                    book = new Book(-1,dbAuthor.getAuthorID(),dbGenre.getGenreID(),ISBN,title, false,false,false);
                    library.add(book);
                }

            }


        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }


        return library;
    }


    private Document stringToXML(String contents) throws Exception
    {
        Document doc = null;

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            doc = builder.parse(new InputSource(new StringReader(contents)));
            return doc;
    }

    private void onImportClicked(View view)
    {


        Document doc = null;
        try {
            doc = stringToXML(text.getText().toString());
            ArrayList<Object>  list = convertXML(doc);

            Bundle bundle = new Bundle();
            bundle.putString("libraryName",libraryName);
            bundle.putSerializable("list", list);


            Navigation.findNavController(view).navigate(R.id.nav_viewList,bundle);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(),getString(R.string.importFail),Toast.LENGTH_SHORT).show();
        }


    }


}