package com.samsung.interview.moviedb.demo.ui.fragment.base;

import android.os.Bundle;

import com.samsung.interview.moviedb.demo.ui.presenter.base.IPresenter;

/**
 * Presentable Fragment class for every fragment with a presenter in this application.
 * Created by licha on 3/24/2018.
 */

public abstract class PresentableFragment extends LoggingFragment {
    /**
     * Fragment Presenter
     */
    protected IPresenter presenter;
    /**
     * Method that return the presenter for the fragment
     * Must be called in the concrete implementation
     */
    protected abstract IPresenter createPresenter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (this.presenter == null)
            this.presenter = this.createPresenter();
    }

    public IPresenter getPresenter() {
        return this.presenter;
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
