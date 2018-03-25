package com.samsung.interview.moviedb.demo.data.api;

import com.samsung.interview.moviedb.demo.data.api.entity.GenreListingDTO;
import com.samsung.interview.moviedb.demo.data.api.entity.MovieDTO;
import com.samsung.interview.moviedb.demo.data.api.entity.MovieListingDTO;

import java.io.IOException;
import java.util.Locale;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Concrete implementation of TMDb Service API
 * Used to get Movie Data DTO's from HTTP Requests Synchronously
 * Created by licha on 3/24/2018.
 */

public class TMDbApiSync {

    private ITMDbServiceAPI api;
    private String language;

    public TMDbApiSync() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ITMDbServiceAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.api = retrofit.create(ITMDbServiceAPI.class);
        this.language = Locale.getDefault().getLanguage();
    }

    /**
     * Get the movies in the theaters right now
     * Synchronously
     *
     * @throws IOException
     */
    public MovieListingDTO getTheatersMovies(int page) throws IOException {
        return this.api.getNowPlaying(ITMDbServiceAPI.API_KEY, page, this.language).execute()
                .body();
    }

    /**
     * Get next movies getting into theaters
     *
     * @throws IOException
     */
    public MovieListingDTO getSoonMovies(int page) throws IOException {
        return this.api.getUpcoming(ITMDbServiceAPI.API_KEY, page, this.language).execute().body();
    }


    /**
     * Get a movie details
     * Synchronously
     *
     * @throws IOException
     */
    public MovieDTO getMovie(int id) throws IOException {
        return this.api.getMovie(id, ITMDbServiceAPI.API_KEY, this.language)
                .execute().body();
    }



    /**
     * Get the genres list
     *
     * @return
     * @throws IOException
     */
    public GenreListingDTO getGenres() throws IOException {
        return this.api.getGenres(ITMDbServiceAPI.API_KEY, this.language).execute().body();
    }

}

