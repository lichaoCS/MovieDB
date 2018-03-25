package com.samsung.interview.moviedb.demo.data.repository.base;

import com.samsung.interview.moviedb.demo.data.exception.FailedGettingDataException;
import com.samsung.interview.moviedb.demo.model.Genre;
import com.samsung.interview.moviedb.demo.model.Movie;
import com.samsung.interview.moviedb.demo.model.MovieDetails;

import java.util.List;

/**
 * This interface defines the contract with the data layer
 * and the presentation layer for requesting data synchronously
 *
 * Created by licha on 3/24/2018.
 */

public interface ICloudMovieRepository {

    /**
     * Get movies list in theaters synchronously
     * And convert to a model entity
     *
     * @param page api page
     * @return Entity model
     * @throws FailedGettingDataException
     */
    List<Movie> getNowPlayingMovies(int page) throws FailedGettingDataException;

    /**
     * Get movies list that will be in theaters soon synchronously
     * And convert to a model entity
     *
     * @param page api page
     * @return Entity model
     * @throws FailedGettingDataException
     */
    List<Movie> getUpcomingMovies(int page) throws FailedGettingDataException;


    /**
     * Get movie by id synchronously
     * And convert to a model entity
     *
     * @param id
     * @return Entity model
     * @throws FailedGettingDataException
     */
    MovieDetails getMovieById(int id) throws FailedGettingDataException;

    //  MovieCredits getCreditsOfMovie(int id) throws FailedGettingDataException;*/

    List<Genre> getGenres() throws FailedGettingDataException;
}
