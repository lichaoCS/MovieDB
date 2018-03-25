package com.samsung.interview.moviedb.demo.data.mapper;

import com.samsung.interview.moviedb.demo.data.api.ITMDbServiceAPI;
import com.samsung.interview.moviedb.demo.data.api.entity.GenreListingDTO;
import com.samsung.interview.moviedb.demo.data.api.entity.MovieDTO;
import com.samsung.interview.moviedb.demo.data.api.entity.MovieListingDTO;
import com.samsung.interview.moviedb.demo.model.Genre;
import com.samsung.interview.moviedb.demo.model.Movie;
import com.samsung.interview.moviedb.demo.model.MovieDetails;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class knows how to transform a data entity to a model entity
 * Created by licha on 3/24/2018.
 */

public class DTOModelEntitiesDataMapper {


    /**
     * Transform a movie data entity to a business model
     *
     * @param dto
     * @return
     */
    public MovieDetails transform(MovieDTO dto) {
        Genre[] genres = this.createGenres(dto.getGenres());

        return new MovieDetails(dto.getId(),
                dto.getTitle(),
                dto.getOriginalTitle(),
                dto.getReleaseDate(),
                createPosterLink(dto.getPosterPath()),
                createBackdropLink(dto.getBackdropPath()),
                dto.getVoteAverage(),
                dto.getPopularity(),
                dto.getRuntime(),
                dto.getOverview(),
                genres,
                dto.getVoteCount(),
                dto.getHomepage()
        );
    }

    /**
     * Transform a movie list details data entity to a business details model
     *
     * @param dto
     * @return
     */
    public Movie transform(MovieListingDTO.MovieListDTO dto) {
        // Genre[] genres = this.createGenres(dto.getGenreIds());
        return new Movie(dto.getId(),
                dto.getTitle(),
                dto.getOriginalTitle(),
                dto.getReleaseDate(),
                createPosterLink(dto.getPosterPath()),
                dto.getVoteAverage(),
                dto.getPopularity(),
                dto.getGenreIds());
    }

    /**
     * Transform a movie list data entity to a business list model
     *
     * @param dto
     * @return
     */
    public List<Movie> transform(MovieListingDTO dto) {
        List<Movie> movies = new ArrayList<>();
        List<MovieListingDTO.MovieListDTO> dtoList = dto.getResults();
        for (MovieListingDTO.MovieListDTO movie : dtoList) {
            movies.add(transform(movie));
        }
        return movies;
    }


    /**
     * Transform a genres list data entity to a business list model
     *
     * @param dto
     * @return
     */
    public List<Genre> transform(GenreListingDTO dto) {
        List<Genre> genres = new LinkedList<>();
        List<GenreListingDTO.GenreDTO> dtoList = dto.getGenres();
        for (GenreListingDTO.GenreDTO genreDto : dtoList) {
            genres.add(new Genre(genreDto.getId(), genreDto.getName()));
        }
        return genres;
    }

    /**
     * Transform a Genres DTO to a Genre model
     *
     * @param genres
     * @return
     */
    private Genre[] createGenres(MovieDTO.GenreDTO[] genres) {
        Genre[] res = new Genre[genres.length];

        for (int i = 0; i < genres.length; i++) {
            res[i] = new Genre(genres[i].getId(), genres[i].getName());
        }

        return res;
    }

    /**
     * Transform a relative path to a complete URI poster image
     *
     * @param path
     * @return
     */
    private String createPosterLink(String path) {
        if (path == null) return null;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(ITMDbServiceAPI.BASE_IMAGES_URL);
        stringBuilder.append(ITMDbServiceAPI.POSTER_SIZE);
        stringBuilder.append(path);

        return stringBuilder.toString();
    }

    /**
     * Transform a relative backdrop path to a complete URI
     *
     * @param path
     * @return
     */
    private String createBackdropLink(String path) {
        if (path == null) return null;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(ITMDbServiceAPI.BASE_IMAGES_URL);
        stringBuilder.append(ITMDbServiceAPI.BACKDROP_SIZE);
        stringBuilder.append(path);

        return stringBuilder.toString();
    }
}
