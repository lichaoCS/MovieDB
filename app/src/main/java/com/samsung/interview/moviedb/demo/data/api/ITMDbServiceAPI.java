package com.samsung.interview.moviedb.demo.data.api;

import com.samsung.interview.moviedb.demo.data.api.entity.GenreListingDTO;
import com.samsung.interview.moviedb.demo.data.api.entity.MovieDTO;
import com.samsung.interview.moviedb.demo.data.api.entity.MovieListingDTO;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
/**
 * ITMDb Service API
 * Created by licha on 3/24/2018.
 */

public interface ITMDbServiceAPI {

    String API_KEY = "9bc5152abdd7949bf9145ee2751363a5";
    String BASE_URL = "http://api.themoviedb.org";
    String BASE_IMAGES_URL = "http://image.tmdb.org/t/p/";
    String POSTER_SIZE = "w185";
    String BACKDROP_SIZE = "w780";

    /**
     * Retrieves a specific movie through his {@code id}
     *
     * @param id      movie id
     * @param api_key api_key used to retrieve information
     * @param lang    to retrieve in this language (ISO 639-1 code)
     * @return
     */
    @GET("/3/movie/{id}")
    Call<MovieDTO> getMovie(@Path("id") int id,
                            @Query("api_key") String api_key,
                            @Query("language") String lang);

    /**
     * Retrieves a list of movies that are now on cinema.
     *
     * @param API_KEY  api_key used to retrieve information
     * @param page     page (Minimum 1, maximum 1000)
     * @param language to retrieve in this language (ISO 639-1 code)
     * @return
     */
    @GET("/3/movie/now_playing")
    Call<MovieListingDTO> getNowPlaying(@Query("api_key") String API_KEY,
                                        @Query("page") int page,
                                        @Query("language") String language);

    /**
     * Retrieves a list of movies that are to come to cinema.
     *
     * @param API_KEY  api_key used to retrieve information
     * @param page     page (Minimum 1, maximum 1000)
     * @param language to retrieve in this language (ISO 639-1 code)
     * @return
     */
    @GET("/3/movie/upcoming")
    Call<MovieListingDTO> getUpcoming(@Query("api_key") String API_KEY,
                                      @Query("page") int page,
                                      @Query("language") String language);




    /**
     * Retrieves a lisf of the movies genres
     *
     * @param API_KEY
     * @param language
     * @return
     */
    @GET("/3/genre/movie/list")
    Call<GenreListingDTO> getGenres(@Query("api_key") String API_KEY,
                                    @Query("language") String language);


}

