package com.samsung.interview.moviedb.demo.ui.fragment.base;

import android.content.Context;

/**
 * This interface is a contract for representing a loading data view
 * @param <T>
 * Created by licha on 3/24/2018.
 */

public interface ILoadDataView<T> {

    /**
     * Show a view with a progress bar indicating a loading process.
     */
    void  showLoading();

    /**
     * Hide a loading view and show the results
     */
    void showResults(boolean reloading);

    /**
     * Show a view saying there is no connection
     */
    void showNoConnection();

    /**
     * Show an error message
     *
     * @param message A string representing an error.
     */
    void showError(String message);

    /**
     * Add data to the view
     * @param data
     */
    void setData(T data);

    /**
     * Get the context
     */
    Context getViewContext();

}
