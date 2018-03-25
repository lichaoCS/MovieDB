package com.samsung.interview.moviedb.demo.ui.fragment;

import com.samsung.interview.moviedb.demo.ui.fragment.base.MovieListableFragment;
import com.samsung.interview.moviedb.demo.ui.presenter.base.IPresenter;
import com.samsung.interview.moviedb.demo.ui.presenter.UpcomingMoviesListPresenter;

/**
 * upcoming movies fragment
 *
 * Created by licha on 3/24/2018.
 */

public class UpcomingMovieListFragment extends MovieListableFragment {

    @Override
    protected IPresenter createPresenter() {
        return new UpcomingMoviesListPresenter(this);
    }
}
