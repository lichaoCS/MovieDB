package com.samsung.interview.moviedb.demo.ui.presenter.base;

import com.samsung.interview.moviedb.demo.ui.fragment.base.ILoadDataView;

/**
 * Presenter in a model view presenter (MVP) pattern.
 * Should load data and pass it to the view
 * @param <T>
 * Created by licha on 3/24/2018.
 */

public abstract class Presenter<T> implements IPresenter {

    protected final String TAG = "PRESENTER_" + getClass().getSimpleName();
    protected ILoadDataView<T> view;

    public Presenter(ILoadDataView<T> view) {
        this.view = view;
    }

    /*
    |--------------------------------------------------------------------------
    | Presenter Lifecycle
    |--------------------------------------------------------------------------
    */
    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onDestroy() {
        //remove reference, no memory leaks
        this.view = null;
    }
}
