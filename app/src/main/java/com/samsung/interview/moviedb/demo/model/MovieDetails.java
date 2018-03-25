package com.samsung.interview.moviedb.demo.model;

/**
 * This class is used for representing a Movie obtained from the data layer with all the details
 * Created by licha on 3/24/2018.
 */

public class MovieDetails extends Movie{
    /**
     * The movie run time
     **/
    private int runtime;
    /**
     * The movie overview description
     **/
    private String overview;
    /**
     * The movie backdrop
     **/
    protected String backdrop;
    /**
     * The movie genres, must be filled
     **/
    private Genre[] genres;
    /**
     * Vote count
     */
    private int votecount;

    /**
     * Home Page
     */
    private String homepage;


    public MovieDetails(int id, String title, String original_title, String release_date,
                        String poster, String backdrop, double rating, double popularity,
                        int runtime, String overview, Genre[] genres, int votecount, String homepage) {
        super(id, title, original_title, release_date, poster, rating, popularity, genres);
        this.runtime = runtime;
        this.overview = overview;
        this.backdrop = backdrop;
        this.genres = genres;
        this.votecount = votecount;
        this.homepage = homepage;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public String getHomepage() {
        return homepage;
    }

    public Genre[] getGenres() {
        return this.genres;
    }

    public int getVotecount() {
        return this.votecount;
    }
}
