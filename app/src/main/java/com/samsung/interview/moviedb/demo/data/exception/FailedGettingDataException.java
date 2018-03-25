package com.samsung.interview.moviedb.demo.data.exception;

import java.io.IOException;

/**
 * A class that represents an exception that occur when something odd happens getting
 * the data from the repository
 *
 * Created by licha on 3/24/2018.
 */

public class FailedGettingDataException extends Exception {

    /**
     * Construct the exception by passing an IOException
     * @param ex
     */
    public FailedGettingDataException(IOException ex) {
        super(ex.getMessage());
    }
}
