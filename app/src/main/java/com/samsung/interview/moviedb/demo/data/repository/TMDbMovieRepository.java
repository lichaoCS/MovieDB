package com.samsung.interview.moviedb.demo.data.repository;

import com.samsung.interview.moviedb.demo.data.api.TMDbApiSync;
import com.samsung.interview.moviedb.demo.data.api.entity.GenreListingDTO;
import com.samsung.interview.moviedb.demo.data.api.entity.MovieDTO;
import com.samsung.interview.moviedb.demo.data.api.entity.MovieListingDTO;
import com.samsung.interview.moviedb.demo.data.exception.FailedGettingDataException;
import com.samsung.interview.moviedb.demo.data.mapper.DTOModelEntitiesDataMapper;
import com.samsung.interview.moviedb.demo.data.repository.base.ICloudMovieRepository;
import com.samsung.interview.moviedb.demo.model.Genre;
import com.samsung.interview.moviedb.demo.model.Movie;
import com.samsung.interview.moviedb.demo.model.MovieDetails;

import java.io.IOException;
import java.util.List;

/**
 * Movies Repository, fetch list of movies and movies details
 * This class  uses an api and a mapper to retrieve the data and convert to a model entity
 * synchronously
 *
 * Created by licha on 3/24/2018.
 */

public class TMDbMovieRepository implements ICloudMovieRepository {

    protected final String TAG = "DEBUG_" + getClass().getSimpleName();

    private final TMDbApiSync api;
    private final DTOModelEntitiesDataMapper mapper;

    public TMDbMovieRepository(TMDbApiSync api,
                               DTOModelEntitiesDataMapper mapper) {
        this.api = api;
        this.mapper = mapper;
    }

    /**
     * Get movies list in theaters synchronously
     * And convert to a model entity
     *
     * @param page api page
     * @return Entity model
     * @throws FailedGettingDataException
     */
    @Override
    public List<Movie> getNowPlayingMovies(int page) throws FailedGettingDataException {
        try {
            MovieListingDTO data = this.api.getTheatersMovies(page);
            return this.mapper.transform(data);
        } catch (IOException e) {
            throw new FailedGettingDataException(e);
        }
    }

    /**
     * Get movies list that will be in theaters soon synchronously
     * And convert to a model entity
     *
     * @param page api page
     * @return Entity model
     * @throws FailedGettingDataException
     */
    @Override
    public List<Movie> getUpcomingMovies(int page) throws FailedGettingDataException {

        try {
            MovieListingDTO data = this.api.getSoonMovies(page);
            return this.mapper.transform(data);
        } catch (IOException e) {
            throw new FailedGettingDataException(e);
        }
    }


    /**
     * Get movie by id synchronously
     * And convert to a model entity
     *
     * @return Entity model
     * @throws FailedGettingDataException
     */
    @Override
    public MovieDetails getMovieById(int id) throws FailedGettingDataException {
        try {
            MovieDTO data = this.api.getMovie(id);
            return this.mapper.transform(data);
        } catch (IOException e) {
            throw new FailedGettingDataException(e);
        }
    }



    @Override
    public  List<Genre> getGenres() throws FailedGettingDataException {
        try {
            GenreListingDTO data = this.api.getGenres();
            return this.mapper.transform(data);
        } catch (IOException e) {
            throw new FailedGettingDataException(e);
        }
    }
}
