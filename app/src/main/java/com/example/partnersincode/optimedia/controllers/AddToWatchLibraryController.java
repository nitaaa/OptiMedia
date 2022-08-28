package com.example.partnersincode.optimedia.controllers;

import com.example.partnersincode.optimedia.models.Movie;
import com.example.partnersincode.optimedia.models.Series;
import com.example.partnersincode.optimedia.models.WatchLibrary;

import java.net.URL;
import java.util.ResourceBundle;

public class AddToWatchLibraryController
{





    //Data collections (need to add relevant adaptor and array


    //Processing attributes
    private int objectID;
    private Class objectClass;
    private WatchLibrary SelectedWatchLibrary;


    public AddToWatchLibraryController()
    {


        Movie o = new Movie(0,0, "", false, false, false);



        //if a movie is passed
        if(o.getClass().equals(Movie.class))
        {
            //processed as movie
        }
        //else if a series is passed
        else if(o.getClass().equals(Series.class))
        {
            //processed as series
        }
        else{
            //Leave a log in the console if the wrong datatype is passed
            System.out.println("Object given is not of type series or movie");
            return;
        }


    }


    //methods used for getting media object details needed: ID for database, Name for UI
    private void getMovieDetails(Object o)
    {
        Movie adding = (Movie)o;
        objectID = adding.getMovieID();
        //ui elements to be fixed
        objName.setText(adding.getTitle());
        objName.setText("Movie:");
        objectClass = Movie.class;
    }

    private void getSeriesDetails(Object o)
    {
        Series adding = (Series)o;
        objectID = adding.getSeriesID();
        //Ui elements to be fixed
        objName.setText(adding.getTitle());
        objType.setText("Series");
        objectClass = Series.class;
    }

    private void getListOfWatchLibraries()
    {
        //SQL command added to get list of WatchLibraries



    }



    public void onAddClicked()
    {
        //liblist was the listview in javaFX
        WatchLibrary current = libList.getSelectionModel().getSelectedItem();

        //test code
//        System.out.println(current.toString());
        //Do SQl

        onCancelClicked();

    }



    public void onCancelClicked()
    {
        //close in java
        objType.getScene().getWindow().hide();
    }



}
