package com.samsung.interview.moviedb.demo.model;

import com.samsung.interview.moviedb.demo.Utils;

/**
 * This class is used for representing a Movie details obtained from the data layer
 * by a movies list. It contains less data than a MovieDetails.
 *
 * Genres must be filled after retrieved.
 *
 * Created by licha on 3/24/2018.
 */

public class Movie {
    /**
     * The movie id
     **/
    protected int id;
    /**
     * The movie title
     **/
    protected String title;
    /**
     * The movie original title
     **/
    protected String original_title;
    /**
     * The movie release date
     **/
    protected String release_date;
    /**
     * The movie popularity
     **/
    protected double rating;
    /**
     * The movie popularity
     **/
    protected double popularity;
    /**
     * The movie poster image
     **/
    protected String poster;
    /**
     * The movie genres
     **/
    protected String genres;

    /**
     * The movie genres, must be filled
     **/
    //  private int[] genresId = null;


    public Movie(int id, String title, String original_title, String release_date,
                 String poster, double rating, double popularity , String genres) {
        this.id = id;
        this.title = title;
        this.original_title = original_title;
        this.release_date = release_date;
        this.poster = poster;
        this.rating = rating;
        this.popularity = popularity;
        this.genres = genres;
    }


    public Movie(int id, String title, String original_title, String release_date,
                 String poster, double rating, double popularity , Genre[] genresList) {
        this.id = id;
        this.title = title;
        this.original_title = original_title;
        this.release_date = release_date;
        this.poster = poster;
        this.rating = rating;
        this.popularity = popularity;
        this.genres = Utils.createGenreText(genresList);
    }


    public Movie(int id, String title, String original_title, String release_date,
                 String poster, double rating, double popularity , int[] genresId) {
        this.id = id;
        this.title = title;
        this.original_title = original_title;
        this.release_date = release_date;
        this.poster = poster;
        this.rating = rating;
        this.popularity = popularity;
        this.genres = Utils.createGenreTextFromId(genresId);

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public double getRating() {
        return rating;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPoster() {
        return poster;
    }

    public String getGenresString(){ return  genres;}
}

