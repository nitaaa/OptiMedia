package com.example.partnersincode.optimedia.models;



public class Book {
    //Properties
    private int bookID;
    private int authorID;
    private int genreID;
    private String ISBN;
    private String title;
    private Boolean favourite;
    private Boolean started;
    private Boolean complete;
    //add author and genre as fields?

    //Constructor
//    public Book(int bookID, int authorID, int genreID, String ISBN, String title, boolean favourite, boolean started, boolean complete) {
//        this.bookID.set(bookID);
//        this.authorID.set(authorID);
//        this.genreID.set(genreID);
//        this.ISBN.set(ISBN);
//        this.title.set(title);
//        this.favourite.set(favourite);
//        this.started.set(started);
//        this.complete.set(complete);
//    }

    public Book(int bookID, int authorID, int genreID, String ISBN, String title, Boolean favourite, Boolean started, Boolean complete) {
        this.bookID = bookID;
        this.authorID = authorID;
        this.genreID = genreID;
        this.ISBN = ISBN;
        this.title = title;
        this.favourite = favourite;
        this.started = started;
        this.complete = complete;
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
