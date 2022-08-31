package com.example.partnersincode.optimedia;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.partnersincode.optimedia.models.Genre;
import com.example.partnersincode.optimedia.models.Library;
import com.example.partnersincode.optimedia.models.Movie;
import com.example.partnersincode.optimedia.models.Series;
import com.example.partnersincode.optimedia.models.WatchObject;

import java.util.ArrayList;

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
    private static final String CREATE_SERIES_LOG = "CREATE TABLE SeriesLog (SL_ID INTEGER PRIMARY KEY AUTOINCREMENT, seriesID INTEGER REFERENCES Series (seriesID), season STRING, episode STRING, s_note TEXT, s_timestamp TEXT)";
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
                "('1','The Witcher 3: Wild Hunt','Open World, RPG','false','false','false'), "+
                "('4','Counter-Strike: Global Offensive','FPS Multiplayer','false','false','false'), "+
                "('6','Animal Crossing: New Horizons','Life simulation','false','false','false')";
        sqLiteDatabaseDB.execSQL(query);

        //add movies
        query = "INSERT INTO Movie (genreID,movieTitle,favourite,started,complete) VALUES ('5','Paper Towns','false','false','false'),"+
                " ('1','Fallen','false','false','false'), ('5','The perks of being a wallflower','false','false','false'), "+
                " ('2','Ready Player One','false','false','false'), ('3','To all the Boys I''ve Loved Before','false','false','false')";
        sqLiteDatabaseDB.execSQL(query);

        //add series
        query = "INSERT INTO Series (genreID,seriesTitle,favourite,started,complete) VALUES ('1','Fate: The Winx Saga','false','false','false'),"+
                " ('2','The Umbrella Academy','false','false','false'), ('3','Bridgerton','false','false','false'), "+
                " ('5','The Summer I Turned Pretty','false','false','false'), ('6','Lightyear','false','false','false')";
        sqLiteDatabaseDB.execSQL(query);

        //add watchlist item
        query = "INSERT INTO WatchListItem (seriesID,movieID, link) VALUES ('1',NULL,''), (NULL,'3',''), ('3',NULL,'')";
        sqLiteDatabaseDB.execSQL(query);

        //add library
        query = "INSERT INTO Library (libraryName,libraryType) VALUES ('To Be Read','Book'), ('Indie Favs','Game'), ('Watch with K','Watch'), " +
                "('Current Reads','Book'), ('Watching123','Watch')";
        sqLiteDatabaseDB.execSQL(query);

        //add book library
        query = "INSERT INTO BookLibrary (libraryID,bookID) VALUES ('4','1'), ('1','3')";
        sqLiteDatabaseDB.execSQL(query);

        //add game library
        query = "INSERT INTO GameLibrary (libraryID,gameID) VALUES ('2','1')";
        sqLiteDatabaseDB.execSQL(query);

        //add watch library
        query = "INSERT INTO WatchLibrary (libraryID,WLI_ID)VALUES ('3','1'), ('3','2'), ('3','3'), ('5','4'),('5','5')";
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

    public void createLibrary(String LibraryName, String LibraryType){
        Log.d("createLibrary", " starting ");
        SQLiteDatabase db = this.getWritableDatabase();

        //Works both ways:
        //Way 1:
//        String query = "INSERT INTO Library (libraryName,libraryType) VALUES ('"+ LibraryName +"','"+ LibraryType +"')";
//        if (LibraryType.equals("Movie/Series")){
//            query = "INSERT INTO Library (libraryName,libraryType) VALUES ('"+ LibraryName +"','Watch')";
//        }
//        db.execSQL(query);

        //Way 2: Helpful because you get the ID as well
        ContentValues values = new ContentValues();
        values.put("libraryName", LibraryName);
        if (LibraryType.equals("Movie/Series")){
            values.put("libraryType", "Watch");
        } else {
            values.put("libraryType", LibraryType);
        }
        long id = db.insertWithOnConflict("Library", null, values, SQLiteDatabase.CONFLICT_IGNORE);
        Log.d("createLibrary", "complete // " +id);
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
                
                moviesAndSeries.add(new Movie(movieID,genreID,movieTitle,favourite,started,complete));

            }while(movies.moveToNext());
        }
        
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

                moviesAndSeries.add(new Series(seriesID,genreID,seriesTitle,favourite,started,complete));

            }while(series.moveToNext());
        }


        return moviesAndSeries;
    }

}
