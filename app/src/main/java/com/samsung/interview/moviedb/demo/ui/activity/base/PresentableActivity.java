package com.samsung.interview.moviedb.demo.ui.activity.base;

import android.os.Bundle;

import com.samsung.interview.moviedb.demo.ui.presenter.base.IPresenter;

/**
 * resentable Activity class for every class in this Application with a presenter
 * Created by licha on 3/25/2018.
 */

public abstract class PresentableActivity extends LoggingActivity {

    /**
     * The Activity presenter for handling data
     */
    protected IPresenter presenter;

    /**
     * Method that return the presenter for the activity
     * Must be called in the concrete implementation
     */
    protected abstract IPresenter createPresenter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (this.presenter == null)
            this.presenter = this.createPresenter();
    }

    /*
    |--------------------------------------------------------------------------
    | Control presenter lifecycle
    |--------------------------------------------------------------------------
    */
    @Override
    public void onResume() {
        super.onResume();

        this.presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.presenter.onPause();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        this.presenter.onDestroy();
        this.presenter = null;
    }
}

