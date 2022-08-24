package com.example.partnersincode.optimedia.models;


import com.example.partnersincode.optimedia.Property;

public class Book {
    //Properties
    private Property<Integer> bookID = new Property<>();
    private Property<Integer> authorID= new Property<>();
    private Property<Integer> genreID= new Property<>();
    private Property<String> ISBN= new Property<>();
    private Property<String> title= new Property<>();
    private Property<Boolean> favourite= new Property<>();
    private Property<Boolean> started= new Property<>();
    private Property<Boolean> complete= new Property<>();
    //add author and genre as fields?



    public Book(int bookID, int authorID, int genreID, String ISBN, String title, Boolean favourite, Boolean started, Boolean complete) {
        this.bookID.set(bookID);
        this.authorID.set(authorID);
        this.genreID.set(genreID);
        this.ISBN.set(ISBN);
        this.title.set(title);
        this.favourite.set(favourite);
        this.started.set(started);
        this.complete.set(complete);
    }


    //Accessors and Modifiers


    //Other methods
    /**
     Writes the details of a book to the console.
     @return Void
     @throws Exception
     */
    public void logBook(){
        try{
            System.out.println("BookID: "+bookID.get()+"\nAuthorID: "+authorID.get()+"\nGenreID: "+genreID.get()+"\nISBN: "+ISBN.get()
                    +"\nTitle: "+title.get()+"\nFavourite: "+favourite.get()+"\nStarted: "+started.get()+"\nComplete: "+complete.get());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
