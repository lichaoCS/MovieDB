package com.samsung.interview.moviedb.demo.data.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Helper to create the movies db, contains the sql to create tables

 * Created by licha on 3/24/2018.
 */

public class MoviesDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "movies.db";
    public static final int DATABASE_VERSION = 1;

    private static final String CREATE_MOVIES_TABLE_SQL = "CREATE TABLE " + MoviesContract.MovieEntry.TABLE_NAME
            + "("
            + MoviesContract.MovieEntry.COLUMN_ID + " INTEGER, "
            + MoviesContract.MovieEntry.COLUMN_ORIGINAL_TITLE + " TEXT NOT NULL, "
            + MoviesContract.MovieEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL, "
            + MoviesContract.MovieEntry.COLUMN_RATING + " REAL NOT NULL, "
            + MoviesContract.MovieEntry.COLUMN_POPULARITY + " REAL NOT NULL, "
            + MoviesContract.MovieEntry.COLUMN_POSTER + " TEXT, "
            + MoviesContract.MovieEntry.COLUMN_LIST_TYPE + " TEXT NOT NULL, "
            + MoviesContract.MovieEntry.COLUMN_LANG + " TEXT NOT NULL, "
            + MoviesContract.MovieEntry.COLUMN_TITLE + " TEXT NOT NULL,"
            + MoviesContract.MovieEntry.COLUMN_GENRES + " TEXT NOT NULL,"
            + "PRIMARY KEY(" + MoviesContract.MovieEntry.COLUMN_ID + ", " + MoviesContract.MovieEntry.COLUMN_LIST_TYPE + ")"
            + ");";


    public MoviesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * Enable foreign keys in sqlite
     *
     * @param db
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MOVIES_TABLE_SQL);
        //db.execSQL(CREATE_FOLLOW_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //no code yet!
    }
}
