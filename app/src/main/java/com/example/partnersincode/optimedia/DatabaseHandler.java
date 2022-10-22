package com.example.partnersincode.optimedia;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.partnersincode.optimedia.models.Booklog;
import com.example.partnersincode.optimedia.models.GameLog;
import com.example.partnersincode.optimedia.models.Genre;
import com.example.partnersincode.optimedia.models.Library;
import com.example.partnersincode.optimedia.models.MediaObject;
import com.example.partnersincode.optimedia.models.Movie;
import com.example.partnersincode.optimedia.models.MovieLog;
import com.example.partnersincode.optimedia.models.Series;
import com.example.partnersincode.optimedia.models.SeriesLog;
import com.example.partnersincode.optimedia.models.WatchObject;

import com.example.partnersincode.optimedia.models.Author;
import com.example.partnersincode.optimedia.models.Book;
import com.example.partnersincode.optimedia.models.Game;


import java.util.ArrayList;
import java.util.Collection;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "optimediadb";
    private static final int DB_VERSION = 1;

    //tables
    private static final String CREATE_AUTHOR = "CREATE TABLE Author (authorID INTEGER PRIMARY KEY AUTOINCREMENT, authorName TEXT, authorSurname TEXT)";
    private static final String CREATE_BOOK = "CREATE TABLE Book (bookID INTEGER PRIMARY KEY AUTOINCREMENT, authorID INTEGER REFERENCES Author (authorID), genreID INTEGER REFERENCES Genre (genreID), ISBN TEXT, bookTitle TEXT, favourite BOOLEAN, started BOOLEAN, complete BOOLEAN)";
    private static final String CREATE_BOOK_LIBRARY = "CREATE TABLE BookLibrary (libraryID INTEGER REFERENCES Library (libraryID), bookID INTEGER REFERENCES Book (bookID))";
    private static final String CREATE_BOOK_LOG = "CREATE TABLE BookLog (BL_ID INTEGER PRIMARY KEY AUTOINCREMENT, bookID INTEGER REFERENCES Book (bookID), blTitle TEXT, blNote TEXT, blPageNumber INTEGER)";
    private static final String CREATE_GAME = "CREATE TABLE Game (gameID INTEGER PRIMARY KEY AUTOINCREMENT, genreID INTEGER REFERENCES Genre (genreID), gameTitle TEXT, gameType TEXT, favourite BOOLEAN, started BOOLEAN, complete BOOLEAN)";
    private static final String CREATE_GAME_LIBRARY = "CREATE TABLE GameLibrary (libraryID INTEGER REFERENCES Library (libraryID), gameID INTEGER REFERENCES Game (gameID))";
    private static final String CREATE_GAME_LOG = "CREATE TABLE GameLog (GL_ID INTEGER PRIMARY KEY AUTOINCREMENT, gameID INTEGER REFERENCES Game (gameID), glTitle TEXT, glNote TEXT)";
    private static final String CREATE_GENRE = "CREATE TABLE Genre (genreID INTEGER PRIMARY KEY AUTOINCREMENT, genreName TEXT)";
    private static final String CREATE_LIBRARY = "CREATE TABLE Library (libraryID INTEGER PRIMARY KEY AUTOINCREMENT, libraryName TEXT, libraryType TEXT)";
    private static final String CREATE_MOVIE = "CREATE TABLE Movie (movieID INTEGER PRIMARY KEY AUTOINCREMENT, genreID INTEGER REFERENCES Genre (genreID), movieTitle TEXT, favourite BOOLEAN, started BOOLEAN, complete BOOLEAN)";
    private static final String CREATE_MOVIE_LOG = "CREATE TABLE MovieLog (ML_ID INTEGER PRIMARY KEY AUTOINCREMENT, movieID INTEGER REFERENCES Movie (movieID), m_note TEXT, m_timestamp TEXT)";
    private static final String CREATE_SERIES = "CREATE TABLE Series (seriesID INTEGER PRIMARY KEY AUTOINCREMENT, genreID INTEGER REFERENCES Genre (genreID), seriesTitle TEXT, favourite BOOLEAN, started BOOLEAN, complete BOOLEAN)";
    private static final String CREATE_SERIES_LOG = "CREATE TABLE SeriesLog (SL_ID INTEGER PRIMARY KEY AUTOINCREMENT, seriesID INTEGER REFERENCES Series (seriesID), season TEXT, episode TEXT, s_note TEXT, s_timestamp TEXT)";
    private static final String CREATE_WATCH_LIBRARY = "CREATE TABLE WatchLibrary (libraryID INTEGER REFERENCES Library (libraryID), WLI_ID INTEGER REFERENCES WatchListItem (WLI_ID))";
    private static final String CREATE_WATCH_LIST_ITEM = "CREATE TABLE WatchListItem (WLI_ID INTEGER PRIMARY KEY AUTOINCREMENT, seriesID INTEGER REFERENCES Series (seriesID), movieID INTEGER REFERENCES Movie (movieID), link TEXT)";


    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, 1);

        Log.d("createDB", DB_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("createDB", "Creating Tables");
        sqLiteDatabase.execSQL(CREATE_GENRE);
        sqLiteDatabase.execSQL(CREATE_AUTHOR);
        sqLiteDatabase.execSQL(CREATE_LIBRARY);
        sqLiteDatabase.execSQL(CREATE_BOOK);
        sqLiteDatabase.execSQL(CREATE_BOOK_LIBRARY);
        sqLiteDatabase.execSQL(CREATE_BOOK_LOG);
        sqLiteDatabase.execSQL(CREATE_MOVIE);
        sqLiteDatabase.execSQL(CREATE_MOVIE_LOG);
        sqLiteDatabase.execSQL(CREATE_SERIES);
        sqLiteDatabase.execSQL(CREATE_SERIES_LOG);
        sqLiteDatabase.execSQL(CREATE_WATCH_LIST_ITEM);
        sqLiteDatabase.execSQL(CREATE_WATCH_LIBRARY);
        sqLiteDatabase.execSQL(CREATE_GAME);
        sqLiteDatabase.execSQL(CREATE_GAME_LIBRARY);
        sqLiteDatabase.execSQL(CREATE_GAME_LOG);
        Log.d("createDB", "Created Tables");
        addTestData(sqLiteDatabase);
        Log.d("createDB", "Adding Test data");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Genre");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Author");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Book");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS BookLibrary");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS BookLog");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Game");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS GameLibrary");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Library");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Movie");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS MovieLog");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Series");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SeriesLog");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS WatchLibrary");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS WatchListItem");
        onCreate(sqLiteDatabase);
    }

    public void addTestData(SQLiteDatabase sqLiteDatabaseDB){
        //TODO: put in test data then add query
        //add genres
        String query = "INSERT INTO Genre (genreName) VALUES ('Fantasy'),('Science Fiction'),('Romance'),('War/Military'),('Young Adult'),('Children')";
        sqLiteDatabaseDB.execSQL(query);

        //add authors
        query = "INSERT INTO Author (authorName, authorSurname) VALUES ('Sarah J.', 'Maas'),('John', 'Knowles'),('Madeleine', 'LEngle')," +
                "('Jennifer', 'Niven'),('Sabaa', 'Tahir'),('Benjamin', 'Alire Saenz'),('Marissa', 'Meyer')";
        sqLiteDatabaseDB.execSQL(query);

        //add books
        query = "INSERT INTO Book (authorID, genreID, ISBN, bookTitle, favourite, started, complete) VALUES (1, 1, '1635575567 ', 'A Court of Thorns and Roses', 0, 0, 0),"+
        "(6, 5, '1933693584 ', 'Last Night I Sang to the Monster', 0, 0, 0), (5, 1, '9781595148049', 'An Ember in the Ashes', 0, 0, 0)";
        sqLiteDatabaseDB.execSQL(query);

        //add games
        query = " INSERT INTO Game (genreID,gameTitle,gameType,favourite,started,complete) VALUES "+
                "('1','The Witcher 3: Wild Hunt','Open World, RPG',0,0,0), "+
                "('4','Counter-Strike: Global Offensive','FPS Multiplayer',0,0,0), "+
                "('6','Animal Crossing: New Horizons','Life simulation',0,0,0)";
        sqLiteDatabaseDB.execSQL(query);

        //add movies
        query = "INSERT INTO Movie (genreID,movieTitle,favourite,started,complete) VALUES ('5','Paper Towns',0,0,0),"+
                " ('1','Fallen',0,0,0), ('5','The perks of being a wallflower',0,0,0), "+
                " ('2','Ready Player One',0,0,0), ('3','To all the Boys I''ve Loved Before',0,0,0)";
        sqLiteDatabaseDB.execSQL(query);

        //add series
        query = "INSERT INTO Series (genreID,seriesTitle,favourite,started,complete) VALUES ('1','Fate: The Winx Saga',0,0,0),"+
                " ('2','The Umbrella Academy',0,0,0), ('3','Bridgerton',0,0,0), "+
                " ('5','The Summer I Turned Pretty',0,0,0), ('6','Lightyear',0,0,0)";
        sqLiteDatabaseDB.execSQL(query);

        //add watchlist item
        query = "INSERT INTO WatchListItem (seriesID,movieID, link) VALUES ('1',NULL,'https://www.imdb.com/title/tt8179402/'), " +
                "(NULL,'3','https://www.imdb.com/title/tt1659337/'), ('3',NULL,'https://www.imdb.com/title/tt8740790/'), " +
                "('2',NULL,'https://www.imdb.com/title/tt1312171/'), (NULL,'2','https://www.imdb.com/title/tt1564777/'), " +
                "(NULL,'5','https://www.imdb.com/title/tt3846674/')";
        sqLiteDatabaseDB.execSQL(query);

        //add library
        query = "INSERT INTO Library (libraryName,libraryType) VALUES ('To Be Read','Book'), ('Indie Favs','Game'), ('Watch with K','Watch'), " +
                "('Current Reads','Book'), ('Watching123','Watch')";
        sqLiteDatabaseDB.execSQL(query);

        //add book library
        query = "INSERT INTO BookLibrary (libraryID,bookID) VALUES (4,1), (1,3)";
        sqLiteDatabaseDB.execSQL(query);

        //add game library
        query = "INSERT INTO GameLibrary (libraryID,gameID) VALUES (2,1)";
        sqLiteDatabaseDB.execSQL(query);

        //add watch library
        query = "INSERT INTO WatchLibrary (libraryID,WLI_ID)VALUES (3,1), (3,2), (3,3), (5,4),(5,5)";
        sqLiteDatabaseDB.execSQL(query);

        //add book log
        query = "INSERT INTO BookLog (bookID,blTitle,blNote,blPageNumber) VALUES " +
                " ('1','Feyre','I threw myself into that fire, threw myself into it, into him, and let myself burn.','312'),"+
                " ('1','Mr. Archeron','We need hope, or else we cannot endure.','19'), " +
                " ('1','Rhysand','I didn''t want you to fight alone. Or die alone.','413')";
        sqLiteDatabaseDB.execSQL(query);

        //add game log
        query = "INSERT INTO GameLog (gameID,glTitle,glNote) VALUES ('1','Spoilers','Play 1 and 2 first'), "+
                "('1','DLC','New DLC availiable on steam'), ('2','Reset ranks',''), ('3','Replant','Need orange and cherry trees')";
        sqLiteDatabaseDB.execSQL(query);

        //add movie log
        query = "INSERT INTO MovieLog (movieID,m_note,m_timestamp)VALUES ('1','Kinda cringe','1:00'), ('2','Twilight but worse?','18:54'),"+
                " ('3','We accept the love we think we deserve.','00:00')";
        sqLiteDatabaseDB.execSQL(query);

        //add series log
        query = "INSERT INTO SeriesLog (seriesID,season,episode,s_note,s_timestamp) VALUES ('1','1','3','Stopped here','33:45'),"+
                "('3','2','1','Drama','11:25')";
        sqLiteDatabaseDB.execSQL(query);

        Log.d("createDB", "Test data added");
    }

    //SQL QUERIES - see https://demonuts.com/sqlite-multiple-tables/

    /**
     * Creates a new library in the database.
     * Qaanita Fataar
     * @param LibraryType String - Library's Type (Book, Game, Watch)
     */
    public void createLibrary(String LibraryName, String LibraryType){
        Log.d("createLibrary", " starting ");
        SQLiteDatabase db = this.getWritableDatabase();

        //Works both ways:
        //Way 1:
        String query = "INSERT INTO Library (libraryName,libraryType) VALUES ('"+ LibraryName +"','"+ LibraryType +"')";
        if (LibraryType.equals("Movie/Series")){
            query = "INSERT INTO Library (libraryName,libraryType) VALUES ('"+ LibraryName +"','Watch')";
        }
        db.execSQL(query);

        //Way 2: Helpful because you get the ID as well
    //    ContentValues values = new ContentValues();
    //    values.put("libraryName", LibraryName);
    //    if (LibraryType.equals("Movie/Series")){
    //        values.put("libraryType", "Watch");
     //   } else {
     //       values.put("libraryType", LibraryType);
     //   }
     //   long id = db.insertWithOnConflict("Library", null, values, SQLiteDatabase.CONFLICT_IGNORE);
     //   Log.d("createLibrary", "complete // " +id);
    }

    /**
     * Colin O'Linksy
     * @return void
     */
    public void addBookToLib(int LibraryID, int BookID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO BookLibrary (libraryID,bookID) VALUES ("+LibraryID+","+BookID+")";
        db.execSQL(query);
    }

    /**
     * Colin O'Linksy
     * @return void
     */
    public void addLogToBook(String BookID, String blTitle, String blNote,int blPageNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO BookLog (bookID,blTitle,blNote,blPageNumber) VALUES ("+BookID+",'"+blTitle+"','"+blNote+"','"+blPageNumber+"')";
        db.execSQL(query);
    }

    /**
     * Colin O'Linksy
     * @return void
     */
    public void createSeries(String seriesTitle,int genreID, boolean favourite, boolean started,boolean completed) {
        int fav, start, complete;
        fav = (favourite) ? 1 : 0;
        start = (started) ? 1 : 0;
        complete = (completed) ? 1 : 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO Series (seriesTitle,genreID,favourite,started,complete) VALUES ('"+seriesTitle+"',"+genreID+","+fav+","+start+","+complete+")";
        db.execSQL(query);
    }

    /**
     * Gets all Libraries from the database.
     * Qaanita Fataar
     * @return ArrayList<Library>
     */
    @SuppressLint("Range")
    public ArrayList<Library> getAllLibraries() {
        ArrayList<Library> libraryArrayList = new ArrayList<>();

        String selectQuery = "SELECT * FROM Library";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Library library = new Library();
                library.setLibraryID(c.getInt(c.getColumnIndex("libraryID")));
                library.setLibraryName(c.getString(c.getColumnIndex("libraryName")));
                library.setLibraryType(c.getString(c.getColumnIndex("libraryType")));

                libraryArrayList.add(library);
                //Log.d("DatabaseHandler", "getAllLibraries: " + library.toString());
            } while (c.moveToNext());
        }
        c.close();
        return libraryArrayList;
    }

    /**
     * Create new game in database.
     * Qaanita Fataar.
     * @return int ID
     */
    public int createNewGame(Game game){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("genreID", game.getGenreID());
        values.put("gameTitle", game.getGameTitle());
        values.put("gameType", game.getGameType());
        values.put("favourite", game.isFavourite());
        values.put("started", game.isStarted());
        values.put("complete", game.isCompleted());
        long id = db.insertWithOnConflict("Game", null, values, SQLiteDatabase.CONFLICT_IGNORE);
        Log.d("createNewGame", "complete // " +id);

        return (int) id;
    }

    /**
     * Gets all genres from the database.
     * Qaanita Fataar
     * @return ArrayList<Genre>
     */
    @SuppressLint("Range")
    public ArrayList<Genre> getGenres() {
        ArrayList<Genre> genreArrayList = new ArrayList<>();

        String selectQuery = "SELECT * FROM Genre";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Genre genre = new Genre();
                genre.setGenreID(c.getInt(c.getColumnIndex("genreID")));
                genre.setGenreName(c.getString(c.getColumnIndex("genreName")));

                genreArrayList.add(genre);
                Log.d("DatabaseHandler", "getAllLibraries: " + genre.toString());
            } while (c.moveToNext());
        }
        c.close();
        return genreArrayList;
    }

    /**
     * Gets all authors from the database.
     * Alexandria
     * @return ArrayList<Author>
     */
    @SuppressLint("Range")
    public ArrayList<Author> getAuthors() {
        ArrayList<Author> authorArrayList = new ArrayList<>();

        String selectQuery = "SELECT * FROM Author";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Author author = new Author();
                author.setAuthorID(c.getInt(c.getColumnIndex("authorID")));
                author.setAuthorName(c.getString(c.getColumnIndex("authorName")));
                author.setAuthorSurname(c.getString(c.getColumnIndex("authorSurname")));

                authorArrayList.add(author);
                Log.d("DatabaseHandler", "getAllLibraries: " + author.toString());
            } while (c.moveToNext());
        }
        c.close();
        return authorArrayList;
    }

    /**
     * Gets all the Libraries of type watch from the database
     * Adriaan Benn
     * @return ArrayList<WatchLibrary> populated with watchLibraries
     */
    @SuppressLint("Range")
    public ArrayList<Library> getWatchLibraries()
    {
        ArrayList<Library> watchLibraries = new ArrayList<>();

        String SQL = "SELECT * FROM Library WHERE libraryType = \"Watch\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(SQL, null);

        if(c.moveToFirst())
        {
            do{

                int libraryID = c.getInt(c.getColumnIndex("libraryID"));
                String libraryName = c.getString(c.getColumnIndex("libraryName"));
                String libraryType = c.getString(c.getColumnIndex("libraryType"));
                watchLibraries.add(new Library(libraryID,libraryName,libraryType));


            } while (c.moveToNext());
        }
        c.close();
        return watchLibraries;
    }


    /**
     * Database access to get a list of all movies and series that can be added to watch list, hopefully merged
     *
     * @return ArrayList containing all the movies and series
     */
    @SuppressLint("Range")
    public ArrayList<WatchObject> getMoviesAndSeries()
    {
        ArrayList<WatchObject> moviesAndSeries = new ArrayList<>();

        String SQLMovies = "SELECT * FROM Movie ORDER BY movieTitle";

        String SQLSeries = "SELECT * FROM Series ORDER BY seriesTitle";

        SQLiteDatabase db = getReadableDatabase();

        Cursor movies = db.rawQuery(SQLMovies, null);

        Cursor series = db.rawQuery(SQLSeries, null);


        if(movies.moveToFirst())
        {
            do
            {
                int movieID = movies.getInt(movies.getColumnIndex("movieID"));
                int genreID = movies.getInt(movies.getColumnIndex("genreID"));
                String movieTitle = movies.getString(movies.getColumnIndex("movieTitle"));
                boolean favourite = movies.getInt(movies.getColumnIndex("favourite"))==1;
                boolean started = movies.getInt(movies.getColumnIndex("started")) == 1;
                boolean complete = movies.getInt(movies.getColumnIndex("complete"))==1;
                //TODO: get link from database
                moviesAndSeries.add(new Movie(movieID,genreID,movieTitle,"",favourite,started,complete));

            }while(movies.moveToNext());
        }
        movies.close();
        
        if(series.moveToFirst())
        {
            do
            {
                int seriesID = series.getInt(series.getColumnIndex("seriesID"));
                int genreID = series.getInt(series.getColumnIndex("genreID"));
                String seriesTitle = series.getString(series.getColumnIndex("seriesTitle"));
                boolean favourite = series.getInt(series.getColumnIndex("favourite"))==1;
                boolean started = series.getInt(series.getColumnIndex("started")) == 1;
                boolean complete = series.getInt(series.getColumnIndex("complete"))==1;
                //TODO: get link from database
                moviesAndSeries.add(new Series(seriesID,genreID,seriesTitle,"",favourite,started,complete));

            }while(series.moveToNext());
        }
        series.close();


        return moviesAndSeries;
    }


    /**
     * Adds a new movie object to the database, also adds the related WatchListItem
     * Adriaan

     */
    public void addMovie(Movie movie)
    {

        int fav, start, complete;
        fav = (movie.getFavourite()) ? 1 : 0;
        start = (movie.getStarted()) ? 1 : 0;
        complete = (movie.getComplete()) ? 1 : 0;

        SQLiteDatabase db = getReadableDatabase();

        ContentValues adding = new ContentValues();
        adding.put("movieTitle",movie.getTitle());
        adding.put("genreID",movie.getGenreID());
        adding.put("favourite",fav);
        adding.put("started",start);
        adding.put("complete",complete);
        long id = db.insertWithOnConflict("Movie",null,adding, SQLiteDatabase.CONFLICT_IGNORE);

        String SQL = String.format("INSERT INTO WatchListItem (movieID, link)" +
                "\n VALUES (%d, \"%s\" )",id,movie.getLink());

        db.execSQL(SQL);
    }


    /**
     * Checks to see if there is already a movie in db with the movieTitle = title
     * Adriaan Benn
     * @param title of movie we want to check
     * @return true if movie is already in the DB
     */
    public boolean isMovieInDatabase(String title)
    {
        SQLiteDatabase db = getReadableDatabase();

        String SQL = String.format("SELECT * FROM Movie WHERE movieTitle = \"%s\"",title);
        Cursor c = db.rawQuery(SQL,null);
        Boolean inDatabase = c.moveToFirst();
        c.close();
        return inDatabase;
    }


    /**
     * Get the WatchListItemID of a given movie/series
     * SQL extract: WHERE idFieldName = objectID
     * Adriaan Benn
     * @param idFieldName Used to specify if the movie passed is a movieID or seriesID, no alternative. This is the field queried in WLI
     * @param objectID ID we are querying, which will either be a seriesID or a movieID
     * @return int
     */
    @SuppressLint("Range")
    public int getWLI_ID(String idFieldName, int objectID)
    {
        //Return the WLI if the field query field is specified correctly
        if(idFieldName.equals("movieID")||idFieldName.equals("seriesID")) {
            String SQL = String.format("SELECT WLI_ID FROM WatchListItem WHERE %s = %d", idFieldName, objectID);
            SQLiteDatabase db = getReadableDatabase();

            Cursor c = db.rawQuery(SQL, null);
            int WLI_ID = -1;
            if (c.moveToFirst()) {
                WLI_ID = c.getInt(c.getColumnIndex("WLI_ID"));
            }
            c.close();
            return WLI_ID;
        }
        return -1; //return -1 if an invalid field is passed via idFieldName
    }


    /**
     * Add watchlist item to library
     * Adriaan Benn
     * @param library To contain WLI
     * @param WLI_ID pk of WLI
     */
    public void addWLItoLibrary(Library library, int WLI_ID)
    {
        String SQL = String.format("INSERT INTO WatchLibrary (libraryID, WLI_ID) VALUES (%d,%d);", library.getLibraryID(), WLI_ID);

        getReadableDatabase().execSQL(SQL);
    }


    /**
     * Create new book in database.
     * Alexandria
     * @return int ID
     */
    public int createNewBook(Book book) {

        int fav, start, complete;
        fav = (book.isFavourite()) ? 1 : 0;
        start = (book.isStarted()) ? 1 : 0;
        complete = (book.isCompleted()) ? 1 : 0;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("authorID", book.getAuthorID());
        values.put("genreID", book.getGenreID());
        values.put("ISBN", book.getISBN());
        values.put("bookTitle", book.getBookTitle());
        values.put("favourite", fav);
        values.put("started", start);
        values.put("complete", complete);
        long id = db.insertWithOnConflict("Book", null, values, SQLiteDatabase.CONFLICT_IGNORE);
        Log.d("createNewBook", "complete // " +id);

        return (int) id;
    }

    /**
     * Create new author in database.
     * Alexandria
     * @return int ID
     */
    public int createNewAuthor(Author author) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("authorName", author.getAuthorName());
        values.put("authorSurname", author.getAuthorSurname());
        long id = db.insertWithOnConflict("Author", null, values, SQLiteDatabase.CONFLICT_IGNORE);
        Log.d("createNewAuthor", "complete // " +id);

        return (int) id;
    }

    /**
     * Update author in database.
     * Alexandria
     * @return void
     */
    public void updateAuthor(Author author) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "UPDATE Author SET authorName = '"+author.getAuthorName() +"', authorSurname = '"
                    + author.getAuthorSurname() + "' WHERE authorID = "+author.getAuthorID();
            db.execSQL(query);
            Log.d("Update Author", "complete // " +author.getAuthorID());
        } catch (Exception e){
            Log.d("Update Author", "failed: " + e.getMessage());
        }
    }

    /**
     * Find all books in a library
     * Qaanita Fataar
     * @return ArrayList<Book>
     * @param libraryID, searchTerm
     */
    @SuppressLint("Range")
    public ArrayList<Book> getAllBooksLibrary(int libraryID, String searchTerm){
        ArrayList<Book> bookArrayList = new ArrayList<>();

        String selectQuery = "SELECT * FROM BookLibrary WHERE libraryID = " + libraryID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                int bookID = c.getInt(c.getColumnIndex("bookID"));
                Book book = getBookByID(bookID);
                if (book.getBookTitle() != null)  { //&& book.getBookTitle().contains(searchTerm)){
                    bookArrayList.add(book);
                    Log.d("DatabaseHandler", "getAllBooksLibrary: " + bookID);
                }
//                if (searchTerm != null){
//                    if (book.getBookTitle() != null && book.getBookTitle().toLowerCase().contains(searchTerm.toLowerCase())){
//                        bookArrayList.add(book);
//                        Log.d("DatabaseHandler", "getAllBooksLibrary: " + bookID);
//                    }
//                } else {
//                    bookArrayList.add(book);
//                    Log.d("DatabaseHandler", "getAllBooksLibrary: " + bookID);
//                }
            } while (c.moveToNext());
        }
        c.close();
        return bookArrayList;
    }

    /**
     * Find a book by its ID
     * Qaanita Fataar
     * @return Book
     * @param bookID
     */
    @SuppressLint("Range")
    public Book getBookByID(int bookID){
        Book book = new Book();
        String selectQuery = "SELECT * FROM Book WHERE bookID = " + bookID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                book.setBookID(c.getInt(c.getColumnIndex("bookID")));
                book.setAuthorID(c.getInt(c.getColumnIndex("authorID")));
                book.setGenreID(c.getInt(c.getColumnIndex("genreID")));
                book.setBookTitle(c.getString(c.getColumnIndex("bookTitle")));
                book.setISBN(c.getString(c.getColumnIndex("ISBN")));
                book.setFavourite(c.getInt(c.getColumnIndex("favourite")) > 0);
                book.setStarted(c.getInt(c.getColumnIndex("started")) > 0);
                book.setCompleted(c.getInt(c.getColumnIndex("complete")) > 0);
                Log.d("DatabaseHandler", "getAllBooksLibrary: " + bookID);
            } while (c.moveToNext());
        }
        c.close();
        return book;
    }

    /**
     * Find all games in a library
     * Qaanita Fataar
     * @return ArrayList<Game>
     * @param libraryID, searchTerm
     */
    @SuppressLint("Range")
    public ArrayList<Game> getAllGamesLibrary(int libraryID, String searchTerm){
        ArrayList<Game> gameArrayList = new ArrayList<>();

        String selectQuery = "SELECT * FROM GameLibrary WHERE libraryID = " + libraryID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                int gameID = c.getInt(c.getColumnIndex("gameID"));
                Game game = getGameByID(gameID);
                gameArrayList.add(game);
                Log.d("DatabaseHandler", "getAllGamesLibrary: " + gameID);
//                if (searchTerm != null){
//                    if (game.getGameTitle() != null && game.getGameTitle().toLowerCase().contains(searchTerm.toLowerCase())){
//                        gameArrayList.add(game);
//                        Log.d("DatabaseHandler", "getAllGamesLibrary: " + gameID);
//                    }
//                } else {
//                    gameArrayList.add(game);
//                    Log.d("DatabaseHandler", "getAllGamesLibrary: " + gameID);
//                }
            } while (c.moveToNext());
        }
        c.close();
        return gameArrayList;
    }

    /**
     * Find a book by its ID
     * Qaanita Fataar
     * @return Book
     * @param gameID
     */
    @SuppressLint("Range")
    public Game getGameByID(int gameID){
        Game game = new Game();
        String selectQuery = "SELECT * FROM Game WHERE gameID = " + gameID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                game.setGameID(c.getInt(c.getColumnIndex("gameID")));
                game.setGenreID(c.getInt(c.getColumnIndex("genreID")));
                game.setGameTitle(c.getString(c.getColumnIndex("gameTitle")));
                game.setGameType(c.getString(c.getColumnIndex("gameType")));
                game.setFavourite(c.getInt(c.getColumnIndex("favourite")) > 0);
                game.setStarted(c.getInt(c.getColumnIndex("started")) > 0);
                game.setCompleted(c.getInt(c.getColumnIndex("complete")) > 0);
                Log.d("DatabaseHandler", "getAllBooksLibrary: " + gameID);
            } while (c.moveToNext());
        }
        c.close();
        return game;
    }

    /**
     * Find all watchlist items  in a library
     * Qaanita Fataar
     * @return ArrayList<WatchObject>
     * @param libraryID, searchTerm
     */
    @SuppressLint("Range")
    public ArrayList<WatchObject> getAllWatchItemsLibrary(int libraryID, String searchTerm){
        ArrayList<WatchObject> watchList = new ArrayList<>();

        String selectQuery = "SELECT * FROM WatchLibrary WHERE libraryID = " + libraryID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                int watchListItemID = c.getInt(c.getColumnIndex("WLI_ID"));
                Log.d("getAllWatchItemsLibrary - ", "wli_id: " + watchListItemID);
                watchList.add(getWatchItemByID(watchListItemID));
//                WatchObject watchItem = getWatchItemByID(watchListItemID);
//                if (searchTerm != null){
//                    if (watchItem.getTitle() != null && watchItem.getTitle().toLowerCase().contains(searchTerm.toLowerCase())){
//                        watchList.add(watchItem);
//                        Log.d("DatabaseHandler", "getAllWatchItemsLibrary: " + watchListItemID);
//                    }
//                } else {
//                    watchList.add(watchItem);
//                    Log.d("DatabaseHandler", "getAllWatchItemsLibrary: " + watchListItemID);
//                }

            } while (c.moveToNext());
        }
        c.close();
        return watchList;
    }
    
    /**
     * Find a watch list item by ID
     * Qaanita Fataar
     * @return WatchObject
     * @param WLI_ID
     */
    @SuppressLint("Range")
    public WatchObject getWatchItemByID(int WLI_ID) {
        String selectQuery = "SELECT * FROM WatchListItem WHERE WLI_ID = " + WLI_ID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            Log.d("TAG", "getWatchItemByID: getting items:"+WLI_ID);
            if (!c.isNull(c.getColumnIndex("movieID"))) {
                int movieID = c.getInt(c.getColumnIndex("movieID"));
                Log.d("getAllWatchItemsLibrary:", "is movie, movieID" + movieID);
                Movie movie = getMovieByID(movieID);
                movie.setLink(c.getString(c.getColumnIndex("link")));
                return movie;
            } else if (!c.isNull(c.getColumnIndex("seriesID"))) {
                int seriesID = c.getInt(c.getColumnIndex("seriesID"));
                Log.d("getAllWatchItemsLibrary:", "is series, seriesID" + seriesID);
                Series series = getSeriesByID(seriesID);
                series.setLink(c.getString(c.getColumnIndex("link")));
                return series;
            }
        }
        c.close();
        return new WatchObject();
    }
    
    /**
     * Find a movie by ID
     * Qaanita Fataar
     * @return Movie
     * @param movieID
     */
    @SuppressLint("Range")
    public Movie getMovieByID(int movieID) {
        Movie movie = new Movie();
        String selectQuery = "SELECT * FROM Movie WHERE movieID = " + movieID;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                movie.setMovieID(c.getInt(c.getColumnIndex("movieID")));
                movie.setGenreID(c.getInt(c.getColumnIndex("genreID")));
                movie.setTitle(c.getString(c.getColumnIndex("movieTitle")));
                movie.setFavourite(c.getInt(c.getColumnIndex("favourite")) > 0);
                movie.setStarted(c.getInt(c.getColumnIndex("started")) > 0);
                movie.setComplete(c.getInt(c.getColumnIndex("complete")) > 0);
                Log.d("DatabaseHandler", "getMovieByID: " + movie.getTitle());
            } while (c.moveToNext());
        }
        c.close();
        return movie;
    }
    
     /**
     * Find a series by ID
     * Qaanita Fataar
     * @return Series
     * @param seriesID
     */
    @SuppressLint("Range")
    public Series getSeriesByID(int seriesID) {
        Series series = new Series();
        String selectQuery = "SELECT * FROM Series WHERE seriesID = " + seriesID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                series.setSeriesID(c.getInt(c.getColumnIndex("seriesID")));
                series.setGenreID(c.getInt(c.getColumnIndex("genreID")));
                series.setTitle(c.getString(c.getColumnIndex("seriesTitle")));
                series.setFavourite(c.getInt(c.getColumnIndex("favourite")) > 0);
                series.setStarted(c.getInt(c.getColumnIndex("started")) > 0);
                series.setComplete(c.getInt(c.getColumnIndex("complete")) > 0);
                Log.d("DatabaseHandler", "getSeriesByID: " + series.getTitle());
            } while (c.moveToNext());
        }
        c.close();
        return series;
    }

    /**
     * Find an author by ID
     * Qaanita Fataar
     * @return Author
     * @param authorID
     */
    @SuppressLint("Range")
    public Author getAuthorByID(int authorID) {
        Author author = new Author();
        String selectQuery = "SELECT * FROM Author WHERE authorID = " + authorID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                author.setAuthorID(c.getInt(c.getColumnIndex("authorID")));
                author.setAuthorName(c.getString(c.getColumnIndex("authorName")));
                author.setAuthorSurname(c.getString(c.getColumnIndex("authorSurname")));
                Log.d("DatabaseHandler", "getAuthorByID: " + author.getFullName());
            } while (c.moveToNext());
        }
        c.close();
        return author;
    }
    @SuppressLint("Range")
    public Author getAuthorByName(String fName,String lName) {
        Author author = new Author();
        String selectQuery = "SELECT * FROM Author WHERE authorName = \'" + fName+"\' and authorSurname = \'"+lName+"\'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                author.setAuthorID(c.getInt(c.getColumnIndex("authorID")));
                author.setAuthorName(c.getString(c.getColumnIndex("authorName")));
                author.setAuthorSurname(c.getString(c.getColumnIndex("authorSurname")));
                Log.d("DatabaseHandler", "getAuthorByID: " + author.getFullName());
            } while (c.moveToNext());
        }
        c.close();
        return author;
    }

    /**
     * Delete a library
     * Qaanita Fataar
     * @return void
     * @param libraryID,libraryType
     */
    public void deleteLibrary(int libraryID, String libraryType){
        String deleteLibQuery = "DELETE FROM Library WHERE libraryID = "+libraryID;
        String deleteSubLibQuery = "";
        switch (libraryType) {
            case "Book":
                deleteSubLibQuery = "DELETE FROM BookLibrary WHERE libraryID = " + libraryID;
                break;
            case "Game":
                deleteSubLibQuery = "DELETE FROM GameLibrary WHERE libraryID = " + libraryID;
                break;
            case "Watch":
                deleteSubLibQuery = "DELETE FROM WatchLibrary WHERE libraryID = " + libraryID;
                break;
        }
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            if (!deleteSubLibQuery.equals(""))
                db.execSQL(deleteSubLibQuery);
            db.execSQL(deleteLibQuery);

            Log.d("deleteLibrary", "Deleted " + libraryID);
        } catch(Exception e){
            Log.d("deleteLibrary", "Error \n" + e.getMessage());
        }
    }

    public void updateGenre(int id,String genre) {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "UPDATE Genre SET genreName = '"+genre +"' WHERE genreID = "+id;
            db.execSQL(query);
    }

    public void addGenre(String genre) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Insert into Genre (genreName) values ('"+genre +"')";
        db.execSQL(query);
    }

    @SuppressLint("Range")
    public ArrayList<Booklog> getBooklogsFromBook(int bookID) {
        ArrayList<Booklog> blArrayList = new ArrayList<>();

        if (bookID!=-1) {
            String selectQuery = "SELECT * FROM Booklog where bookID = " + bookID;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery(selectQuery, null);
            if (c.moveToFirst()) {
                do {
                    Booklog booklog = new Booklog();
                    booklog.setBL_ID(c.getInt(c.getColumnIndex("BL_ID")));
                    booklog.setBookID(bookID);
                    booklog.setBlTitle(c.getString(c.getColumnIndex("blTitle")));
                    booklog.setBlNote(c.getString(c.getColumnIndex("blNote")));
                    booklog.setBlPageNumber(c.getInt(c.getColumnIndex("blPageNumber")));

                    blArrayList.add(booklog);
                } while (c.moveToNext());
            }
            c.close();
        }else{
            String selectQuery = "SELECT * FROM Booklog";
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery(selectQuery, null);
            if (c.moveToFirst()) {
                do {
                    Booklog booklog = new Booklog();
                    booklog.setBL_ID(c.getInt(c.getColumnIndex("BL_ID")));
                    booklog.setBookID(c.getInt(c.getColumnIndex("bookID")));
                    booklog.setBlTitle(c.getString(c.getColumnIndex("blTitle")));
                    booklog.setBlNote(c.getString(c.getColumnIndex("blNote")));
                    booklog.setBlPageNumber(c.getInt(c.getColumnIndex("blPageNumber")));

                    blArrayList.add(booklog);
                } while (c.moveToNext());
            }
            c.close();

        }
        return blArrayList;
    }

    public void updateBooklog(int id,String Title, String Note,int PN) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Booklog SET blTitle = '"+Title +"',blNote = '"+Note +"', blPageNumber= "+PN +"  WHERE BL_ID = "+id;
        db.execSQL(query);
    }

    public void addBooklog(int bookID,String Title, String Note,int PN) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Insert into Booklog (bookID,blTitle,blNote,blPageNumber) " +
                "values ("+bookID+",'"+Title+"', '"+Note+"', "+PN+")";
        db.execSQL(query);
    }


    /**
     * Update a library (change name)
     * Qaanita Fataar
     * @return void
     * @param libraryID,libraryName
     */
    public void updateLibrary(int libraryID, String libraryName){
        String updateQuery = "UPDATE Library SET libraryName = '"+libraryName.replace("'", "''")
                +"' WHERE libraryID = "+libraryID;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL(updateQuery);
            Log.d("updateLibrary", "Updated " + libraryID+", "+libraryName);
        } catch(Exception e){
            Log.d("updateLibrary", "Error \n" + e.getMessage());
        }
    }

    /**
     * Update a Series (favourite, started, complete)
     * Qaanita Fataar
     * @return void
     * @param series
     */
    public void updateSeries(Series series){
        int fav, start, complete;
        fav = (series.getFavourite()) ? 1 : 0;
        start = (series.getStarted()) ? 1 : 0;
        complete = (series.getComplete()) ? 1 : 0;
        String updateQuery = "UPDATE Series SET favourite = "+fav+", started = "+start
                +", complete = "+complete+" WHERE seriesID = "+series.getSeriesID();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL(updateQuery);
            Log.d("updateLibrary", "Updated " + series.toString());
        } catch(Exception e){
            Log.d("updateLibrary", "Error \n" + e.getMessage());
        }
    }

    /**
     * Update a Movie (favourite, started, complete)
     * Adriaan Benn && Alexandria Damons
     * @return void
     * @param  movie
     */
    public void updateMovie(Movie movie){
        int fav, start, complete;
        fav = (movie.getFavourite()) ? 1 : 0;
        start = (movie.getStarted()) ? 1 : 0;
        complete = (movie.getComplete()) ? 1 : 0;
        String updateQuery = "UPDATE Movie SET favourite = "+fav+", started = "+start
                +", complete = "+complete+" WHERE movieID = "+movie.getMovieID();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL(updateQuery);
            
        } catch(Exception e){
           e.printStackTrace();
        }
    }

    /**
     * Update a Game (favourite, started, complete)
     * Adriaan Benn && Alexandria Damons
     * @return void
     * @param  game
     */
    public void updateGame(Game game){
        int fav, start, complete;
        fav = (game.isFavourite()) ? 1 : 0;
        start = (game.isStarted()) ? 1 : 0;
        complete = (game.isCompleted()) ? 1 : 0;
        String updateQuery = "UPDATE Game SET favourite = "+fav+", started = "+start
                +", complete = "+complete+" WHERE gameID = "+game.getGameID();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL(updateQuery);

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Update a Book (favourite, started, complete)
     * Adriaan Benn
     * @return void
     * @param  book
     */
    public void updateBook(Book book){
        int fav, start, complete;
        fav = (book.isFavourite()) ? 1 : 0;
        start = (book.isStarted()) ? 1 : 0;
        complete = (book.isCompleted()) ? 1 : 0;
        String updateQuery = "UPDATE Book SET favourite = "+fav+", started = "+start
                +", complete = "+complete+" WHERE bookID = "+book.getBookID();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL(updateQuery);

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Create a record/log for a series
     * Qaanita Fataar
     * @return int
     */
    public int createSeriesLog(SeriesLog seriesLog){
        //INSERT INTO SeriesLog (seriesID,season,episode,s_note,s_timestamp)

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("seriesID",seriesLog.getSeriesID());
        values.put("season", seriesLog.getSeason());
        values.put("episode", seriesLog.getEpisode());
        values.put("s_note", seriesLog.getS_note());
        values.put("s_timestamp", seriesLog.getS_timestamp());
        long id = db.insertWithOnConflict("SeriesLog", null, values, SQLiteDatabase.CONFLICT_IGNORE);
        Log.d("createSeriesLog", "complete // " +id);

        return (int) id;
    }

    /**
     * Get all logs for a series (by ID)
     * Qaanita Fataar
     * @return ArrayList<SeriesLog>
     * @param seriesID
     */
    @SuppressLint("Range")
    public ArrayList<SeriesLog> getAllSeriesLogByID(int seriesID){
        ArrayList<SeriesLog> seriesLogs = new ArrayList<>();
        String selectQuery = "SELECT * FROM SeriesLog WHERE seriesID = " + seriesID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                SeriesLog seriesLog = new SeriesLog();
                seriesLog.setSL_ID(c.getInt(c.getColumnIndex("SL_ID")));
                seriesLog.setSeriesID(c.getInt(c.getColumnIndex("seriesID")));
                seriesLog.setSeason(c.getString(c.getColumnIndex("season")));
                seriesLog.setEpisode(c.getString(c.getColumnIndex("episode")));
                seriesLog.setS_note(c.getString(c.getColumnIndex("s_note")));
                seriesLog.setS_timestamp(c.getString(c.getColumnIndex("s_timestamp")));
                seriesLogs.add(seriesLog);
                Log.d("DatabaseHandler", "getAllSeriesLogByID: " + seriesLog.toString());
            } while (c.moveToNext());
        }
        c.close();
        return seriesLogs;
    }

    /**
     * Get a single log for a series (by SL_ID)
     * Qaanita Fataar
     * @return ArrayList<SeriesLog>
     * @param SL_ID
     */
    @SuppressLint("Range")
    public SeriesLog getSeriesLogByID(int SL_ID){
        SeriesLog seriesLog = new SeriesLog();
        String selectQuery = "SELECT * FROM SeriesLog WHERE SL_ID = " + SL_ID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                seriesLog.setSL_ID(c.getInt(c.getColumnIndex("SL_ID")));
                seriesLog.setSeriesID(c.getInt(c.getColumnIndex("seriesID")));
                seriesLog.setSeason(c.getString(c.getColumnIndex("season")));
                seriesLog.setEpisode(c.getString(c.getColumnIndex("episode")));
                seriesLog.setS_note(c.getString(c.getColumnIndex("s_note")));
                seriesLog.setS_timestamp(c.getString(c.getColumnIndex("s_timestamp")));
                Log.d("DatabaseHandler", "getAllSeriesLogByID: " + seriesLog.toString());
            } while (c.moveToNext());
        }
        c.close();
        return seriesLog;
    }
    

    /**
     * Gets all the Libraries of type watch from the database
     * Adriaan Benn
     * @return ArrayList<WatchLibrary> populated with watchLibraries
     */
    @SuppressLint("Range")
    public ArrayList<Library> getGameLibraries()
    {
        ArrayList<Library> gameLibraries = new ArrayList<>();

        String SQL = "SELECT * FROM Library WHERE libraryType = \"Game\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(SQL, null);

        if(c.moveToFirst())
        {
            do{

                int libraryID = c.getInt(c.getColumnIndex("libraryID"));
                String libraryName = c.getString(c.getColumnIndex("libraryName"));
                String libraryType = c.getString(c.getColumnIndex("libraryType"));
                gameLibraries.add(new Library(libraryID,libraryName,libraryType));


            } while (c.moveToNext());
        }
        c.close();
        return gameLibraries;
    }

    @SuppressLint("Range")
    public ArrayList<Library> getBookLibraries()
    {
        ArrayList<Library> BookLibraries = new ArrayList<>();

        String SQL = "SELECT * FROM Library WHERE libraryType = \"Book\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(SQL, null);

        if(c.moveToFirst())
        {
            do{

                int libraryID = c.getInt(c.getColumnIndex("libraryID"));
                String libraryName = c.getString(c.getColumnIndex("libraryName"));
                String libraryType = c.getString(c.getColumnIndex("libraryType"));
                BookLibraries.add(new Library(libraryID,libraryName,libraryType));


            } while (c.moveToNext());
        }
        return BookLibraries;
    }

    /**
     * Gets all authors from the database.
     * Alexandria
     * @return ArrayList<Author>
     */
    @SuppressLint("Range")
    public ArrayList<Game> getGames() {
        ArrayList<Game> gameArrayList = new ArrayList<>();

        String selectQuery = "SELECT * FROM Game";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Game game = new Game();
                game.setGameID(c.getInt(c.getColumnIndex("gameID")));
                game.setGenreID(c.getInt(c.getColumnIndex("genreID")));
                game.setGameTitle(c.getString(c.getColumnIndex("gameTitle")));
                game.setGameType(c.getString(c.getColumnIndex("gameType")));
                game.setFavourite(c.getInt(c.getColumnIndex("favourite"))==1);
                game.setStarted(c.getInt(c.getColumnIndex("started")) == 1);
                game.setCompleted(c.getInt(c.getColumnIndex("complete"))==1);

                gameArrayList.add(game);
                Log.d("DatabaseHandler", "getGameLibraries: " + game.toString());
            } while (c.moveToNext());
        }
        c.close();
        return gameArrayList;
    }

    /**
     * Gets all authors from the database.
     * Alexandria
     * @return ArrayList<Author>
     */
    @SuppressLint("Range")
    public ArrayList<Game> getGames(String string) {
        ArrayList<Game> gameArrayList = new ArrayList<>();

        String selectQuery = "SELECT * FROM Game WHERE gameTitle LIKE '%" + string +"%'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Game game = new Game();
                game.setGameID(c.getInt(c.getColumnIndex("gameID")));
                game.setGenreID(c.getInt(c.getColumnIndex("genreID")));
                game.setGameTitle(c.getString(c.getColumnIndex("gameTitle")));
                game.setGameType(c.getString(c.getColumnIndex("gameType")));
                game.setFavourite(c.getInt(c.getColumnIndex("favourite"))==1);
                game.setStarted(c.getInt(c.getColumnIndex("started")) == 1);
                game.setCompleted(c.getInt(c.getColumnIndex("complete"))==1);

                gameArrayList.add(game);
                Log.d("DatabaseHandler", "getGameLibraries: " + game.toString());
            } while (c.moveToNext());
        }
        c.close();
        return gameArrayList;
    }

    @SuppressLint("Range")
    public ArrayList<Book> getBooks(String string) {
        ArrayList<Book> bookArrayList = new ArrayList<>();

        String selectQuery = "SELECT * FROM Book WHERE bookTitle LIKE '%" + string +"%'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Book book = new Book();
                book.setBookID(c.getInt(c.getColumnIndex("bookID")));
                book.setAuthorID(c.getInt(c.getColumnIndex("authorID")));
                book.setGenreID(c.getInt(c.getColumnIndex("genreID")));
                book.setISBN(c.getString(c.getColumnIndex("ISBN")));
                book.setBookTitle(c.getString(c.getColumnIndex("bookTitle")));
                book.setFavourite(c.getInt(c.getColumnIndex("favourite")) > 0);
                book.setStarted(c.getInt(c.getColumnIndex("started")) > 0);
                book.setCompleted(c.getInt(c.getColumnIndex("complete")) > 0);

                bookArrayList.add(book);
                Log.d("DatabaseHandler", "getBookLibraries: " + book.toString());
            } while (c.moveToNext());
        }
        c.close();
        return bookArrayList;
    }

    @SuppressLint("Range")
    public ArrayList<Book> getBooks() {
        ArrayList<Book> bookArrayList = new ArrayList<>();

        String selectQuery = "SELECT * FROM Book";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Book book = new Book();
                book.setBookID(c.getInt(c.getColumnIndex("bookID")));
                book.setAuthorID(c.getInt(c.getColumnIndex("authorID")));
                book.setGenreID(c.getInt(c.getColumnIndex("genreID")));
                book.setISBN(c.getString(c.getColumnIndex("ISBN")));
                book.setBookTitle(c.getString(c.getColumnIndex("bookTitle")));
                book.setFavourite(c.getInt(c.getColumnIndex("favourite"))==1);
                book.setStarted(c.getInt(c.getColumnIndex("started")) == 1);
                book.setCompleted(c.getInt(c.getColumnIndex("complete"))==1);

                bookArrayList.add(book);
                Log.d("DatabaseHandler", "getBookLibraries: " + book.toString());
            } while (c.moveToNext());
        }
        c.close();
        return bookArrayList;
    }

    /**
     * Create new author in database.
     * Alexandria
     * @return int ID
     */
    public int createGameLibrary(Game game, Library library) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("libraryID", library.getLibraryID());
        values.put("gameID", game.getGameID());
        long id = db.insertWithOnConflict("GameLibrary", null, values, SQLiteDatabase.CONFLICT_IGNORE);
        Log.d("createGameLibrary", "complete // " +id);

        return (int) id;
    }

    public int createBookLibrary(Book book, Library library) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("libraryID", library.getLibraryID());
        values.put("bookID", book.getBookID());
        long id = db.insertWithOnConflict("BookLibrary", null, values, SQLiteDatabase.CONFLICT_IGNORE);
        Log.d("createBookLibrary", "complete // " +id);

        return (int) id;
    }

    /**
     * Create new movie log in database.
     * Alexandria
     * @return int ID
     */
    public void addMovieLog(MovieLog log) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Insert into Movielog (movieID,m_note,m_timestamp) " +
                "values ("+log.getMovieID()+",'"+log.getM_note()+"', '"+log.getM_timestamp()+"')";
        db.execSQL(query);

    }

    /**
     * Gets all the movie logs from the database
     * Alexandria
     * @return ArrayList<MovieLog> populated with list
     */
    @SuppressLint("Range")
    public ArrayList<MovieLog> getMovieLog()
    {
        ArrayList<MovieLog> logs = new ArrayList<>();

        String SQL = "SELECT * FROM MovieLog";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(SQL, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                MovieLog movieLog = new MovieLog();
                movieLog.setML_ID(c.getInt(c.getColumnIndex("ML_ID")));
                movieLog.setMovieID(c.getInt(c.getColumnIndex("movieID")));
                movieLog.setM_note(c.getString(c.getColumnIndex("m_note")));
                movieLog.setM_timestamp(c.getString(c.getColumnIndex("m_timestamp")));

                logs.add(movieLog);
                Log.d("DatabaseHandler", "getMovieLogs: " + movieLog.toString());
            } while (c.moveToNext());
        }
        c.close();
        return logs;
    }


    /**
     * Updates a movie log in the database.
     * Alexandria
     * @return
     */
    public void updateMovieLog(int id, String note,String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Movielog SET m_note = '"+ note +"',m_timestamp = '"+ time +"' WHERE ML_ID = "+id;
        db.execSQL(query);
    }


    /**
     * Gets all genres from the database.
     * Alexandria
     * @return ArrayList<Genre>
     */
    @SuppressLint("Range")
    public Genre getGenre(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = String.format("SELECT * FROM GENRE WHERE genreID = %d",id);

        Genre genre = null;
        Cursor c = db.rawQuery(sql,null);
        if(c.moveToFirst())
        {
            do {
                String genreName = c.getString(c.getColumnIndex("genreName"));

                genre = new Genre(id,genreName);

            } while (c.moveToNext());
        }
        return genre;
    }

    /**
     * Gets a specific genre from the database.
     * Alexandria
     * @return genre
     */
    @SuppressLint("Range")
    public Genre getGenre(String Name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = String.format("SELECT * FROM GENRE WHERE genreName = \'%s\'",Name);

        Genre genre = null;
        Cursor c = db.rawQuery(sql,null);
        if(c.moveToFirst())
        {
            do {
                int id = c.getInt(c.getColumnIndex("genreID"));

                genre = new Genre(id,Name);

            } while (c.moveToNext());
        }
        return genre;
    }


    public int statsSeriesLogCount() {
            SQLiteDatabase db = this.getWritableDatabase();
            String sql = String.format("Select count(SL_ID) From SeriesLog");
        Cursor c = db.rawQuery(sql,null);
        c.moveToFirst();
            return  c.getInt(0);
    }

    public int statsMovieLogCount() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = String.format("Select count(ML_ID) From MovieLog");
        Cursor c = db.rawQuery(sql,null);
        c.moveToFirst();
        return  c.getInt(0);
    }

    public int statsGameLogCount() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = String.format("Select count(GL_ID) From GameLog");
        Cursor c = db.rawQuery(sql,null);
        c.moveToFirst();
        return  c.getInt(0);
    }

    public int statsBookLogCount() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = String.format("Select count(BL_ID) From BookLog");
        Cursor c = db.rawQuery(sql,null);
        c.moveToFirst();
        return  c.getInt(0);
    }

    public int statsFinishedSeries() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = String.format("Select count(seriesID) From Series where complete=1");
        Cursor c = db.rawQuery(sql,null);
        c.moveToFirst();
        return  c.getInt(0);
    }

    public int statsFinishedMovies() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = String.format("Select count(movieID) From Movie where complete=1");
        Cursor c = db.rawQuery(sql,null);
        c.moveToFirst();
        return  c.getInt(0);
    }

    public int statsFinishedGames() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = String.format("Select count(gameID) From Game where complete=1");
        Cursor c = db.rawQuery(sql,null);
        c.moveToFirst();
        return  c.getInt(0);
    }

    public int statsFinishedBooks() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = String.format("Select count(bookID) From Book where complete=1");
        Cursor c = db.rawQuery(sql,null);
        c.moveToFirst();
        return  c.getInt(0);
    }

    //TODO seems to be an error with this
    public String statsSeriesPopGenre() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Select genreName,count(genreID) From Book inner join Genre" +
                " on Book.genreID=Genre.genreID group by genreName order by count(genreID) desc";
        Cursor c = db.rawQuery(sql,null);
        c.moveToFirst();
        return  c.getString(0);
     }

    /**
     * Gets all the game logs from the database
     * Alexandria
     * @return ArrayList<GameLog> populated with list
     */
    @SuppressLint("Range")
    //TODO Refactor name
    public ArrayList<GameLog> getGameLogs()
    {

        ArrayList<GameLog> logs = new ArrayList<>();


        String SQL = "SELECT * FROM GameLog";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(SQL, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {

                GameLog gameLog = new GameLog();
                gameLog.setGL_ID(c.getInt(c.getColumnIndex("GL_ID")));
                gameLog.setGameID(c.getInt(c.getColumnIndex("gameID")));
                gameLog.setGLTitle(c.getString(c.getColumnIndex("glTitle")));
                gameLog.setGLNote(c.getString(c.getColumnIndex("glNote")));

                logs.add(gameLog);
                Log.d("DatabaseHandler", "getGameLogs: " + gameLog.toString());
            } while (c.moveToNext());
        }
        c.close();
        return logs;
    }

    /**
     * Updates a game log in the database.
     * Alexandria
     * @return
     */
    public void updateGameLog(int id, String title,String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE GameLog SET glTitle = '"+ title +"',glNote = '"+ note +"' WHERE GL_ID = "+id;
        db.execSQL(query);
    }

    /**
     * Create new game log in database.
     * Alexandria
     * @return int ID
     */
    public void addGameLog(GameLog log) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Insert into Gamelog (gameID,glTitle,glNote) " +
                "values ("+log.getGameID()+",'"+log.getGLTitle()+"', '"+log.getGLNote()+"')";
        db.execSQL(query);

    }

    /**
     * Adriaan
     * SQL to remove an media object from the library
     * @param library from which object is being removed from
     * @param object Media object being removed
     */
    public void removeFromLibrary(Library library, Object object)
    {
        int libraryID=library.getLibraryID();

        SQLiteDatabase db = getReadableDatabase();

        if(object instanceof Book)
        {
            Book cur = (Book) object;
            int bookID = cur.getBookID();
            String SQL = String.format("DELETE FROM BookLibrary WHERE \n bookIO = %d AND libraryID = %d",
                    bookID, libraryID);
            db.execSQL(SQL);


        }
        else if(object instanceof Game)
        {
            Game cur = (Game) object;
            int gameID = cur.getGameID();
            String SQL = String.format("DELETE FROM GameLibrary WHERE \n gameIO = %d AND libraryID = %d",
                    gameID, libraryID);
            db.execSQL(SQL);

        }
        else if(object instanceof WatchObject) {
            WatchObject cur = (WatchObject) object;
            int objectID = cur.getID();

            String SQL = String.format(
                    "DELETE FROM WatchLibrary WHERE libraryID = %d AND" +
                            "WLI_ID = (SELECT WLI_ID FROM WatchListItem WHERE seriesID = %d or movieID = %d )"
                    , libraryID, objectID, objectID);

            db.execSQL(SQL);
        }
    }

    public Collection<WatchObject> getWatchItems(String keyword) {
        ArrayList<WatchObject> watchList = new ArrayList<>();

        String selectQuery = "SELECT * FROM WatchLibrary";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                @SuppressLint("Range") int watchListItemID = c.getInt(c.getColumnIndex("WLI_ID"));
                Log.d("getAllWatchItemsLibrary - ", "wli_id: " + watchListItemID);
                WatchObject watchItem = getWatchItemByID(watchListItemID);
                if (keyword != null){
                    if (watchItem.getTitle() != null && watchItem.getTitle().toLowerCase().contains(keyword.toLowerCase())){
                        watchList.add(watchItem);
                        Log.d("DatabaseHandler", "getWatchItems: " + watchItem.getTitle());
                    }
                } else {
                    watchList.add(watchItem);
                    Log.d("DatabaseHandler", "getWatchItems: " + watchItem.getTitle());
                }

            } while (c.moveToNext());
        }
        c.close();
        return watchList;
    }

    @SuppressLint("Range")
    public int getWLI_IDbySeriesID(int sID){
        String selectQuery = "SELECT * FROM WatchListItem WHERE seriesID = " +sID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        int wli_id = 0;
        if (c.moveToFirst()){
            wli_id = c.getInt(c.getColumnIndex("WLI_ID"));
        }
        return wli_id;
    }

    @SuppressLint("Range")
    public int getWLI_IDbyMovieID(int mID){
        String selectQuery = "SELECT * FROM WatchListItem WHERE movieID = " +mID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        int wli_id = 0;
        if (c.moveToFirst()){
            wli_id = c.getInt(c.getColumnIndex("WLI_ID"));
        }
        return wli_id;
    }
}
