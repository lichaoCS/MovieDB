package com.samsung.interview.moviedb.demo.data.api.entity;

import java.util.List;

/**
 * Genre list Data Transfer Object
 * Used to store the genres request of TMDb
 * Created by licha on 3/24/2018.
 */

public class GenreListingDTO {

    private List<GenreDTO> genres;

    public List<GenreDTO> getGenres() {
        return genres;
    }


    /**
     * DTO for genre list element
     */
    public static class GenreDTO {
        private int id;
        private String name;

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }
    }

}
