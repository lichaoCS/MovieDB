package com.samsung.interview.moviedb.demo.data.exception;

/**
 * Exception throw by the application when there is a network connection exception.
 *
 * Created by licha on 3/24/2018.
 */

public class NetworkConnectionException extends Exception {

    public NetworkConnectionException() {
        super();
    }

    public NetworkConnectionException(final String message) {
        super(message);
    }

    public NetworkConnectionException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NetworkConnectionException(final Throwable cause) {
        super(cause);
    }
}
